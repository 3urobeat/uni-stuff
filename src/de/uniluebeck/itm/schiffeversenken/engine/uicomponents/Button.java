package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This class represents a drawable and clickable button.
 * 
 * @author leondietrich
 *
 */
public abstract class Button extends Component implements Drawable, Clickable {

	private String text;

	/**
	 * Construct a new Button by settings his text.
	 * 
	 * @param text The text to display
	 */
	public Button(String text) {
		super();
		this.text = text;
	}

	/**
	 * Construct a new button.
	 * 
	 * @param text   The text to display
	 * @param x      The X coordinate of the button
	 * @param y      The Y coordinate of the button
	 * @param width  His width
	 * @param height His height
	 */
	public Button(String text, int x, int y, int width, int height) {
		this(text);
		this.setPosition(new Vec2(x, y));
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public boolean checkedIfClicked(Vec2 clickPosition) {
		return clickPosition.getX() >= this.getPosition().getX()
				&& clickPosition.getX() <= this.getPosition().getX() + this.getWidth()
				&& clickPosition.getY() >= this.getPosition().getY()
				&& clickPosition.getY() <= this.getPosition().getY() + this.getHeight()
				&& this.isEnabled();
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		if (this.isVisible()) {
			c.setColor(0.7, 0.7, 0.7);
			if (this.isEnabled())
				c.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
			else
				c.drawRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
			c.setColor(0, 0, 0);
			final Vec2 text_dimensions = c.getTextDimensions(this.getText());
			final int text_width = text_dimensions.getX(), text_height = text_dimensions.getY();
			c.drawString(this.getPosition().getX() + this.getWidth() / 2 - text_width / 2,
					this.getPosition().getY() + (this.getHeight() / 2) - (text_height / 2), this.getText());
		}
	}

	/**
	 * Use this method in order to get the displayed Text.
	 * 
	 * @return the text of the button
	 */
	public final String getText() {
		return text;
	}

	/**
	 * Use this method in order to set a new display text. Keep in mind that a very
	 * long text may be longer than the button itself.
	 * 
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}

}
