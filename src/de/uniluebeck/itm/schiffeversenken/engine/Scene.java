package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * The scene class handles all your top level drawing and interaction
 * distribution.
 * 
 * @author leondietrich
 *
 */
public abstract class Scene {

	/**
	 * This method gets called when ever the application decides that it needs to
	 * redraw the screen. Use this method to implement your scene rendering.
	 * 
	 * @param c The canvas to draw on
	 * @param mouseCursorLocation The current position of the mouse cursor relative to the canvas
	 */
	public abstract void draw(Canvas c, Vec2 mouseCursorLocation);

	/**
	 * This method will be called when the scene becomes visible. Use it to set up
	 * (or reset) your scene in order to be nice.
	 */
	public abstract void attach();

	/**
	 * This method gets called prior to rendering. Use it in order to update
	 * anything that needs to be updated multiple times a second.
	 * 
	 * @param milis Time since the last invoking of update
	 */
	public abstract void update(long milis);

	/**
	 * This method will be called just before the scene becomes invisible.
	 */
	public abstract void detach();

	/**
	 * Use this method to check where the user clicked at your scene.
	 * 
	 * @param position The mouse cursor position relative to your scene.
	 */
	public abstract void clickedAt(Vec2 position);

	/**
	 * Use this method in order to receive newly pressed keys
	 * 
	 * @param key   The pressed key
	 * @param shift Was the shift modifier pressed?
	 * @param alt   Was the ALT modifier pressed?
	 * @param ctrl  Was the CTRL modifier pressed?
	 * @param down  Was the down arrow key pressed?
	 * @param up    Was the up arrow key pressed?
	 * @param left  Was the left arrow key pressed?
	 * @param right Was the right arrow key pressed?
	 */
	public abstract void keyPressed(char key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left, boolean right);

}
