package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

/**
 * Use this class to mark your component as capable of accepting text.
 * 
 * @author leondietrich
 *
 */
public interface TextInput {

	/**
	 * This method gets called by a ComponentController when the corresponding
	 * component gets activated.
	 * 
	 * @param newState The new activation state.
	 */
	public abstract void notifyActivationStateChanged(boolean newState);

	/**
	 * This method should represent the components own activation state. It will be
	 * used by a ComponentView
	 * 
	 * @return The internal state.
	 */
	public abstract boolean getOwnActiveState();

	/**
	 * This method gets called by a ComponentController when there is new Text to be
	 * stored.
	 * 
	 * @param key The key that was pressed.
	 * @param down  Was the down arrow key pressed?
	 * @param up    Was the up arrow key pressed?
	 * @param left  Was the left arrow key pressed?
	 * @param right Was the right arrow key pressed?
	 */
	public abstract void registerKeyInput(int key, boolean down, boolean up, boolean left, boolean right);

	/**
	 * As most text inputs consist of an internal buffer this method should contain
	 * its state. A calling method must assume that it may return null if it isn't
	 * appropriate.
	 * 
	 * @return The current buffer content as a String or null.
	 */
	public abstract String getCurrentTextBuffer();
}
