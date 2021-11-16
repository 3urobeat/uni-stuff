/**
 * Since Java 9 there is this new module system inside the Oracle JVM. Don't
 * worry about it for now. It will get nasty later.
 * 
 * @author leondietrich
 *
 */
module schiffeversenken {
	exports de.uniluebeck.itm.schiffeversenken.main;

	requires java.desktop;
}