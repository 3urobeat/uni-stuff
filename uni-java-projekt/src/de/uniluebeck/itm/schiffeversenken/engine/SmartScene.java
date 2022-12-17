package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * While a scene object is a low level interface to the graphics stack it lacks
 * some convenience. Most notably it isn't split into a View and Controller.
 * This intermediate class aims to fix this issue.
 * 
 * @author leondietrich
 *
 */
public class SmartScene<Model> extends Scene {

	private final View<Model> v;
	private final Controller<Model> c;
	
	public SmartScene(View<Model> v, Controller<Model> c) {
		this.v = v;
		this.c = c;
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		this.v.render(c, mouseLocation);
	}

	@Override
	public void attach() {
		this.v.prepare();
		this.c.prepare();
	}

	@Override
	public void update(long milis) {
		this.c.performFrequentUpdates();
		this.c.startWorkStack();
	}

	@Override
	public void detach() {
		try {
			this.c.stop();
		} catch (InterruptedException e) {
			Application.log("WARNING: stopping the controller took too long. It got interrupted:");
			e.printStackTrace();
		}
	}

	@Override
	public void clickedAt(Vec2 position) {
		this.c.clickedAt(position);
	}

	@Override
	public void keyPressed(char key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left, boolean right) {
		this.c.keyPressed(key, shift, alt, ctrl, down, up, left, right);
	}

	/**
	 * Use this method in order to get the view of this scene.
	 * @return The attached View
	 */
	public View<Model> getView() {
		return this.v;
	}

	/**
	 * Use this method in order to get the attached controller.
	 * @return The controller.
	 */
	public Controller<Model> getController() {
		return this.c;
	}

}
