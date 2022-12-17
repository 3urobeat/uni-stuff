package de.uniluebeck.itm.schiffeversenken.main;

import de.uniluebeck.itm.schiffeversenken.engine.AWTGUIContext;
import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.game.Constants;
import de.uniluebeck.itm.schiffeversenken.game.menues.LoadingScene;

public class Main {
    /**
     * The classic main function.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.setup(new AWTGUIContext("Schiffeversenken", Constants.SCREEN_WIDTH,
        		Constants.SCREEN_HEIGHT));

        Application.switchToScene(new LoadingScene());

        Application.mainLoop();
        System.exit(0);
    }
}
