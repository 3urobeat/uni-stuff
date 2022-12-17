/**
 * 
 */
package de.uniluebeck.itm.schiffeversenken.engine.utilityscenes;

import java.util.ArrayList;

import de.uniluebeck.itm.schiffeversenken.engine.Application;
import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Scene;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This scene displays any exception that wasn't handled by the game and led to a crash.
 * @author leondietrich
 *
 */
public class CrashDialogScene extends Scene {

	private final Exception e;
	private final String[] stacktrace;

	public CrashDialogScene(Exception e) {
		super();
		this.e = e;
		
		ArrayList<String> calls = new ArrayList<String>();
		
		calls.add(e.getLocalizedMessage());
		for (StackTraceElement p : e.getStackTrace()) {
			StringBuilder sb = new StringBuilder();
			sb.append(p.getFileName());
			sb.append(':');
			sb.append(p.getLineNumber());
			sb.append(' ');
			sb.append(p.isNativeMethod() ? "N" : "J");
			calls.add(sb.toString());
			sb.append(' ');
			sb.append(p.getModuleName());
			sb.append('.');
			sb.append(p.getClassName());
			sb.append('.');
			sb.append(p.getMethodName());
		}
		this.stacktrace = calls.toArray(new String[calls.size()]);
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		final int base = Application.isFPSDisplayEnabled() ? 0: 20;
		c.setColor(1.0, 0, 0);
		c.drawString(5, 15 + base, "The game crashed. Press any key to exit or review the stack trace below:");
		c.drawLine(new Vec2(0, 20 + base), new Vec2(c.getResolutionWidth(), 20 + base));
		
		int y = 20 + base;
		
		for (String s : this.stacktrace) {
			y += 20;
			c.drawString(5, y, s);
		}
	}

	@Override
	public void attach() {}

	@Override
	public void detach() {}

	@Override
	public void clickedAt(Vec2 position) {
		Application.stopApplication();
	}

	@Override
	public void keyPressed(char key, boolean shift, boolean alt, boolean ctrl, boolean down, boolean up, boolean left, boolean right) {
		Application.stopApplication();
	}

	@Override
	public void update(long milis) {
	}

}
