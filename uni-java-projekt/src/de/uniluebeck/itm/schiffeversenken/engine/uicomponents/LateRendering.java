package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

/**
 * Use this interface to mark drawables that should be rendered late.
 * @author leondietrich
 *
 */
public interface LateRendering {
	
	/**
	 * This method must be implemented to check if the current object should be rendered late at the moment.
	 * @return True if it should be late.
	 */
	public abstract boolean isCurrentlyLate();
}
