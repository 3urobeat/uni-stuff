package de.uniluebeck.itm.schiffeversenken.engine;

import java.util.HashMap;

/**
 * This class holds all your assets.
 *
 * @author leondietrich
 */
public class AssetRegistry {

    private static final HashMap<String, Tile> tileMap;
    private static final Tile defaultMissingTile;


    static {
        tileMap = new HashMap<String, Tile>();
        defaultMissingTile = Application.loadTile("assets/missing_tile.png");
        registerTile("unknown", defaultMissingTile);
    }

    /**
     * Use this method in order to register a tile.
     * @param key The key to use for registration
     * @param t The tile to register
     */
    public static final void registerTile(String key, Tile t) {
        tileMap.put(key, t);
    }

    /**
     * Use this method in order to obtain a registered Tile.
     * It may return a place holder tile in case the requested tile isn't registered (yet?).
     *
     * @param key The key to use for the lookup.
     * @return The requested tile or a place holder one (in case of a failed lookup).
     */
    public static final Tile getTile(String key) {
        final Tile f = tileMap.get(key);
        return f != null ? f : defaultMissingTile;
    }
}
