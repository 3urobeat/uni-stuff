package de.uniluebeck.itm.schiffeversenken.game.model;

/**
 * This class represents a tile on a players field.
 * @author leondietrich
 *
 */
public final class FieldTile {

	private Ship correspondingShip = null;
	private FieldTileState tilestate = FieldTileState.STATE_WATER;



    public enum FieldTileState {
		/**
		 * Mark the tile as water. The ship field will be null.
		 */
		STATE_WATER,
		/**
		 * Mark the tile as bombarded but not hit. The ship field will be null.
		 */
		STATE_MISSED,
		/**
		 * Mark the tile as occupied by a ship but not bombarded. The ship field will
		 * contain a reference to a ship.
		 */
		STATE_SHIP,
		/**
		 * Mark the tile as occupied by a ship and bombarded. The ship field will
		 * contain a reference to a ship.
		 */
		STATE_SHIP_HIT
	}

	/**
	 * @return the correspondingShip
	 */
	public final Ship getCorrespondingShip() {
		return correspondingShip;
	}

	/**
	 * @param correspondingShip the correspondingShip to set
	 */
	public final void setCorrespondingShip(Ship correspondingShip) {
		this.correspondingShip = correspondingShip;
	}

	/**
	 * @return the tilestate
	 */
	public final FieldTileState getTilestate() {
		return tilestate;
	}

	/**
	 * @param tilestate the tilestate to set
	 */
	public final void setTilestate(FieldTileState tilestate) {
		this.tilestate = tilestate;
	}

	/**
	 * Use this method in order to bombard the tile.
	 * 
	 * @return True if it was a hit.
	 */
	public boolean bombard() {
		switch (this.tilestate) {
		case STATE_MISSED:
			return false;
		case STATE_SHIP:
			this.tilestate = FieldTileState.STATE_SHIP_HIT;
			this.getCorrespondingShip().hit();
			return true;
		case STATE_SHIP_HIT:
			return false;
		case STATE_WATER:
			this.tilestate = FieldTileState.STATE_MISSED;
			return false;
		}
		return false;
	}

	/**
	 * Use this method in order to check if the field was already hit.
	 * @return True if the tile state is STATE_SHIP_HIT or STATE_MISSED.
	 */
	public boolean wasAlreadyBombarded() {
		return this.tilestate == FieldTileState.STATE_SHIP_HIT || this.tilestate == FieldTileState.STATE_MISSED;
	}
}
