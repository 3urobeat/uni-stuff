package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Controller;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This controller automatically prepares your components for rendering. and
 * handles your key strokes. The rest is up to you.
 * 
 * @author leondietrich
 *
 */
public abstract class ComponentController extends Controller<ComponentModel> {

	private String activeTextInput;

	public ComponentController(ComponentModel m) {
		super(m);
		this.activeTextInput = null;
	}

	/**
	 * This controller class automatically enables your first text input.
	 */
	public void prepare() {
		boolean textInputActivated = false;
		for (Entry<String, Component> c : this.getModelInstance().getNamedComponents()) {
			if (c.getValue() instanceof TextInput) {
				TextInput ti = (TextInput) c.getValue();
				ti.notifyActivationStateChanged(!textInputActivated);
				if(ti.getOwnActiveState()) {
					this.activeTextInput = c.getKey();
				}
				this.activeTextInput = (!textInputActivated) ? this.activeTextInput : c.getKey();
				textInputActivated = true;
			}
		}
	}

	@Override
	public void clickedAt(Vec2 position) {
		// Search for a click candidate
		final LinkedList<Entry<String, Component>> clickCandidates = new LinkedList<Entry<String, Component>>();
		for (Entry<String, Component> entry : this.getModelInstance().getNamedComponents()) {
			if (entry.getValue() instanceof Clickable) {
				final Clickable c = (Clickable) entry.getValue();
				if (c.checkedIfClicked(position)) {
					clickCandidates.add(entry);
				}
			} else {
				final Component c = entry.getValue();
				if (c.getPosition().getX() <= position.getX()
						&& position.getX() <= c.getPosition().getX() + c.getWidth()
						&& c.getPosition().getY() <= position.getY()
						&& position.getY() <= c.getPosition().getY() + c.getHeight()) {
					clickCandidates.add(entry);
				}
			}
		}
		// Process the click
		if (clickCandidates.isEmpty()) {
			Application.log("Clicked at nothing?");
			return;
		}
		clickCandidates.sort(new Comparator<Entry<String, Component>>() {

			@Override
			public int compare(Entry<String, Component> arg0, Entry<String, Component> arg1) {
				final Component c = arg0.getValue();
				if(!(c instanceof LateRendering && c instanceof Clickable)) {
					return 1;
				}
				final LateRendering l = (LateRendering) c;
				return l.isCurrentlyLate() ? -1 : 1;
			}});
		final Entry<String, Component> c = clickCandidates.get(0);
		if (c != null) {
			Application.log("Processing click of " + c.getValue().toString());
			if (c.getValue() instanceof TextInput) {
				TextInput ti = (TextInput) c.getValue();
				ti.notifyActivationStateChanged(true);
				if (this.activeTextInput != null) {
					final Component oldActiveInput = this.getModelInstance().getComponent(this.activeTextInput);
					if (oldActiveInput != null) {
						if (oldActiveInput instanceof TextInput) {
							final TextInput oldTi = (TextInput) oldActiveInput;
							oldTi.notifyActivationStateChanged(false);
						}
					}
				}
				this.activeTextInput = c.getKey();
			}
			if (c.getValue() instanceof Clickable) {
				Clickable cli = (Clickable) c.getValue();
				cli.performAction();
			}
		}
	}

	@Override
	public void keyPressed(int key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left,
			boolean right) {
		if (this.activeTextInput == null)
			return;
		final Component c = this.getModelInstance().getComponent(this.activeTextInput);
		if (c == null)
			return;
		if (!(c instanceof TextInput)) {
			throw new RuntimeException(
					"At the preparation state the corresponding component was a TextInput. Now it's not. Whaaat?");
		}
		TextInput ti = (TextInput) c;
		ti.registerKeyInput(key, down, up, left, right);
	}
}
