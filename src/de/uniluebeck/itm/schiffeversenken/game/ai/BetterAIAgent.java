package de.uniluebeck.itm.schiffeversenken.game.ai;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.game.model.FieldTile;
import de.uniluebeck.itm.schiffeversenken.game.model.GameField;
import de.uniluebeck.itm.schiffeversenken.game.model.Ruleset;

import java.util.Random;

/**
 * Improved AI for Hard difficulty
 * @author T. Goritz, L. Janßen
 */
public class BetterAIAgent extends AIAgent {

    private int thisX; //save current tile coords to be able to write them into lastX and lastY
    private int thisY;
    private int lastX; //save last checked tile coords
    private int lastY;
    private int triesToTheRight; //track steps to the right so that we can try to hit left of starting field if ship is not sunken yet but right is no hit anymore
    private int triesTowardsAbove; //same as above but for y axis
    private FieldTile lastTile;

    public BetterAIAgent(int hardness) {
        super(hardness);
    }

    @Override
    public void setup(Ruleset r, GameField agentsField) {
        Application.log("[BetterAI] So you want to die? Fasten your seatbelts, here we go.");
        this.placeShipsAccordingToRules(r, agentsField);
    }


    /**
     * Gets a random tile that hasn't been bombarded yet
     * @return
     */
    private FieldTile getRandomField(GameField playersField) {
        final Random rnd = new Random(System.currentTimeMillis());
        final int fieldWidth = playersField.getSize().getX();
        final int fieldHeight = playersField.getSize().getY();
        FieldTile tile;

        do {
            thisX = rnd.nextInt(fieldWidth);
            thisY = rnd.nextInt(fieldHeight);

            tile = playersField.getTileAt(thisX, thisY);
        } while (tile.wasAlreadyBombarded());

        return tile;
    }

    @Override
    /**
     * Big brain AI. It tries to hit adjacent fields when it hit a ship and tracks steps to one side to be able to go in the other direction and try more fields if we hit a water tile but the ship isn't sunken yet.
     *
     * What does it know more than the player? Is it cheating?
     * No, not really, even though AI's are allowed to "cheat" in my opinion, if it is balanced.
     * This AI only knows the orientation of a ship which the player doesn't know.
     * You can see if you sunk a ship by looking at the score counter as the player as well.
     */
    public boolean performMove(GameField playersField) {
        FieldTile tile;

        //Improve AI by trying to bombard adjacent fields if we previously hit a ship that is not sunken yet, otherwise try a random tile
        if (lastTile != null && lastTile.getTilestate() == FieldTile.FieldTileState.STATE_SHIP_HIT && !lastTile.getCorrespondingShip().isSunken()) {
            if (lastTile.getCorrespondingShip().isUp()) { //try Y axis
                if (lastY - 1 < 0) { //try below if at edge of play area, otherwise just always go above and only then retry below if no hit above
                    tile = playersField.getTileAt(lastX, lastY + 1);
                    thisX = lastX;
                    thisY = lastY + 1;
                    Application.log("[BetterAI] I hit! Above is OOB, trying field below...");
                } else {
                    tile = playersField.getTileAt(lastX, lastY - 1);
                    thisX = lastX;
                    thisY = lastY - 1;

                    if (tile.wasAlreadyBombarded()) {
                        if (lastY + 1 < playersField.getSize().getY()) { //don't try below of tile if we are near the edge
                            Application.log("[BetterAI] I already tried above, lets try below...");
                            tile = playersField.getTileAt(lastX, lastY + 1);
                            thisX = lastX;
                            thisY = lastY + 1;
                        } else {
                            Application.log("[BetterAI] I already tried above but below is OOB so we need to pick a random field :(");
                            tile = getRandomField(playersField);
                        }
                    } else {
                        Application.log("[BetterAI] I hit! Trying field above..."); //getTileAt for this msg is called above the if check
                        triesTowardsAbove++;
                    }
                }
            } else { //try x axis
                if (lastX + 1 >= playersField.getSize().getX()) { //try left if at edge of play area, otherwise just always go right and only then retry left if no hit on the right
                    tile = playersField.getTileAt(lastX - 1, lastY);
                    thisX = lastX - 1;
                    thisY = lastY;
                    Application.log("[BetterAI] I hit! Right is OOB, trying field left...");
                } else {
                    tile = playersField.getTileAt(lastX + 1, lastY);
                    thisX = lastX + 1;
                    thisY = lastY;

                    if (tile.wasAlreadyBombarded()) {
                        if (lastX - 1 > 0) { //don't try left of tile if we are near the edge
                            Application.log("[BetterAI] I already tried right, lets try left...");
                            tile = playersField.getTileAt(lastX - 1, lastY);
                            thisX = lastX - 1;
                            thisY = lastY;
                        } else {
                            Application.log("[BetterAI] I already tried right but left is OOB so we need to pick a random field :(");
                            tile = getRandomField(playersField);
                        }
                    } else {
                        Application.log("[BetterAI] I hit! Trying field right..."); //getTileAt for this msg is called above the if check
                        triesToTheRight++;
                    }
                }
            }
        } else {
            //Try to hit the left/below tile of the starting tile if last shot wasn't a hit but the ship is also not sunken yet (can't be OOB because then the ship would be sunken)
            FieldTile lastShipHitStartTile = playersField.getTileAt(lastX - triesToTheRight, lastY + triesTowardsAbove);

            if (lastTile != null && lastTile.getTilestate() == FieldTile.FieldTileState.STATE_MISSED && lastShipHitStartTile.getTilestate() == FieldTile.FieldTileState.STATE_SHIP_HIT && !lastShipHitStartTile.getCorrespondingShip().isSunken()) {
                if (lastShipHitStartTile.getCorrespondingShip().isUp()) {
                    Application.log("[BetterAI] I shot above of the last hit, didn't hit a ship but ship is also not sunken yet. Let's try below of the first hit...");
                    tile = playersField.getTileAt(lastX, lastY + triesTowardsAbove + 1);
                    thisX = lastX;
                    thisY = lastY + triesTowardsAbove + 1;
                } else {
                    Application.log("[BetterAI] I shot right of the last hit, didn't hit a ship but ship is also not sunken yet. Let's try left of the first hit...");
                    tile = playersField.getTileAt(lastX - triesToTheRight - 1, lastY);
                    thisX = lastX - triesToTheRight - 1;
                    thisY = lastY;
                }
            } else {
                if ((lastTile != null && lastTile.getTilestate() == FieldTile.FieldTileState.STATE_SHIP_HIT && lastTile.getCorrespondingShip().isSunken()) || (lastShipHitStartTile.getTilestate() == FieldTile.FieldTileState.STATE_SHIP_HIT && lastShipHitStartTile.getCorrespondingShip().isSunken())) Application.log("[BetterAI] My last hit sunk the ship! Going back to trying a random field...");
                    else Application.log("[BetterAI] Trying a random field...");
                tile = getRandomField(playersField);
                triesToTheRight = 0;
                triesTowardsAbove = 0;
            }
        }


        lastX = thisX;
        lastY = thisY;
        lastTile = tile;
        Application.log("[BetterAI] Bombarding position " + thisX + ", " + thisY);
        return tile.bombard();
    }

    @Override
    public FieldTile getLastAttackedTile() {
        return this.lastTile;
    }
}
