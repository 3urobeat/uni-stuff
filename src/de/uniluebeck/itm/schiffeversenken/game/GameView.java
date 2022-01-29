package de.uniluebeck.itm.schiffeversenken.game;

import de.uniluebeck.itm.schiffeversenken.engine.AssetRegistry;
import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;
import de.uniluebeck.itm.schiffeversenken.engine.View;
import de.uniluebeck.itm.schiffeversenken.game.model.GameModel;

/**
 * Rendering stuff
 * @author modified by T. Goritz, L. Janßen
 */
public class GameView extends View<GameModel> {

    private final GameFieldRenderer fieldRenderer;
    private final HitMissRenderer opponentFieldRenderer;

    public GameView(GameModel m) {
        super(m);
        this.fieldRenderer = new GameFieldRenderer(this.getModelInstance().getHumanPlayerField());
        this.opponentFieldRenderer = new HitMissRenderer(this.getModelInstance().getComputerPlayerField());
    }

    @Override
    public void render(Canvas c, Vec2 mouseLocation) {
        final int frameWidth = c.getResolutionWidth(), frameHeight = c.getResolutionHeight();
        final int offsetX = 10, offsetY = 35;

        final GameModel model = this.getModelInstance();
        final Vec2 gameFieldDimensions = model.getHumanPlayerField().getSize(); 

        final int fieldsWidth = gameFieldDimensions.getX() * Constants.TILE_SIZE;
        final int fieldsHeight = gameFieldDimensions.getX() * Constants.TILE_SIZE;
        final int opponentsFieldX = offsetX + fieldsWidth + 10;
        this.fieldRenderer.renderGameField(c, offsetX, offsetY);
        this.opponentFieldRenderer.renderGameField(c, opponentsFieldX, offsetY);
        model.updateOpponentsFieldOnScreenData(new Vec2(opponentsFieldX, offsetY),
                new Vec2(fieldsWidth, fieldsHeight));

        //Render tile overlay the mouse is hovering above
        opponentFieldRenderer.renderMouseOver(c, mouseLocation.getX(), mouseLocation.getY(), opponentsFieldX, offsetY);

        c.setColor(0.7, 0.7, 0.7);
        c.drawRoundRect(frameWidth - 280 - offsetX, offsetY, 280, frameHeight - offsetY - 100, 5, 5);

        final int[] numbers = new int[]{model.getRoundCounter(), model.getPlayerPoints(), model.getAiPoints()};
        final String[] labels = new String[]{"Round: ", "Your points", "Computers points"};
        for (int i = 0; i < numbers.length; i++) {
            // Irgendwie muss man da noch dinge mit dem Offset machen...
            draw7segNumberAt(c, frameWidth - offsetX - 45,
                    offsetY + 25 + (i * 50), numbers[i]);
            c.drawString(frameWidth - 270 - offsetX, offsetY + 35 + (i * 50), labels[i]);
        }


        if(model.isRoundChanging()) {
            c.setColor(0.7, 0.7, 0.7, 0.7);
            c.fillRect(0, 0, frameWidth, frameHeight);
            c.setColor(0, 0, 0);
            final String text = "Round changing. Please wait for the AI to destroy you.";
            final Vec2 textDim = c.getTextDimensions(text);
            c.drawString(frameWidth / 2 - textDim.getX() / 2, frameHeight / 2 - textDim.getY() / 2, text);
        }
    }

    /**
     * Draws a 7 segment display number at a specified location
     * @param c The canvas to render on
     * @param x x coordinate of the location to draw at
     * @param y y coordinate of the location to draw at
     * @param number The number to draw
     */
    private void draw7segNumberAt(Canvas c, int x, int y, int number) {
        int currentPosX = x;
        int width       = 26; //width and height of the grey rectangle appearing behind the 7seg digits
        int height      = 44;

        //Convert number to String, split at each digit and iterate over each digit
        String[] sStr = Integer.toString(number).split("");

        for (int i = sStr.length - 1; i >= 0; i--) { //iterate starting from the back so that the number doesn't appear "mirrored"
            c.setColor(1, 1, 1, 0.5); //create some sort of grey
            c.fillRect(currentPosX, y, width, height); //render background

            AssetRegistry.getTile("7seg." + sStr[i]).renderAt(c, new Vec2(currentPosX + 3, y + 3)); //+3 to appear in the middle of the grey rectangle

            currentPosX -= width; //subtract width so that the next number appears on the left of this digit
        }
    }

    @Override
    public void prepare() {

    }
}
