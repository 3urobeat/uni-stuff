package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * Use this interface to make your component drawable.
 * @author leondietrich
 *
 */
public interface Drawable {
	
	/**
	 * Implement this method in order to draw your component.
	 * @param c The Canvas to draw on.
	 * @param mouseLocation the location of the mouse relative to the canvas
	 */
	public abstract void draw(Canvas c, Vec2 mouseLocation);
}
