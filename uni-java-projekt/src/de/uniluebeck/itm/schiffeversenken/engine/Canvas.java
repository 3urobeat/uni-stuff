package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * Canvas
 * 
 * This class provides 2D adapting capabilities to the underlying graphics
 * stack.
 * 
 * @author leondietrich
 *
 */
public abstract class Canvas {

	protected int translateX;
	protected int translateY;

	/**
	 * 
	 */
	public Canvas() {
		this.translateX = 0;
		this.translateY = 0;
	}

	/**
	 * This method clears the canvas.
	 */
	public abstract void clear();

	public void translate(int x, int y) {
		this.translateX += x;
		this.translateY += y;
	}

	/**
	 * Use this method in order to obtain the current translation.
	 * 
	 * @return the X part
	 */
	protected int getTranslationX() {
		return translateX;
	}

	/**
	 * Use this method in order to obtain the current translation.
	 * 
	 * @return the Y part
	 */
	protected int getTranslationY() {
		return translateY;
	}

	/**
	 * Use this method in order to set a new Color. Valid values range from 0 to 1.
	 * 
	 * @param r The red component
	 * @param g The green component
	 * @param b The blue component
	 * @param a The alpha component (if supported. Otherwise ignored.)
	 */
	public abstract void setColor(double r, double g, double b, double a);

	/**
	 * Use this method in order to set a new Color. Valid values range from 0 to 1.
	 * 
	 * @param r The red component
	 * @param g The green component
	 * @param b The blue component
	 */
	public void setColor(double r, double g, double b) {
		setColor(r, g, b, 1.0);
	}

	/**
	 * This method shall return the current cursor color
	 * 
	 * @return an array of doubles indexing r,g,b,a
	 */
	public abstract double[] getColor();

	/**
	 * Use this method in order to draw (an unfilled) rectangle on the given
	 * location in the given size.
	 * 
	 * @param x      The X coordinate to draw the rectangle at
	 * @param y      The Y coordinate to draw the rectangle at
	 * @param width  The width to draw the rectangle with
	 * @param height The height to draw the rectangle with
	 */
	public abstract void drawRect(int x, int y, int width, int height);

	/**
	 * Use this method to draw a rectangle with round corners.
	 * 
	 * @param x          The X coordinate to draw the rectangle at
	 * @param y          The Y coordinate to draw the rectangle at
	 * @param width      The width to draw the rectangle with
	 * @param height     The height to draw the rectangle with
	 * @param arc_width  The width of the rounding arc
	 * @param arc_height The height of the rounding arc
	 */
	public abstract void drawRoundRect(int x, int y, int width, int height, int arc_width, int arc_height);

	/**
	 * Use this method in order to fill in a rectangle on the given location in the
	 * given size.
	 * 
	 * @param x      The X coordinate to draw the rectangle at
	 * @param y      The Y coordinate to draw the rectangle at
	 * @param width  The width to draw the rectangle with
	 * @param height The height to draw the rectangle with
	 */
	public abstract void fillRect(int x, int y, int width, int height);

	/**
	 * Use this method to fill in a rectangle with round corners.
	 * 
	 * @param x          The X coordinate to draw the rectangle at
	 * @param y          The Y coordinate to draw the rectangle at
	 * @param width      The width to draw the rectangle with
	 * @param height     The height to draw the rectangle with
	 * @param arc_width  The width of the rounding arc
	 * @param arc_height The height of the rounding arc
	 */
	public abstract void fillRoundRect(int x, int y, int width, int height, int arc_width, int arc_height);

	/**
	 * Use this method in oder to draw a Line from the first point to the second
	 * point.
	 * 
	 * @param p1 The point where the line should start
	 * @param p2 The point where the line should end
	 */
	public abstract void drawLine(Vec2 p1, Vec2 p2);

	/**
	 * Use this method in order to draw a string at the given location.
	 * 
	 * @param x    The X coordinate where the text should be drawn
	 * @param y    The Y coordinate where the text should be drawn
	 * @param text The text to be drawn
	 */
	public abstract void drawString(int x, int y, String text);

	/**
	 * Use this method in order to perform a singular rotation on the planaric
	 * projection according to the given angle
	 * 
	 * @param theta The angle of the rotation to perform
	 */
	public abstract void rotate2D(double theta);

	/**
	 * Use this method in order to obtain the resolution width of the rendering
	 * context.
	 * 
	 * @return The width of the context
	 */
	public abstract int getResolutionWidth();

	/**
	 * Use this method in order to obtain the resolution height of the rendering
	 * context.
	 * 
	 * @return The height of the context
	 */
	public abstract int getResolutionHeight();

	/**
	 * Get the text representation dimensions on the current canvas.
	 * 
	 * @param text The text to compute the dimensions of.
	 * @return a Vec2 object. (X = width of text, Y = height of text)
	 */
	public abstract Vec2 getTextDimensions(String text);

}
