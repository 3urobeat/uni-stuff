package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * This abstract class represents a tile that can be drawn at a given position.
 * The loading of tiles may happen within the appropriate GUIContext.
 *
 * @author leondietrich
 */
public abstract class Tile {

    /**
     * Use this method in order to render the tile at a given location on the canvas.
     *
     * @param c The canvas to draw the tile on.
     * @param position The position to render the tile at.
     */
    public abstract void renderAt(Canvas c, Vec2 position);
}
