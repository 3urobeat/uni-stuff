package de.uniluebeck.itm.schiffeversenken.engine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class implements an GUI adapter to Java/AWT
 * 
 * @author leondietrich
 *
 */
public final class AWTGUIContext extends GUIContext {

	private final JPanel panel;
	private final JFrame frame;
	private Canvas c;
	private BufferedImage bufferedImage;

	private Scene s;

	private boolean painting = false;

	public AWTGUIContext(String windowtitle, int windowWidth, int windowHeight) {
		super(windowtitle);

		// In some cases this transparently enables hardware acceleration for canvas
		// rendering.

		// Use with caution: Sometimes this will do magical things for your performance,
		// sometimes it doesn't seam to do anything and sometimes it breaks your
		// rendering completely.
		// System.setProperty("sun.java2d.opengl", "true");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Application.log("Error: unable to set the system look and feel:\n" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		this.frame = new JFrame(windowtitle);
		
		if (System.getProperty("sun.java2d.opengl", "false").equals("true")) {
			this.frame.createBufferStrategy(2);
			final BufferStrategy strategy = this.frame.getBufferStrategy();
		}
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
				if (isInputDebuggingEnabled()) {
					Application.log("INPUT: Received window closing event.");
				}
				Application.stopApplication();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
		});

		this.panel = new JPanel() {
			private static final long serialVersionUID = -6104205382310524761L;

			@Override
			public void paintComponent(Graphics g) {
				try {
					if (!g.drawImage(bufferedImage, 0, 0, null)) {
						Application.log("The frame couldn't be draw in time");
					}
					painting = false;
				} catch (Exception e) {
					Application.crash(e);
				}
			}
		};
		this.panel.setLayout(null);

		frame.add(this.panel, BorderLayout.CENTER);
		this.setResolution(windowWidth, windowHeight);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					final int keyCode = arg0.getKeyCode();
					if (isInputDebuggingEnabled()) {
						Application.log("INPUT: Key event: " + keyCode + " (" + arg0.getKeyChar() + ", 0x"
								+ Integer.toHexString(arg0.getKeyChar()) + ") Shift: " + arg0.isShiftDown() + " Alt: "
								+ Boolean.toString(arg0.isAltDown() || arg0.isAltGraphDown()) + " CTRL: "
								+ arg0.isControlDown());
					}
					s.keyPressed(arg0.getKeyChar(), arg0.isShiftDown(), arg0.isAltDown() || arg0.isAltGraphDown(),
							arg0.isControlDown(), keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN, keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP,
							keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT, keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT);
				} catch (Exception e) {
					Application.crash(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// We use the key pressed logger as this one filters out all non char keys.
			}
		});
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					// Since the window server returns the position absolute to the frame so we need
					// to subtract the window decorations. Furthermore java is super wired: while
					// both the screen and the mouse location share the same behavior for their X
					// axis, they don't share the same behavior regarding their Y axis: the graphics
					// coordinates grow from the top to the bottom and the mouse coordinates grow
					// from the bottom to the top (except when they don't which I'll politely ignore
					// for now).
					Vec2 pos = new Vec2(arg0.getPoint().x - (frame.getWidth() - frame.getContentPane().getWidth()),
							arg0.getPoint().y - (frame.getHeight() - frame.getContentPane().getHeight()));
					if (isInputDebuggingEnabled()) {
						Application.log("INPUT: Mouse click at " + pos.toString());
					}
					s.clickedAt(pos);
				} catch (Exception e) {
					Application.crash(e);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		this.frame.setResizable(false);
	}

	@Override
	public Canvas getCanvas() {
		while (painting) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Application.log("Thread interrupted while waiting for drawing lock");
				e.printStackTrace();
			}
		}
		c.clear();
		return c;
	}

	@Override
	public synchronized void performPaintOperation() {
		painting = true;
		this.panel.repaint();
	}

	@Override
	public void destroy() {
		frame.setVisible(false);
		frame.dispose();
	}

	@Override
	public void setResolution(int width, int height) {
		this.frame.setSize(width, height);
		this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.c = new BufferedImageCanvas(this.bufferedImage);
		c.clear();
		Application.log("Set resolution to " + width + " by " + height);
	}

	@Override
	public void setup() {
		this.frame.setVisible(true);
	}

	@Override
	void appendScene(Scene s) {
		this.s = s;
	}

	@Override
	public Vec2 getResolution() {
		return new Vec2(this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
	}

	@Override
	public Vec2 getMouseCursorLocation() {
		final Point mp = MouseInfo.getPointerInfo().getLocation();
		if (mp == null)
			return new Vec2(0, 0);
		final Point fp = this.frame.getLocationOnScreen();
		final Insets windowInsets = this.frame.getInsets();
		return new Vec2((int) (mp.getX() - fp.getX() - windowInsets.left), (int) (mp.getY() - fp.getY() - windowInsets.top));
	}

	public class AWTTile extends Tile {

		private final BufferedImage tileBi;

		private AWTTile(String file) {
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(file));
			} catch (IOException e) {
				Application.crash(new RuntimeException("While loading the file " + file + " something went wrong.", e));
			}
			this.tileBi = bi;
		}

		@Override
		public void renderAt(Canvas c, Vec2 position) {
			if (!(c instanceof BufferedImageCanvas)) {
				throw new RuntimeException("Something went wrong: This is an AWT Tile but the " +
						"canvas doesn't seam to be AWT compatible.");
			}
			final BufferedImageCanvas bic = (BufferedImageCanvas) c;
			bic.renderBufferedImage(this.tileBi, position.getX(), position.getY());
		}
	}

	@Override
	public Tile loadTile(String filePath) {
		return new AWTTile(filePath);
	}

}
