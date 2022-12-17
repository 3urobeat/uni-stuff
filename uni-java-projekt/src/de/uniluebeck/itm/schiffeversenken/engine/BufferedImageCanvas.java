/**
 * 
 */
package de.uniluebeck.itm.schiffeversenken.engine;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * This canvas is SWING/AWT compatible.
 * 
 * @author leondietrich
 *
 */
public final class BufferedImageCanvas extends Canvas {

	private final BufferedImage bi;
	private final Graphics2D g;
	private double[] color;

	public BufferedImageCanvas(@SuppressWarnings("exports") BufferedImage bufferedImage) {
		super();
		this.color = new double[4];
		this.bi = bufferedImage;
		this.g = this.bi.createGraphics();
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	@Override
	public void clear() {
		this.g.clearRect(0, 0, this.bi.getWidth(), this.bi.getHeight());
	}

	@Override
	public void setColor(double r, double g, double b, double a) {
		if (r < 0 || r > 1) {
			throw new RuntimeException(
					"The red value of the provided color needs to be greater or equal to zero and less than or equal to 1.");
		} else if (g < 0 || g > 1) {
			throw new RuntimeException(
					"The green value of the provided color needs to be greater or equal to zero and less than or equal to 1.");
		} else if (b < 0 || b > 1) {
			throw new RuntimeException(
					"The blue value of the provided color needs to be greater or equal to zero and less than or equal to 1.");
		} else if (a < 0 || a > 1) {
			throw new RuntimeException(
					"The alpha value of the provided color needs to be greater or equal to zero and less than or equal to 1.");
		}
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
		this.color[3] = a;
		this.g.setColor(new Color((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255)));
	}

	@Override
	public void drawRect(int x, int y, int width, int height) {
		this.g.drawRect(this.getTranslationX() + x, this.getTranslationY() + y, width, height);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arc_width, int arc_height) {
		this.g.drawRoundRect(this.getTranslationX() + x, this.getTranslationY() + y, width, height, arc_width,
				arc_height);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		this.g.fillRect(this.getTranslationX() + x, this.getTranslationY() + y, width, height);
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arc_width, int arc_height) {
		this.g.fillRoundRect(this.getTranslationX() + x, this.getTranslationY() + y, width, height, arc_width,
				arc_height);
	}

	@Override
	public void drawLine(Vec2 p1, Vec2 p2) {
		this.g.drawLine(this.getTranslationX() + p1.getX(), this.getTranslationY() + p1.getY(),
				this.getTranslationX() + p2.getX(), this.getTranslationY() + p2.getY());
	}

	@Override
	public void drawString(int x, int y, String text) {
		if (text != null)
			this.g.drawString(text, this.getTranslationX() + x, this.getTranslationY() + y);
	}

	@Override
	public void rotate2D(double theta) {
		this.g.rotate(theta);
	}

	@Override
	public void translate(int x, int y) {
		super.translate(x, y);
		this.g.translate(x, y);
	}

	@Override
	public int getResolutionWidth() {
		return this.bi.getWidth();
	}

	@Override
	public int getResolutionHeight() {
		return this.bi.getHeight();
	}

	@Override
	public Vec2 getTextDimensions(String text) {
		if(text == null) {
			return new Vec2(0, 0);
		}
		final FontMetrics fm = this.g.getFontMetrics();
		// Due to some strange legacy behavior java computes text sizes 2 pixels too
		// small.
		return new Vec2(fm.stringWidth(text) + 2, fm.getHeight() + 2);
	}

	@Override
	public double[] getColor() {
		return color;
	}

	/**
	 * Platform specific method in order to render other buffered images at a given location.
	 *
	 * @param bi The BufferedImage to render
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	void renderBufferedImage(BufferedImage bi, int x, int y) {
		this.g.drawImage(bi, x, y, null);
	}

}
