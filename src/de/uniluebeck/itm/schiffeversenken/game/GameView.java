package de.uniluebeck.itm.schiffeversenken.game;

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

    private void draw7segNumberAt(Canvas c, int x, int y, int number) {
        c.drawString(x, y, Integer.toString(number));
        // TODO implement a real (well sort of, it's still inside a computer) 7 segment display
    }

    @Override
    public void prepare() {

    }
}
