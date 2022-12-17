package de.uniluebeck.itm.schiffeversenken.game.ai;

import de.uniluebeck.itm.schiffeversenken.game.model.FieldTile;
import de.uniluebeck.itm.schiffeversenken.game.model.GameField;
import de.uniluebeck.itm.schiffeversenken.game.model.Ruleset;
import de.uniluebeck.itm.schiffeversenken.game.model.Ship;

import java.util.Random;

/**
 * Abstract class for doing AI stuff
 * @author modified by T. Goritz, L. Janßen
 */
public abstract class AIAgent {

    private final int hardness;

    public AIAgent(int hardness) {
        this.hardness = hardness;
    }

    /**
     * Use this method in order to initialize the agent. It needs to place his ships now.
     * @param r The rule set of the game.
     * @param agentsField The field belonging to the agent
     */
    public abstract void setup(Ruleset r, GameField agentsField);

    /**
     * Use this method in order to let the agent perform its move.
     * @param playersField The game field of the human player.
     * @return true if the agent hit something or otherwise false.
     */
    public abstract boolean performMove(GameField playersField);

    /**
     * Call this method from within a setup method in order to conviniently place your ships.
     * @param r The rule set to obey
     * @param f The field to place the ships on
     */
    protected void placeShipsAccordingToRules (Ruleset r, GameField f) {

        final int width = r.getGameFieldSize().getX();
        final int height = r.getGameFieldSize().getY();

        final int[] shipsToBePlaced = new int[] {
                r.getNumberOf1Ships(),
                r.getNumberOf2Ships(),
                r.getNumberOf3Ships(),
                r.getNumberOf4Ships(),
                r.getNumberOf5Ships()};

        final Random rnd = new Random(System.currentTimeMillis());

        final boolean keepDistance = r.getKeepDistance();

        for (int shipsLenghtIndex = 0; shipsLenghtIndex < shipsToBePlaced.length; shipsLenghtIndex++) {
            for (int ship = 0; ship < shipsToBePlaced[shipsLenghtIndex]; ship++) {
                while (!checkAndPlace(f, rnd.nextBoolean(), rnd.nextInt(width),
                        rnd.nextInt(height), shipsLenghtIndex + 1, width, height, keepDistance));
            }
        }
    }

    private boolean checkAndPlace(GameField f, boolean up, int x, int y, int length, int width, int height, boolean keepDistance) {
        if ((up && y + length > height) || (!up && x + length > width)) {
            return false;
        }

        for(int currentShipsX = x, currentShipsY = y, i = 0; i < length; i++) {
            final FieldTile t = f.getTileAt(currentShipsX, currentShipsY);
            if (t.getTilestate() != FieldTile.FieldTileState.STATE_WATER || t.getCorrespondingShip() != null) {
                return false;
            }
            if (up) {
                currentShipsY++;
            } else {
                currentShipsX++;
            }
        }

        //Check for keepDistance
        if (placeShipOnFieldKeepDistanceCheck(x, y, keepDistance, length, f, up)) return false; //don't place ship if distance was not kept

        final Ship shipToPlace = new Ship(length, up);
        f.placeShip(x, y, length, up, shipToPlace);
        return true;
    }

    /**
     * Checks for invalid ship placement if keepDistance is true
     * @param x The initial X coordinate of the ship
     * @param y The initial Y coordinate of the ship
     * @param keepDistance Result of r.getKeepDistance()
     * @param length The length of the ship to place
     * @param f The GameField
     * @param up Rotation of the ship to place
     * @return true if placement is illegal, false if ship can be placed
     */
    private boolean placeShipOnFieldKeepDistanceCheck(int x, int y, boolean keepDistance, int length, GameField f, boolean up) {
        if (!keepDistance) return false; //stop execution if distance is irrelevant

        //Check if the tiles besides the ship are occupied if keepDistance is true
        for (int currentShipsX = x, currentShipsY = y, i = 0; i < length; i++) {
            //above  (don't use ++ and -- here so that we don't modify the vars in the loop header)
            if (currentShipsY + 1 < f.getSize().getY()) {
                final FieldTile t = f.getTileAt(currentShipsX, currentShipsY + 1);
                if (t.getTilestate() != FieldTile.FieldTileState.STATE_WATER || t.getCorrespondingShip() != null) return true;
            }

            //right
            if (currentShipsX + 1 < f.getSize().getX()) {
                final FieldTile t = f.getTileAt(currentShipsX + 1, currentShipsY);
                if (t.getTilestate() != FieldTile.FieldTileState.STATE_WATER || t.getCorrespondingShip() != null) return true;
            }

            //below
            if (currentShipsY - 1 >= 0) {
                final FieldTile t = f.getTileAt(currentShipsX, currentShipsY - 1);
                if (t.getTilestate() != FieldTile.FieldTileState.STATE_WATER || t.getCorrespondingShip() != null) return true;
            }

            //left
            if (currentShipsX - 1 >= 0) {
                final FieldTile t = f.getTileAt(currentShipsX - 1, currentShipsY);
                if (t.getTilestate() != FieldTile.FieldTileState.STATE_WATER || t.getCorrespondingShip() != null) return true;
            }


            if (up) {
                currentShipsY++;
            } else {
                currentShipsX++;
            }
        }

        return false;
    }

    /**
     * This method must provide the tile that was previously attacked by the AI agent.
     * @return The last tile under fire.
     */
    public abstract FieldTile getLastAttackedTile();

    /**
     * This method is a getter for the hardness level of the AI
     * @return The current hardness level
     */
    public int getHardness() {
        return this.hardness;
    }
}
