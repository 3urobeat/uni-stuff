package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import de.uniluebeck.itm.schiffeversenken.engine.Canvas;
import de.uniluebeck.itm.schiffeversenken.engine.Vec2;
import de.uniluebeck.itm.schiffeversenken.engine.View;

/**
 * @author leondietrich
 *
 */
public class ComponentView extends View<ComponentModel> {

	public ComponentView(ComponentModel m) {
		super(m);
	}

	@Override
	public void render(Canvas canvas, Vec2 mouseLocation) {
		final List<Entry<String, Component>> list = new ArrayList<>(this.getModelInstance().getNamedComponents());
		list.sort(new Comparator<Entry<String, Component>>() {

			@Override
			public int compare(Entry<String, Component> arg0, Entry<String, Component> arg1) {
				if (!(arg0.getValue() instanceof LateRendering)) {
					return -1;
				}
				final LateRendering l = (LateRendering) arg0.getValue();
				return l.isCurrentlyLate() ? 1 : -1;
			}
		});

		for (Entry<String, Component> e : list) {
			final Component c = e.getValue();
			if (c instanceof Drawable) {
				final Drawable d = (Drawable) c;
				d.draw(canvas, mouseLocation);
			}
		}
	}

	@Override
	public void prepare() {
		// nothing needs to be done here (for now)

	}

}
