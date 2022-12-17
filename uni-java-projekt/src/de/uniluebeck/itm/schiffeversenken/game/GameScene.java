package de.uniluebeck.itm.schiffeversenken.game;

import de.uniluebeck.itm.schiffeversenken.engine.SmartScene;
import de.uniluebeck.itm.schiffeversenken.game.model.GameModel;

public class GameScene extends SmartScene<GameModel> {

    private final GameModel model;

    public GameScene(GameModel m) {
        super(new GameView(m), new GameController(m));
        this.model = m;
    }
}
