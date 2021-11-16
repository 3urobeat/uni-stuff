package de.uniluebeck.itm.schiffeversenken.game.model;

/**
 * This class represents a ship.
 * @author leondietrich, modified by I. Schumacher & T. Goritz
 *
 */
public class Ship {

	//Create three private variables for storing
	private int length;
	private int hits;
	private boolean orientation;
	
	/**
	 * This constructor constructs a new ship.
	 * @param lenght The length of the ship to create.
	 * @param orientation The orientation of the ship: true for vertical, false for horizontal
	 */
	public Ship(int length, boolean orientation) {
		this.length      = length;
		this.hits        = 0;
		this.orientation = orientation;
	}
	
	/**
	 * Add a hit to the ship if it isn't sunken already
	 */
	public void hit() {
		if (!isSunken()) hits++;
	}

	/**
	 * Helper method to check if ship is sunken
	 * @return boolean
	 */
	public boolean isSunken() {
		return false;
	}

	/**
	 * Return the orientation (which is the same like the private orientation variable lol)
	 * @return boolean
	 */
	public boolean isUp() {
		return orientation;
	}

}
