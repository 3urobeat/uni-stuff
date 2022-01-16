package de.uniluebeck.itm.schiffeversenken.game;

import de.uniluebeck.itm.schiffeversenken.engine.AssetRegistry;
import de.uniluebeck.itm.schiffeversenken.engine.Tile;
import de.uniluebeck.itm.schiffeversenken.game.model.GameField;

public class HitMissRenderer extends GameFieldRenderer {

    public HitMissRenderer(GameField field) {
        super(field);
    }

    /**
     * Hide ships of enemy
     * @param x The x coordinate of the tile to look up
     * @param y The y coordinate of the tile to look up
     * @param waterTile A cached version for a water tile (will be the most returned one)
     * @param waterHitTile A cached version of the water_missed tile (Will be the second most returned one)
     * @return The correct tile to render
     */
    protected Tile getTileAt(int x, int y, Tile waterTile, Tile waterHitTile) {
        switch(this.getField().getTileAt(x, y).getTilestate()) {
            default:
                return AssetRegistry.getTile("unknown");
            case STATE_WATER:
            case STATE_SHIP:
                return waterTile;
            case STATE_MISSED:
                return waterHitTile;
            case STATE_SHIP_HIT:
                return AssetRegistry.getTile("water.hiddenshiphit");
        }
    }

}
