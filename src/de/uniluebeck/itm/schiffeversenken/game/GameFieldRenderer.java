package de.uniluebeck.itm.schiffeversenken.game;

import de.uniluebeck.itm.schiffeversenken.engine.*;
import de.uniluebeck.itm.schiffeversenken.game.model.*;

/**
 * Game field rendering methods.
 *
 * It only contains methods designated to rendering a game field. In future it will also provide scroll bars in case the
 * field doesn't fit on the canvas.
 *
 * @author modified by T. Goritz, L. Janßen
 *
 */
public class GameFieldRenderer {

    private final GameField field;

    public GameFieldRenderer(GameField field) {
        this.field = field;
    }

    /**
     * Use this method in order to render a game field.
     *
     * @param c The canvas to render on.
     * @param x The x coordinate where to render the field.
     * @param y The y coordinate where to render the field.
     */
    public void renderGameField(Canvas c, int x, int y) {
        final int tileSize = Constants.TILE_SIZE;

        // Look up the default water tiles
        final Tile waterTile = AssetRegistry.getTile("water");
        final Tile waterMissed = AssetRegistry.getTile("water.hit");

        // draw the fields tiles
        for (int tileX = 0; tileX < this.field.getSize().getX(); tileX++) {
            for (int tileY = 0; tileY < this.field.getSize().getY(); tileY++) {
                final Vec2 tilePosition = new Vec2(x + tileX * tileSize, y + tileY * tileSize);
                getTileAt(tileX, tileY, waterTile, waterMissed).renderAt(c, tilePosition);
            }
        }
        
        final int width = tileSize * this.field.getSize().getX();
        final int height = tileSize * this.field.getSize().getY();


        //Draw borders between each row/column of fields
        c.setColor(0, 0, 0); //set color of lines to draw

        for (int posX = x; posX < width - tileSize; posX += tileSize) { //iterate over x axis
            c.drawLine(new Vec2(posX + tileSize, y), new Vec2(posX + tileSize, height + y)); //draw line for y axis on this x coordinate
        }

        for (int posY = y; posY < height; posY += tileSize) { //iterate over y axis
            c.drawLine(new Vec2(x, posY + tileSize), new Vec2(width + x, posY + tileSize)); //draw line for x axis on this y coordinate
        }


        //Draw a border around the canvas
        c.setColor(1, 1, 1); //set color of lines to draw

        c.drawLine(new Vec2(x, y), new Vec2(x, height + y));
        c.drawLine(new Vec2(x, height + y), new Vec2(width + x, height + y));
        c.drawLine(new Vec2(width + x, height + y), new Vec2(width + x, y));
        c.drawLine(new Vec2(width + x, y), new Vec2(x, y));
    }

    /**
     * This method gets called by the rendering method in order to look up the correct tile at a given position.
     * @param x The x coordinate of the tile to look up
     * @param y The y coordinate of the tile to look up
     * @param waterTile A cached version for a water tile (will be the most returned one)
     * @param waterHitTile A cached version of the water_missed tile (Will be the second most returned one)
     * @return The correct tile to render
     */
    protected Tile getTileAt(int x, int y, Tile waterTile, Tile waterHitTile) {
        switch(this.field.getTileAt(x, y).getTilestate()) {
            default:
                return AssetRegistry.getTile("unknown");
            case STATE_WATER:
                return waterTile;
            case STATE_MISSED:
                return waterHitTile;
            case STATE_SHIP:
                return lookupShipTile(x, y, false);
            case STATE_SHIP_HIT:
                return lookupShipTile(x, y, true);
        }
    }

    /**
     * This method looks up the correct tile if the position happens to be a ship.
     * @param x The x coordinate of the ships tile
     * @param y The y coordinate of the ships tile
     * @param alreadyHit A Flag that indicates whether nor not the tile has been hit yet.
     * @return The composed ships tile
     */
    private Tile lookupShipTile(int x, int y, boolean alreadyHit) {
        boolean above = false; //initialize booleans for checking neighbor fields for the same ship
        boolean below = false;
        boolean left  = false;
        boolean right = false;

        Ship ship = this.field.getTileAt(x, y).getCorrespondingShip(); //get the corresponding ship for this tile

        //Above - Check if tile is out of bounds and then if the ship instances match
        if (y - 1 > 0 && this.field.getTileAt(x, y - 1).getCorrespondingShip() == ship) above = true;

        //Below
        if (this.field.getSize().getY() > y + 1 && this.field.getTileAt(x, y + 1).getCorrespondingShip() == ship) below = true;

        //Left
        if (x - 1 > 0 && this.field.getTileAt(x - 1, y).getCorrespondingShip() == ship) left = true;

        //Right
        if (this.field.getSize().getX() > x + 1 && this.field.getTileAt(x + 1, y).getCorrespondingShip() == ship) right = true;


        //Return correct texture for each case
        String returnTexture = "";

        if (!above && !below && !left && !right) returnTexture = (ship.isUp()) ? "up.ship.single" : "right.ship.single"; //https://www.javatpoint.com/ternary-operator-in-java

        if ( above &&  below && !left && !right) returnTexture = "up.ship.middle";
        if (!above &&  below && !left && !right) returnTexture = "up.ship.bug";
        if ( above && !below && !left && !right) returnTexture = "up.ship.aft";
        if (!above && !below &&  left &&  right) returnTexture = "right.ship.middle";
        if (!above && !below &&  left && !right) returnTexture = "right.ship.bug";
        if (!above && !below && !left &&  right) returnTexture = "right.ship.aft";

        if (alreadyHit) returnTexture += ".hit"; //add a .hit to the texture path if the field is already hit

        return AssetRegistry.getTile(returnTexture);
    }

    
    /**
     * The purpose of this method is to enable the usage of the game field on expanding classes.
     * @return The game field
     */
    protected GameField getField() {
        return this.field;
    }

}
