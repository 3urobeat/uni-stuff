package de.uniluebeck.itm.schiffeversenken.engine;

/**
 * This class only purpose is to provide graphical output to your model.
 * 
 * @author leondietrich
 *
 */
public abstract class View<Model> {

	private final Model m;

	/**
	 * This constructor doesn't do anything fancy beside initializing the model
	 * 
	 * @param m The model to use.
	 */
	public View(Model m) {
		this.m = m;
	}

	/**
	 * Use this method in order to access the underlying model.
	 * 
	 * @return the model instance.
	 */
	public final Model getModelInstance() {
		return m;
	}

	/**
	 * Implement this method in order to draw your swag!
	 * 
	 * @param c The canvas. Trust me: you'll need this
	 * @param mouseLocation The location of the mouse cursor relative to the canvas.
	 */
	public abstract void render(Canvas c, Vec2 mouseLocation);

	/**
	 * Use this method in order to perform tasks prior to being rendered the first
	 * time.
	 */
	public abstract void prepare();
}
