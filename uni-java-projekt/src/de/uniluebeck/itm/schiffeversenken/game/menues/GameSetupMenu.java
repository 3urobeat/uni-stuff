package de.uniluebeck.itm.schiffeversenken.game.menues;

import java.util.*;

import de.uniluebeck.itm.schiffeversenken.engine.*;
import de.uniluebeck.itm.schiffeversenken.game.model.Ruleset;
import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.*;

/**
 * This class is used to prepare a new game.
 * 
 * @author leondietrich
 *
 */
public class GameSetupMenu {

	private final SmartScene<ComponentModel> scene;
	private final Textbox playerNameTextBox;
	private final DropdownSelector<Ruleset> ruleSelector;
	private final DropdownSelector<Integer> hardnessSelector;
	private final SimpleLabel loadingHintLabel;
	private final Controller<ComponentModel> controller;

	/**
	 * Use this constructor in order to create a setup menu. When there wasn't a
	 * rule set selected yet (for example if you're creating this menu from within
	 * the main menu) simply leave the parameter set to null.
	 * 
	 * @param selectedRuleset The selected rule set or null
	 */
	public GameSetupMenu(Ruleset selectedRuleset) {
		super();
		final Vec2 res = Application.getCurrentResolution();

		this.playerNameTextBox = new Textbox("Player1", res.getX() / 2 - 100, 150, 200, 20);
		final LinkedHashMap<Ruleset, String> ruleMap = new LinkedHashMap<Ruleset, String>();
		ruleMap.put(Ruleset.OFFICIAL_FIRST_VERSION_RULESET, "Offizielle Regeln - Version1");
		ruleMap.put(Ruleset.OFFICIAL_SECOND_VERSION_RULESET, "Offizielle Regeln - Version2");

		this.ruleSelector = new DropdownSelector<Ruleset>(ruleMap, res.getX() / 2 - 100, 220, 200, 20,
				selectedRuleset == null ? ruleMap.keySet().iterator().next() : selectedRuleset);

		this.hardnessSelector = new DropdownSelector<Integer>(Map.of(0, "Easy", 1,  "Hard"),
				res.getX() / 2 - 100, 250, 200, 20, 0);

		this.loadingHintLabel = new SimpleLabel("Loading. Please wait...", res.getX() / 2 - 50, 250);
		this.loadingHintLabel.setVisible(false);

		ComponentModel m = new ComponentModel(Map.of("textbox.playername", this.playerNameTextBox,
				"label.playernamelabel", new SimpleLabel("Spielername:", res.getX() / 2 - 100, 120),
				"label.ruleselectorhint", new SimpleLabel("Bitte wähle deine Regeln:", res.getX() / 2 - 100, 190),
				"label.loadinghint", this.loadingHintLabel,
				"selector.rulesetselector", this.ruleSelector,
				"selector.hardnessselector", this.hardnessSelector, "button.back",
				new Button("Zurück", res.getX() / 2 - 100, 280, 95, 45) {

					@Override
					public void performAction() {
						Application.switchToScene(new MainMenu().getScene());
					}
				}, "button.createGame", new Button("Weiter", res.getX() / 2 + 5, 280, 95, 45) {

					@Override
					public void performAction() {
						loadingHintLabel.setVisible(true);
						controller.dispatchWork(new Runnable() {
							@Override
							public void run() {
								// This is a bit heavier on the work load so we're putting it on a different thread.
								Ruleset rules = ruleSelector.getCurrentSelectedItem();
								String playerName = playerNameTextBox.getText();
								String computerName = generateComputerName();
								final int hardness = hardnessSelector.getCurrentSelectedItem();
								final Scene s = new ShipPlacementMenuScene(rules,
										new String[] {playerName, computerName},
										hardness, scene);
								Application.switchToScene(s);
								loadingHintLabel.setVisible(false);
							}

							private String generateComputerName() {
								final Random rnd = new Random(System.currentTimeMillis());
								switch (rnd.nextInt(3)) {
									case 0:
										return "[AI] Schnuckie the computer virus";
									case 1:
										return "[AI] Lord Assembler the 5th";
									default:
										return "[AI] Sir Processor - The destroyer";
								}
							}
						});
						controller.startWorkStack();
					}
				}));

		final View<ComponentModel> v = new ComponentView(m);

		this.controller = new ComponentController(m) {

			@Override
			public void performFrequentUpdates() {
			}
		};

		this.scene = new SmartScene<ComponentModel>(v, this.controller);
	}

	public SmartScene<ComponentModel> getScene() {
		return this.scene;
	}

}
