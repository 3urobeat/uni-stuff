package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.concurrent.Semaphore;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This class represents a text box. This is a component where the user can input
 * text.
 * 
 * @author leondietrich
 *
 */
public class Textbox extends Component implements Drawable, TextInput {

	protected final Semaphore bufferMutex;

	protected StringBuilder textBuffer;
	private boolean cursorEnabled;
	private int cursorPosition;

	/**
	 * Use this constructor in order to generate a new text box.
	 * 
	 * @param defaultText The text to begin with (can be empty or null)
	 * @param x           The x coordinate where the component should be placed.
	 * @param y           The y coordinate where the component should be placed.
	 * @param width       The width of the component.
	 * @param height      The height of the component.
	 */
	public Textbox(String defaultText, int x, int y, int width, int height) {
		super();
		this.setPosition(new Vec2(x, y));
		this.setWidth(width);
		this.setHeight(height);
		this.setText(defaultText);
		this.cursorEnabled = false;
		this.bufferMutex = new Semaphore(1);
	}

	/**
	 * This setter sets the new text and puts the cursor position at its end.
	 * 
	 * @param text The new text to set
	 */
	public void setText(String text) {
		// We can't manipulate a string directly as they're immutable and the user
		// changing the content of the box would be a huge (well small as humans are
		// lazy, but still a) memory leak.
		this.textBuffer = new StringBuilder();
		if (text != null)
			this.textBuffer.append(text);
		this.cursorPosition = this.textBuffer.length();
	}

	/**
	 * Use this method in order to get the content of the text buffer.
	 * 
	 * @return The current text
	 */
	public String getText() {
		return this.textBuffer.toString();
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		try {
			this.bufferMutex.acquire();
			try {
				final int x = this.getPosition().getX(), y = this.getPosition().getY();
				final int width = this.getWidth();
				final String text = this.getCurrentTextBuffer();
				c.setColor(0.7, 0.7, 0.7);
				c.fillRect(x, y, width, this.getHeight());
				c.setColor(0, 0, 0);
				int startIndex = 0;
				int preTextLenght = c.getTextDimensions(text.substring(startIndex, this.cursorPosition)).getX();
				int postTextLenght = c.getTextDimensions(text.substring(this.cursorPosition)).getX();
				final int textHeight = c.getTextDimensions("Gg").getY();
				while (preTextLenght > width - 10) {
					startIndex++;
					preTextLenght = c.getTextDimensions(text.substring(startIndex, this.cursorPosition)).getX();
				}
				final String preText = text.substring(startIndex, cursorPosition);
				int xOffset = 3;
				c.drawString(x + xOffset, y + textHeight, preText);
				xOffset += c.getTextDimensions(preText).getX() + 1;
				if (this.cursorEnabled) {
					if ((System.currentTimeMillis() / 1000) % 2 == 0)
						c.drawLine(new Vec2(x + xOffset, y + 2), new Vec2(x + xOffset, y + this.getHeight() - 2));
					xOffset += 2;
				}
				int endIndex = text.length();
				while (postTextLenght > width - 2 - xOffset) {
					endIndex--;
					postTextLenght = c.getTextDimensions(text.substring(this.cursorPosition, endIndex)).getX();
				}
				c.drawString(x + xOffset, y + textHeight, text.substring(cursorPosition, endIndex));
			} finally {
				this.bufferMutex.release();
			}
		} catch (InterruptedException e) {
			Application.log("Semaphore aquisition in rendering task was interrupted. Aborting Textbox rendering.");
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void notifyActivationStateChanged(boolean newState) {
		this.cursorEnabled = newState;
	}

	@Override
	public boolean getOwnActiveState() {
		return this.cursorEnabled;
	}

	@Override
	public void registerKeyInput(int key, boolean down, boolean up, boolean left, boolean right) {
		try {
			this.bufferMutex.acquire();
			try {
				if (left) {
					this.cursorPosition--;
					checkCursorConstrains();
					return;
				}

				if (right) {
					this.cursorPosition++;
					checkCursorConstrains();
					return;
				}

				if (key == 0x8) {
					// backspace
					if (this.cursorPosition > 0) {
						this.textBuffer.deleteCharAt(cursorPosition - 1);
						this.cursorPosition--;
						checkCursorConstrains();
					}
				} else if (key == 0x7F) {
					// delete
					if (this.textBuffer.length() > 0) {
						this.textBuffer.deleteCharAt(cursorPosition);
						checkCursorConstrains();
					}
				} else if (key == 0xFFFF) {
					// Some window manager send in this key if a key event occured
					// but no key was pressed yet. We'll simply ignore this and hope
					// That no Keyboard that actually has this key will ever
					// arrive on the market.
					return;
				} else {
					this.textBuffer.insert(cursorPosition, (char) key);
					this.cursorPosition++;
				}
			} finally {
				this.bufferMutex.release();
			}
		} catch (InterruptedException e) {
			Application.log("Failed to aquire buffer mutex for Textbox.");
			return;
		}
	}

	private void checkCursorConstrains() {
		if (this.cursorPosition < 0) {
			this.cursorPosition = 0;
		}
		if (this.cursorPosition > this.textBuffer.length()) {
			this.cursorPosition = this.textBuffer.length();
		}
	}

	/**
	 * Use this method in a sub class in order to retrieve the cursor position
	 * 
	 * @return The current cursor position
	 */
	protected int getCursorPosition() {
		return this.cursorPosition;
	}

	/**
	 * Use this method in order to set the cursor position.
	 * 
	 * @param newPosition The new position to set.
	 */
	protected void setCursorPosition(int newPosition) {
		this.cursorPosition = newPosition;
		this.checkCursorConstrains();
	}

	@Override
	public String getCurrentTextBuffer() {
		return this.getText();
	}

}
