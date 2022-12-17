package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * GUIContext
 * 
 * This class provides an abstraction to the OS windowing subsystem.
 * 
 * @author leondietrich
 *
 */
public abstract class GUIContext {

	private boolean inputDebuggingEnabled = false;

	/**
	 * Construct a new GUI context
	 * 
	 * @param windowtitle The window title to produce (if applicable)
	 */
	public GUIContext(String windowtitle) {
	}

	/**
	 * This method must provide a canvas object suitable to paint on the window.
	 * 
	 * @return The canvas object.
	 */
	public abstract Canvas getCanvas();

	/**
	 * This method shall be called after everything has been drawn. It should take
	 * care of swapping the buffers so that the user can see what has been rendered.
	 */
	public abstract void performPaintOperation();

	/**
	 * Call this method in order to destroy the gui objects.
	 */
	public abstract void destroy();

	/**
	 * This method gets called when it is time to display the game.
	 */
	public abstract void setup();

	/**
	 * Use this method in order to load a tile in a way suitable for the current gui context.
	 *
	 * @param filePath The file to load. This may be a relative path.
	 * @return The loaded tile.
	 */
	public abstract Tile loadTile(String filePath);

	/**
	 * Set the graphics resolution. Don't call this while drawing. I've warned you.
	 * 
	 * @param width  The width to set to
	 * @param height The height to set to
	 */
	public abstract void setResolution(int width, int height);
	
	/**
	 * Use this method in order to obtain the current resolution.
	 * @return The resolution as a Vec2 object.
	 */
	public abstract Vec2 getResolution();

	/**
	 * This method notifies the handler about a scene switch.
	 * 
	 * @param s the new Scene.
	 */
	abstract void appendScene(Scene s);

	/**
	 * Use this method in order to enable or disable input debugging. When input
	 * debugging is enabled the context logs incoming mouse and keyboard actions.
	 * 
	 * @param enabled The new value to set the flag to.
	 */
	public void setInputDebugModeEnabled(boolean enabled) {
		this.inputDebuggingEnabled = enabled;
	}

	/**
	 * Use this method in order to check if input debugging is enabled.
	 * 
	 * @return The current status of the flag.
	 */
	public boolean isInputDebuggingEnabled() {
		return this.inputDebuggingEnabled;
	}
	

	/**
	 * Use this method in order to obtain the current mouse cursor location. Please
	 * note that they might be negative or greater than the canvas itself as the
	 * context might be windowed.
	 * 
	 * @return The coordinates of the mouse relative to the canvas.
	 */
	public abstract Vec2 getMouseCursorLocation();

}
