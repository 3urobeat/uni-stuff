package de.uniluebeck.itm.schiffeversenken.game.menues;

import de.uniluebeck.itm.schiffeversenken.engine.*;
import de.uniluebeck.itm.schiffeversenken.game.model.GameModel;

import de.uniluebeck.itm.schiffeversenken.engine.uicomponents.*;

import java.util.Map;

public class EndOfGameMenu {

    private final SmartScene<ComponentModel> scene;

    public EndOfGameMenu(GameModel gameModel, boolean playerWon) {
        final int middleX = Application.getCurrentResolution().getX() / 2;

        final ComponentModel m = new ComponentModel(Map.of(
                "label.winMarker", new SimpleLabel((playerWon ? gameModel.getPlayerName() : gameModel.getComputerName()) + "won", middleX - 50, 50),
                "label.playerPoints", new SimpleLabel("Your points: " + gameModel.getPlayerPoints(), middleX - 250, 70),
                "label.aiPoints", new SimpleLabel("Your opponents points: " + gameModel.getAiPoints(), middleX + 50, 70),
                "button.revenge", new Button("Get revenge", middleX - 250, 250, 200, 50) {
                    @Override
                    public void performAction() {
                        Application.switchToScene(new ShipPlacementMenuScene(gameModel.getRules(),
                                new String[]{gameModel.getPlayerName(), gameModel.getComputerName()},
                                gameModel.getAgent().getHardness(), null));
                    }
                },
                "button.toMainMenu", new Button("To the main menu", middleX + 50, 250, 200, 50) {
                    @Override
                    public void performAction() {
                        Application.switchToScene(new MainMenu().getScene());
                    }
                }));

        final View<ComponentModel> v = new ComponentView(m);
        final ComponentController c = new ComponentController(m) {

            @Override
            public void performFrequentUpdates() {
            }
        };

        this.scene = new SmartScene<>(v, c);
    }

    public SmartScene<ComponentModel> getScene() {
        return this.scene;
    }
}
