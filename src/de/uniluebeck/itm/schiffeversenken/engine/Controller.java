/**
 * 
 */
package de.uniluebeck.itm.schiffeversenken.engine;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Instantiate this class in order to implement your user interaction logic.
 * 
 * @author leondietrich
 *
 */
public abstract class Controller<Model> {

	private final Model m;
	private final LinkedList<Runnable> waitingWork;
	private final ArrayList<Thread> activeWorkerThreads;

	/**
	 * This constructor doesn't do anything fancy beside initializing the model.
	 * 
	 * @param m The model to use.
	 */
	public Controller(Model m) {
		this.m = m;
		this.waitingWork = new LinkedList<Runnable>();
		this.activeWorkerThreads = new ArrayList<Thread>(Application.AVAIABLE_CPU_CORES);
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
	 * Use this method to check where the user clicked at your scene.
	 * 
	 * @param position The mouse cursor position relative to your scene.
	 */
	public abstract void clickedAt(Vec2 position);

	/**
	 * Use this method in order to receive newly pressed keys
	 * 
	 * @param key   The pressed key
	 * @param shift Was the shift modifier pressed?
	 * @param alt   Was the ALT modifier pressed?
	 * @param ctrl  Was the CTRL modifier pressed?
	 * @param down  Was the down arrow key pressed?
	 * @param up    Was the up arrow key pressed?
	 * @param left  Was the left arrow key pressed?
	 * @param right Was the right arrow key pressed?
	 */
	public abstract void keyPressed(int key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left, boolean right);

	/**
	 * This method gets called just before the view renders the scene. Use it to
	 * perform last minute updates but keep in mind that you're computing on the
	 * graphics thread. This means that your're better of delegating heavy workloads
	 * to the dispatchWork method.
	 */
	public abstract void performFrequentUpdates();

	/**
	 * Use this method in order to enqueue work to be processed later on. Don't use
	 * it for endless loops as they need to finish in finite time.
	 * 
	 * @param r The runnable that should be enqued.
	 */
	public void dispatchWork(Runnable r) {
		synchronized (this.waitingWork) {
			this.waitingWork.add(r);
		}
	}

	/**
	 * This method starts the processing of the dispatched work queue. It is save to
	 * call it in fast cycles as it wont create any threads as long as there are
	 * enough.
	 */
	public void startWorkStack() {
		for (int i = 0; i < waitingWork.size() - activeWorkerThreads.size()
				&& activeWorkerThreads.size() < Application.AVAIABLE_CPU_CORES; i++) {
			// Spawning additional worker Threads
			final Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					final Thread t = Thread.currentThread();
					// Perform work while there is some
					while (waitingWork.size() > 0) {
						final Runnable r;
						synchronized (waitingWork) {
							r = waitingWork.remove();
						}
						try {
							r.run();
						} catch (StackOverflowError e) {
							Application.crash(new RuntimeException(e));
						} catch (Exception e) {
							Application.crash(e);
						}
					}
					activeWorkerThreads.remove(t);
				}
			});
			t.setName("Worker thread #" + activeWorkerThreads.size() + " of controller " + this.toString());
			activeWorkerThreads.add(t);
			t.start();
		}
	}

	/**
	 * Use this method in order to check if the controller has an active (full) work queue.
	 * Keep in mind that this method will also return true if there is no work within the queue but threads are still
	 * processing older tasks.
	 *
	 * @return True if there is still unprocessed work to do.
	 */
	public final boolean hasWork() {
		return (!this.waitingWork.isEmpty()) || (!this.activeWorkerThreads.isEmpty());
	}

	/**
	 * This method gets called when the scene gets detached. Override it in order to
	 * stop your interfacing logic. But don't forget to call your super():
	 * 
	 * @throws InterruptedException
	 */
	public void stop() throws InterruptedException {
		startWorkStack();
		for (Thread t : this.activeWorkerThreads) {
			if(!t.equals(Thread.currentThread()))
				t.join();
		}
	}

	/**
	 * Use this method in order to prepare the controller for being rendered by a
	 * view.
	 */
	public abstract void prepare();
}
