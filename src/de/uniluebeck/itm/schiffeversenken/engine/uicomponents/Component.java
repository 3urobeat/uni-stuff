package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This class represents a component shell. Don't let yourself be distracted by
 * this rather unconventional functionality split. Usually you have a
 * Model-View/Controller architecture. In this case (due to pedagogic thoughts)
 * we have a Model-View-Controller architecture. That's why this layout doesn't
 * look very familiar if you already are an experienced programmer.
 * 
 * @author leondietrich
 *
 */
public class Component {

	private Vec2 position;
	private int width;
	private int height;
	private boolean visible;
	private boolean enabled;

	public Component() {
		position = new Vec2(0, 0);
		width = 20;
		height = 10;
		visible = true;
		enabled = true;
	}

	/**
	 * Use this method in order to get the current components position.
	 * 
	 * @return the position of the component.
	 */
	public Vec2 getPosition() {
		return position;
	}

	/**
	 * Use this method in order to set a new position.
	 * 
	 * @param position the new position to set
	 */
	public void setPosition(Vec2 position) {
		this.position = position;
	}

	/**
	 * Use this method in order to get the current width of the component.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Use this method in order to modify the width of the component.
	 * 
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Use this method in order to obtain the current height of the component.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Use this method in order to set a new height.
	 * 
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Use this method to check if the component is visible.
	 * 
	 * @return the visibility
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Use this method to set the visibility to a new level.
	 * 
	 * @param visible true if the component should be visible or otherwise false.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Use this method to check if the component is enabled.
	 * 
	 * @return true if the component is enabled and otherwise false.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Use this method to enable or disable the component.
	 * 
	 * @param enabled true if the component should be enabled or otherwise false.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
