package de.uniluebeck.itm.schiffeversenken.game;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Controller;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;
import de.uniluebeck.itm.schiffeversenken.game.menues.EndOfGameMenu;
import de.uniluebeck.itm.schiffeversenken.game.model.FieldTile;
import de.uniluebeck.itm.schiffeversenken.game.model.GameModel;
import de.uniluebeck.itm.schiffeversenken.game.model.Ship;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handle UI interactions
 * @author modified by T. Goritz, L. Janßen
 */
public class GameController extends Controller<GameModel> {

    public GameController(GameModel m) {
        super(m);
    }

    @Override
    public void clickedAt(Vec2 mousePosition) {
        final GameModel model = this.getModelInstance();
        final Vec2 positionOnOpponentsField = mousePosition.add(model.getOpponentsFieldPosition().multiply(-1));
        final Vec2 opponentsFieldDimensions = model.getOpponentsFieldDimensions();

        // First of all we need to test if the player clicked within the field
        if(positionOnOpponentsField.getX() >= 0 && positionOnOpponentsField.getY() >= 0
                && positionOnOpponentsField.getX() < opponentsFieldDimensions.getX()
                && positionOnOpponentsField.getY() < opponentsFieldDimensions.getY()) {
            final int res = Constants.TILE_SIZE;

            final int tileX = positionOnOpponentsField.getX() / res;
            final int tileY = positionOnOpponentsField.getY() / res;
            Application.log("Bombarding position " + tileX + ", " + tileY);

            final FieldTile fieldTile = model.getComputerPlayerField().getTileAt(tileX, tileY);
            if (!fieldTile.wasAlreadyBombarded()) {
                if (fieldTile.bombard()) {

                    model.addPlayerPoints(Constants.POINTS_FOR_HIT); //add points if player hit something

                    final Ship thisShip = model.getAgent().getLastAttackedTile().getCorrespondingShip();
                    if (thisShip != null && thisShip.isSunken()) model.addPlayerPoints(Constants.POINTS_FOR_SHIP_SUNK); //add additional points if the ship is now sunken

                    handlePossibleGameEnd();

                } else {
                    model.setRoundChangingFlag(true);
                    this.dispatchWork(new Runnable() {

                        @Override
                        public void run() {
                            while (model.getAgent().performMove(model.getHumanPlayerField())) {
                                rewardAgentForDestroyingPlayer();
                            }
                        }

                        private void rewardAgentForDestroyingPlayer() {
                            model.addAiPoints(Constants.POINTS_FOR_HIT);

                            final Ship s = model.getAgent().getLastAttackedTile().getCorrespondingShip();
                            if (s != null && s.isSunken()) model.addAiPoints(Constants.POINTS_FOR_SHIP_SUNK); //add additional points if the ship is now sunken

                        }

                    });
                    this.startWorkStack();

                    handlePossibleGameEnd();
                }
            }
        }
    }

    /**
     * Helper function to determine if all ships are sunken
     * @param ships Array of all ships to check
     * @return true if all ships are sunken, otherwise false
     */
    private boolean allShipsSunken(Ship[] ships) {
        boolean result = true;

        //iterate over all ships and set result to false if at least one ship is not sunken yet
        for (Ship e : ships) {
            if (!e.isSunken()) result = false;
        }

        return result;
    }

    /**
     * Check if the game has ended
     */
    private void handlePossibleGameEnd() {
        final GameModel model = this.getModelInstance();

        if (allShipsSunken(model.getHumanPlayerField().getCopyOfShipListAsArray()) && allShipsSunken(model.getComputerPlayerField().getCopyOfShipListAsArray())) {
            endGame(allShipsSunken(model.getComputerPlayerField().getCopyOfShipListAsArray())); //player has won if all ships on computer's gamefield are sunken
        }
    }

    /**
     * Use this function in order to end the game and display the EndOfGameMenu.
     * @param playerWon Pass true if the player won and false if the computer won.
     */
    private void endGame(boolean playerWon) {
        Application.log("Ending game.");
        Application.switchToScene(new EndOfGameMenu(this.getModelInstance(), playerWon).getScene());
    }

    @Override
    public void keyPressed(int key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left, boolean right) {
        // Doesn't do anything anymore since we've dropped multiple resolutions
    }

    @Override
    public void performFrequentUpdates() {
        if (!this.hasWork() && this.getModelInstance().isRoundChanging()) {
            // We need to assume that the round changing work has finished.
            this.getModelInstance().increaseRoundCounter();
            this.getModelInstance().setRoundChangingFlag(false);
        }
    }

    @Override
    public void prepare() {

    }
}
