package de.uniluebeck.itm.schiffeversenken.game.menues;

import java.util.Map;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Controller;
import de.uniluebeck.itm.schiffeversenken.engine.Scene;
import de.uniluebeck.itm.schiffeversenken.engine.SmartScene;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;
import de.uniluebeck.itm.schiffeversenken.engine.View;
import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.Button;
import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.ComponentController;
import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.ComponentModel;
import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.ComponentView;

/**
 * This scene displays the main menu
 * 
 * @author leondietrich
 *
 */
public final class MainMenu {

	private final SmartScene<ComponentModel> scene;

	public MainMenu() {
		super();
		final Vec2 res = Application.getCurrentResolution();
		ComponentModel m = new ComponentModel(
				Map.of("button.newgame", new Button("Neues Spiel", res.getX() / 2 - 250 / 2, 150, 250, 50) {

					@Override
					public void performAction() {
						Application.switchToScene(new GameSetupMenu(null).getScene());
					}
				}, "button.settings", new Button("Einstellungen", res.getX() / 2 - 250 / 2, 210, 250, 50) {

					@Override
					public void performAction() {
						Application.log("This feature was taken out of the game for this year.");
					}
				}, "button.exit", new Button("Beenden", res.getX() / 2 - 250 / 2, 270, 250, 50) {

					@Override
					public void performAction() {
						Application.stopApplication();
					}
				}));

		View<ComponentModel> v = new ComponentView(m);

		Controller<ComponentModel> c = new ComponentController(m) {

			@Override
			public void performFrequentUpdates() {
			}
		};

		this.scene = new SmartScene<ComponentModel>(v, c);

	}

	/**
	 * Use this method in order to retrieve the constructed Scene.
	 * 
	 * @return The underlying scene
	 */
	public final Scene getScene() {
		return this.scene;
	}


}
