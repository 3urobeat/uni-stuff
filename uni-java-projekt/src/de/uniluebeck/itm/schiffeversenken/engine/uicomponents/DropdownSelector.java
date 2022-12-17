package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Consumer;

import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;

/**
 * This component allows you to select different items when you click on them.
 * It displays the current selected entry within the box.
 * 
 * @author leondietrich
 *
 */
public final class DropdownSelector<K> extends Component implements Clickable, Drawable, LateRendering {

	private final Map<K, String> itemMap;

	private K currentSelection = null;
	private Vec2 lastClickposition = null;

	private int expandedWidth, expandedHeight;
	private int lastTextHeight = 0;
	private boolean expanded;

	/**
	 * Use this constructor to generate a new instance of an drop down selector.
	 * 
	 * @param items  The items that the user may choose. Use K as your internal key
	 *               and the Value as the text that should be displayed.
	 * @param x      The x coordinate where the object should be located.
	 * @param y      The y coordinate where the object should be located.
	 * @param width  The width of the selector (When not expanded).
	 * @param height The height of the selector (When not expanded).
	 */
	public DropdownSelector(Map<K, String> items, int x, int y, int width, int height) {
		super();
		this.expandedHeight = -1;
		this.expandedWidth = -1;
		this.expanded = false;

		/**
		 * Wir brauchen eine Liste, die die Reihenfolge behaelt. Das tun (von den Maps
		 * der Standartbibliothek) zum Zeitpunkt des Kommentarschreibens nur die
		 * LinkedHashMap und die Treemap. Wenn wir das Format konvertieren muessen, dann
		 * waehlen wir aber die LinkedHashMap, da diese weniger Speicher braucht und
		 * beim itereieren etwas schneller ist.
		 */
		if (items instanceof LinkedHashMap || items instanceof TreeMap)
			this.itemMap = items;
		else
			this.itemMap = new LinkedHashMap<K, String>(items);

		this.setPosition(new Vec2(x, y));
		this.setWidth(width);
		this.setHeight(height);
		if (!items.isEmpty()) {
			currentSelection = items.keySet().iterator().next();
		}
	}

	/**
	 * Use this constructor to generate a new instance of an drop down selector.
	 * 
	 * @param items            The items that the user may choose. Use K as your
	 *                         internal key and the Value as the text that should be
	 *                         displayed.
	 * @param x                The x coordinate where the object should be located.
	 * @param y                The y coordinate where the object should be located.
	 * @param width            The width of the selector (When not expanded).
	 * @param height           The height of the selector (When not expanded).
	 * @param currentSelection The default selection.
	 */
	public DropdownSelector(Map<K, String> items, int x, int y, int width, int height, K currentSelection) {
		this(items, x, y, width, height);
		this.currentSelection = currentSelection;
	}

	@Override
	public void draw(Canvas c, Vec2 mouseLocation) {
		final Vec2 position = this.getPosition();
		final int x = position.getX(), y = position.getY();
		c.setColor(0.7, 0.7, 0.7);
		c.fillRoundRect(x, y, this.getWidth(), this.getHeight(), 3, 3);

		String selectedText = itemMap.get(currentSelection);
		if (selectedText == null) {
			selectedText = "";
		}
		Vec2 textdimms = c.getTextDimensions(selectedText);
		while (textdimms.getX() + 15 > this.getWidth() && selectedText.length() > 4) {
			selectedText = selectedText.substring(0, selectedText.length() - 4) + "...";
			textdimms = c.getTextDimensions(selectedText);
		}
		c.setColor(0, 0, 0);
		c.drawString(x + 2, y + textdimms.getY(), selectedText);

		c.drawLine(new Vec2(x + this.getWidth() - 10, y), new Vec2(x + this.getWidth() - 10, y + this.getWidth()));
		c.fillRoundRect(x + this.getWidth() - 7, y + this.getHeight() / 2 - 2, 4, 4, 2, 2);

		if (this.isCurrentlyExpanded()) {
			/*
			 * Wenn wir noch keine Dimensionen einer ausgeweiteten Auswahl haben, berechnen
			 * wir die hier mal kurz.
			 */
			if (this.expandedHeight == -1 || this.expandedWidth == -1) {
				this.computeExpandedDimensions(c);
			}

			c.setColor(0.7, 0.7, 0.7);
			c.fillRect(x, y + this.getHeight(), this.expandedWidth, this.expandedHeight);
			c.setColor(0, 0, 0);

			int yOffset = this.getHeight();
			final String selectedEntryText = this.itemMap.get(this.currentSelection);
			this.lastTextHeight = textdimms.getY();
			for (String s : this.itemMap.values()) {
				if (mouseLocation.getX() > x
						&& mouseLocation.getX() <= x + this.expandedWidth
						&& mouseLocation.getY() > y + yOffset
						&& mouseLocation.getY() <= y + yOffset + this.lastTextHeight) {
					// Wir haben ein mouseover!
					c.setColor(1, 1, 1);
					c.drawRect(x, y + yOffset, this.expandedWidth, this.lastTextHeight);
				}
				if (s == selectedEntryText) {
					// Wir koennen die Pointer einfach vergleichen, da es sich um das gleiche
					// Objekt handelt
					c.setColor(0.1, 0.1, 0.3);
				} else {
					c.setColor(0, 0, 0);
				}
				c.drawString(x + 2, y + yOffset - 3 + this.lastTextHeight, s);
				yOffset += this.lastTextHeight; // Should always be the same height
			}
		}
	}

	private void computeExpandedDimensions(Canvas c) {
		/*
		 * Hier sagen wir der Map, dass es fuer jeden Eintrag die Textgröße berechnen
		 * soll. Wir nutzen ein lambda, welches wir direkt dem Java-Internen Scheduler
		 * uebergeben, da auf diese weise Java bereits zugewisene restrechenzeit (meist
		 * auch ueber mehrere Kerne hinweg) nutzen kann und keine weitere vom
		 * Betriebsystem anfordern muss. Das geht bei kleinen Aufgaben deutlich
		 * schneller (und die map ist vermutlich klein [d.h. weniger als ein paar
		 * millionen Eintraege]).
		 * 
		 * Ausserdem koennt ihr dann mal so etwas sehen (als Kontrast zu den meist sehr
		 * linearen Pooluebungen).
		 */
		this.itemMap.values().forEach(new Consumer<String>() {

			@Override
			public void accept(String arg0) {
				Vec2 dim = c.getTextDimensions(arg0);
				expandedWidth = dim.getX() > expandedWidth ? dim.getX() : expandedWidth;
				expandedHeight += dim.getY() + 6;
			}
		});
	}

	@Override
	public boolean checkedIfClicked(Vec2 clickPosition) {
		this.lastClickposition = clickPosition;
		final boolean hit = clickPosition.getX() >= this.getPosition().getX()
				&& ((clickPosition.getX() <= this.getPosition().getX() + this.getWidth()
						&& clickPosition.getY() >= this.getPosition().getY()
						&& clickPosition.getY() <= this.getPosition().getY() + this.getHeight())
						|| (this.isCurrentlyExpanded()
								&& clickPosition.getX() <= this.getPosition().getX() + this.expandedWidth
								&& clickPosition.getY() >= this.getPosition().getY() + this.getHeight()
								&& clickPosition.getY() <= this.getPosition().getY() + this.getHeight() + this.expandedHeight));
		if (!hit && this.isCurrentlyExpanded()) {
			this.expanded = false;
		}
		return hit;
	}

	@Override
	public void performAction() {
		final int mouse_y = this.lastClickposition.getY();
		final int comp_y = this.getPosition().getY();
		// Wir muessen die X-Koordinate nicht mehr pruefen, da es ja einen click gab und
		// es also auf jeden fall getroffen hat.
		if (mouse_y >= comp_y && mouse_y <= comp_y + this.getHeight()) {
			this.expanded = !this.expanded;
			return;
		}
		if (this.isCurrentlyExpanded()) {
			int yOffset = comp_y + this.getHeight();
			for (Entry<K, String> e : this.itemMap.entrySet()) {
				if (mouse_y >= yOffset && mouse_y <= yOffset + this.lastTextHeight) {
					// Wir haben einen sieger
					this.expanded = false;
					this.currentSelection = e.getKey();
					return;
				}
				yOffset += this.lastTextHeight;
			}
		}
	}

	/**
	 * Use this method to check if the selector is currently expanded.
	 * 
	 * @return True if it is and otherwise false.
	 */
	public boolean isCurrentlyExpanded() {
		return this.expanded;
	}

	@Override
	public boolean isCurrentlyLate() {
		return this.isCurrentlyExpanded();
	}

	/**
	 * Use this method in order to get the current selected item.
	 * @return The current selected item as K
	 */
	public K getCurrentSelectedItem() {
		return this.currentSelection;
	}

}
