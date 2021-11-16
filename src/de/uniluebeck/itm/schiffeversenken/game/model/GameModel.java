package de.uniluebeck.itm.schiffeversenken.game.model;

import de.uniluebeck.itm.schiffeversenken.engine.Vec2;
import de.uniluebeck.itm.schiffeversenken.game.ai.AIAgent;

public class GameModel{
    private final GameField humanPlayerField;
    private final GameField computerPlayerField;
    private final Ruleset rules;
    private final AIAgent agent;

    private final String playerName;
    private final String computerName;

    private Vec2 opponentsFieldPosition, opponentsFieldDimensions;
    private int roundCounter;
    private boolean changingRound;

    private int playerPoints;
    private int aiPoints;

    public GameModel(GameField humanPlayerField, GameField computerPlayerField, Ruleset rules, AIAgent agent, String playerName, String computerName) {
        this.humanPlayerField = humanPlayerField;
        this.computerPlayerField = computerPlayerField;
        this.rules = rules;
        this.agent = agent;
        this.playerName = playerName;
        this.computerName = computerName;
        this.roundCounter = 0;
        this.changingRound = false;
        this.opponentsFieldPosition = new Vec2(0, 0);
        this.opponentsFieldDimensions = new Vec2(-1, -1);
        this.playerPoints = 0;
        this.aiPoints = 0;
    }

    public GameField getHumanPlayerField() {
        return humanPlayerField;
    }

    public GameField getComputerPlayerField() {
        return computerPlayerField;
    }

    public Ruleset getRules() {
        return rules;
    }

    public AIAgent getAgent() {
        return agent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getComputerName() {
        return computerName;
    }

    /**
     * This method increases the round counter by 1.
     */
    public void increaseRoundCounter() {
        this.roundCounter++;
    }

    /**
     * Use this getter in order to get the current round counter value.
     * @return The current round number.
     */
    public int getRoundCounter() {
        return this.roundCounter;
    }

    /**
     * Use this method in order to change the round changing flag status.
     * @param newValue true if the round is currently changing or otherwise false.
     */
    public void setRoundChangingFlag(boolean newValue) {
        this.changingRound = newValue;
    }

    /**
     * Use this method in order to check if the round is currently changing.
     * @return True if it is or otherwise false.
     */
    public boolean isRoundChanging() {
        return this.changingRound;
    }

    /**
     * Use this method in order to update the opponents game field visualization data.
     * @param position The new position to set
     * @param dimensions The new dimensions to set
     */
    public void updateOpponentsFieldOnScreenData(Vec2 position, Vec2 dimensions) {
        this.opponentsFieldDimensions = dimensions;
        this.opponentsFieldPosition = position;
    }

    /**
     * This method is a getter for the opponents game field position.
     * @return The rendered position on screen.
     */
    public Vec2 getOpponentsFieldPosition() {
        return this.opponentsFieldPosition;
    }

    /**
     * This method is a getter for the opponents game field dimensions.
     * @return The actual dimensions of the field on screen.
     */
    public Vec2 getOpponentsFieldDimensions() {
        return this.opponentsFieldDimensions;
    }

    /**
     * Use this method in order to get the points of the human player.
     * @return The amount of points the player has.
     */
    public int getPlayerPoints() {
        return this.playerPoints;
    }

    /**
     * Use this method in order to increase the amount of points the human player has.
     * @param points The points to add
     */
    public void addPlayerPoints(int points) {
        this.playerPoints += points;
    }

    /**
     * Use this method in order to get the points of the AI.
     * @return The amount of points the AI has.
     */
    public int getAiPoints() {
        return this.aiPoints;
    }

    /**
     * Use this method in order to increase the amount of points the AI player has.
     * @param points The points to add
     */
    public void addAiPoints(int points) {
        this.aiPoints += points;
    }
}
