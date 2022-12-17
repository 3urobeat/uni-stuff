/**
 * 
 */
package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * @author leondietrich
 *
 */
public class SimpleLabel extends Component implements Drawable {
	
	final String text;

	/**
	 * Use this constructor in order to generate a new Label.
	 * @param text The text to display.
	 * @param x The x position where the text should be drawn
	 * @param y The y position where the text should be drawn.
	 */
	public SimpleLabel(String text, int x, int y) {
		super();
		this.setPosition(new Vec2(x, y));
		this.text = text;
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		if(!this.isVisible())
			return;
		final int x = this.getPosition().getX(), y = this.getPosition().getY();
		c.setColor(0.7, 0.7, 0.7, 0.7);
		final Vec2 textDimms = c.getTextDimensions(this.text);
		c.fillRoundRect(x, y, textDimms.getX() + 10, textDimms.getY() + 10, 5, 5);
		c.setColor(0, 0, 0);
		c.drawString(x + 5, y + 5 + textDimms.getY(), this.text);
	}

}
