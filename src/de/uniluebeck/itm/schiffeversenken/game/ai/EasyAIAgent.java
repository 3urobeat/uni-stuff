package de.uniluebeck.itm.schiffeversenken.game.ai;

import de.uniluebeck.itm.schiffeversenken.game.model.FieldTile;
import de.uniluebeck.itm.schiffeversenken.game.model.GameField;
import de.uniluebeck.itm.schiffeversenken.game.model.Ruleset;

import java.util.Random;

public class EasyAIAgent extends AIAgent {

    private FieldTile lastTile;

    public EasyAIAgent(int hardness) {
        super(hardness);
    }

    @Override
    public void setup(Ruleset r, GameField agentsField) {
        this.placeShipsAccordingToRules(r, agentsField);
    }

    @Override
    public boolean performMove(GameField playersField) {
        final Random rnd = new Random(System.currentTimeMillis());
        final int fieldWidth = playersField.getSize().getX();
        final int fieldHeight = playersField.getSize().getY();
        FieldTile tile;
        do {
            tile = playersField.getTileAt(rnd.nextInt(fieldWidth), rnd.nextInt(fieldHeight));
        } while (tile.wasAlreadyBombarded());
        this.lastTile = tile;
        return tile.bombard();
    }

    @Override
    public FieldTile getLastAttackedTile() {
        return this.lastTile;
    }
}
