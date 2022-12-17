package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.regex.Pattern;

/**
 * This class is a text box that only allows the input of text that matches the given mask pattern.
 *
 * @author leondietrich
 */
public class MaskedTextBox extends Textbox {

    private final Pattern mask;

    /**
     * Use this constructor in order to generate a new text box.
     *
     * @param defaultText The text to begin with (can be empty or null)
     * @param x           The x coordinate where the component should be placed.
     * @param y           The y coordinate where the component should be placed.
     * @param width       The width of the component.
     * @param height      The height of the component.
     * @param mask        The RegEx to use for the matching.
     */
    public MaskedTextBox(String defaultText, int x, int y, int width, int height, String mask) {
        super(defaultText, x, y, width, height);
        this.mask = Pattern.compile(mask);
    }

    @Override
    public void registerKeyInput(int key, boolean down, boolean up, boolean left, boolean right) {
        final String oldBufferContent = textBuffer.toString();
        super.registerKeyInput(key, down, up, left, right);
        if(!mask.matcher(textBuffer.toString()).matches()) {
            this.textBuffer = new StringBuilder();
            this.textBuffer.append(oldBufferContent);
            this.setCursorPosition(this.getCursorPosition() - 1);
        }
    }
}
