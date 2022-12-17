package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * Use this interface in order to make a component clickable. This interface is
 * particularly useful if you don't insist on classic rectangle forms.
 * 
 * @author leondietrich
 *
 */
public interface Clickable {

	/**
	 * This method gets called in order to check if the object on screen was clicked
	 * on. Do not perform any actions within this method as it may cause
	 * interference.
	 * 
	 * @param clickPosition The position on the canvas where the user clicked at.
	 * @return true if you think that the user meant this object.
	 */
	public abstract boolean checkedIfClicked(Vec2 clickPosition);

	/**
	 * After an controller agent selected the attached object as the target of the
	 * click action it will call this method. You may perform your (lightweight)
	 * operations now.
	 */
	public abstract void performAction();
}
