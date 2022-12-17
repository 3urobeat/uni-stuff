package de.uniluebeck.itm.schiffeversenken.engine.uicomponents;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class is a ready-to-extend model for a scene that also contains GUI
 * components.
 * 
 * @author leondietrich
 *
 */
public class ComponentModel {

	private final Map<String, Component> components;

	public ComponentModel(Map<String, Component> c) {
		this.components = c;
	}

	/**
	 * Use this method to get the attached components.
	 * 
	 * @return the components within this model
	 */
	public Component[] getComponents() {
		return components.values().toArray(new Component[components.size()]);
	}

	/**
	 * Use this method to iterate over the contained components.
	 * 
	 * @return An iterator of components
	 */
	public Iterator<Component> getComponentIterator() {
		return this.components.values().iterator();
	}

	/**
	 * Use this method in order to get the components and their registration names
	 * at the same time.
	 * 
	 * @return A set of map entries of strings and components.
	 */
	public Set<Entry<String, Component>> getNamedComponents() {
		return this.components.entrySet();
	}

	/**
	 * Use this method to get a specific component by its name.
	 * 
	 * @param name The name of the component to get.
	 * @return The component or null if no component was found.
	 */
	public Component getComponent(String name) {
		return this.components.get(name);
	}

	/**
	 * Use this method to get the name of a registered component.
	 * 
	 * @param c The component to look up.
	 * @implNote Warning: This method is very computationally expensive.
	 * @return The name of the component or null if it isn't registered with this
	 *         model
	 */
	public String getComponentName(Component c) {
		Iterator<String> iter = this.components.keySet().iterator();
		String foundName = null;
		while (iter.hasNext() && foundName == null) {
			String s = iter.next();
			if (this.components.get(s) == c) {
				foundName = s;
			}
		}
		return foundName;
	}
}
