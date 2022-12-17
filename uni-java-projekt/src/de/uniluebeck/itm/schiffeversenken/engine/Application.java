package de.uniluebeck.itm.schiffeversenken.engine;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import de.uniluebeck.itm.schiffeversenken.engine.utilityscenes.CrashDialogScene;

/**
 * Application
 * 
 * this class contains the application boiler plate code to be run on a
 * graphical OS.
 * 
 * @author leondietrich
 *
 */
public final class Application {

	public static final int AVAIABLE_CPU_CORES = Runtime.getRuntime().availableProcessors();

	private static GUIContext context = null;
	private static Scene currentScene = null;
	private static boolean running = true;
	private static boolean showFPS = false;
	private static int fpsLimit = 60;

	public static void mainLoop() {
		long lastStartOfDrawing = System.currentTimeMillis() - 1;

		while (isStillRunning()) {
			try {
				final long now = System.currentTimeMillis();
				final Canvas c = context.getCanvas();
				synchronized (currentScene) {
					currentScene.update(now - lastStartOfDrawing);
					currentScene.draw(c, context.getMouseCursorLocation());
				}
				if (showFPS) {
					final double[] color = c.getColor();
					c.setColor(0.7, 0.7, 0.7, 0.7);
					final String fpsText = "FPS: " + Long.toString(1000 / (now - lastStartOfDrawing));
					final Vec2 textDimms = c.getTextDimensions(fpsText);
					c.fillRoundRect(10, 10, textDimms.getX() + 10, textDimms.getY() + 10, 5, 5);
					c.setColor(0, 0, 0);
					c.drawString(15, 15 + textDimms.getY(), fpsText);
				}
				context.performPaintOperation();
				final long sleepTime = 1000 / fpsLimit - (System.currentTimeMillis() - now);
				if (sleepTime < 1)
					Thread.sleep(1);
				else
					Thread.sleep(sleepTime);
				lastStartOfDrawing = now - 1;
			} catch (Exception e) {
				crash(e);
			}
		}
		context.destroy();
		log("Stopped.");
	}

	public static void setup(GUIContext c) {
		// TODO make constructor once extracted class
		context = c;
		context.setup();
		context.setInputDebugModeEnabled(true);
	}

	/**
	 * Use this function to check if the game is still running.
	 * 
	 * Please keep in Mind that a running application doesn't mean its healthy.
	 * 
	 * @return True if it wasn't stopped yet.
	 */
	public static boolean isStillRunning() {
		return running;
	}

	/**
	 * Call this method in order to halt the game application.
	 */
	public static void stopApplication() {
		log("Stopping application...");
		running = false;
	}

	/**
	 * Use this method in order to display a different scene.
	 * 
	 * @param s The new scene to show
	 */
	public static void switchToScene(Scene s) {
		if (currentScene != null) {
			currentScene.detach();
		} else {
			currentScene = s;
		}
		synchronized (currentScene) {
			currentScene = s;
			currentScene.attach();
			context.appendScene(currentScene);
		}
	}

	/**
	 * Use this method in order to log text to the screen.
	 * 
	 * @param s The string to output.
	 */
	public synchronized static void log(String s) {
		System.out.println(
				ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"))
						+ ": " + s);
	}

	/**
	 * Crash application in case of fire. Use this method in order to mark a crashed
	 * game state.
	 * 
	 * @param e The exception that caused the crash.
	 */
	public synchronized static void crash(Exception e) {
		if (e == null) {
			crash(new RuntimeException("The game was requested to crash but there was no exception provided"));
			return;
		}

		log("######Game crash encountered at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
				+ "######");
		e.printStackTrace();
		switchToScene(new CrashDialogScene(e));
	}

	/**
	 * @return the true if the current frame rate is drawn on the screen or
	 *         otherwise false.
	 */
	public static final boolean isFPSDisplayEnabled() {
		return showFPS;
	}

	/**
	 * Use this method to enable or disable the current FPS counter within the upper
	 * right corner.
	 * 
	 * @param showFPS true if the frame rate shall be rendered or otherwise false.
	 */
	public static final void setFPSDisplayEnabled(boolean showFPS) {
		Application.showFPS = showFPS;
	}

	/**
	 * Use this method in order to get the current contexts resolution. This may
	 * represent the actual rendering resolution and not the display or window
	 * resolution.
	 * 
	 * @return The resolution of the context.
	 */
	public static final Vec2 getCurrentResolution() {
		if (context == null) {
			return new Vec2(0, 0);
		}
		return context.getResolution();
	}

	/**
	 * Use this method in order to limit the current frame rate. This may be useful
	 * in scenarios where you don't need high refresh rates.
	 * 
	 * @param maxFPS the new fps limit to set.
	 */
	public static void setMaximumFPS(int maxFPS) {
		fpsLimit = maxFPS;
	}

	/**
	 * Use this method in order to get the current fps limit.
	 * 
	 * @return the current fps limit.
	 */
	public static int getCurrentMaximumFPS() {
		return fpsLimit;
	}

	/**
	 * Ask the gui context to load a tile for us.
	 * @param fileName The file to load the tile from.
	 * @return The loaded tile.
	 */
	public static Tile loadTile(String fileName) {
		return context.loadTile(fileName);
	}

}
