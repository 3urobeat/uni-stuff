package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * A check box. A box that displays text and that you can click on to check it.
 * 
 * @author leondietrich
 *
 */
public final class Checkbox extends Component implements Clickable, Drawable {

	private String text;
	private boolean activated;

	/**
	 * This constructor gives you a new check box.
	 * 
	 * @param text   The text to display
	 * @param active Should the check box be activated by default or not.
	 * @param x      The x coordinate of the component
	 * @param y      The y coordinate of the component
	 */
	public Checkbox(String text, boolean active, int x, int y) {
		super();
		this.setPosition(new Vec2(x, y));
		this.text = text;
		this.setActivated(active);
		this.setHeight(this.getWidth());
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		if (this.isVisible()) {
			c.setColor(0.7, 0.7, 0.7);
			final Vec2 pos = this.getPosition();
			final int x = pos.getX(), y = pos.getY();
			c.drawString(x + this.getWidth() + 10, y + c.getTextDimensions(text).getY(), text);
			if (this.isActivated()) {
				c.setColor(0, 1, 0);
			} else {
				c.setColor(1, 0, 0);
			}
			if (this.isEnabled()) {
				c.fillRoundRect(x, y, this.getWidth(), this.getHeight(), 5, 5);
			} else {
				c.drawRoundRect(x, y, this.getWidth(), this.getHeight(), 5, 5);
			}
			
			/* if (this.isActivated()) {
				c.setColor(0.2, 0.5, 1);
				c.translate(-x - this.getWidth() / 2, -y - this.getHeight() / 2);
				c.rotate2D(45);
				c.fillRect(1, 1, this.getWidth() / 2, this.getHeight() / 2);
				c.rotate2D(-90);
				c.fillRect(1, 1, this.getWidth(), this.getHeight());
				c.rotate2D(45);
				c.translate(x + this.getWidth() / 2, y + this.getHeight() / 2);
			} */
		}
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
	public void performAction() {
		this.setActivated(!this.isActivated());
		Application.log("Set checkbox to " + Boolean.toString(this.isActivated()));
	}

	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
