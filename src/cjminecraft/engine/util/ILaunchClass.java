package cjminecraft.engine.util;

/**
 * All boot up classes must implement this to make sure that all managers are
 * added before the game is loaded up.
 * 
 * @author CJMinecraft
 *
 */
public interface ILaunchClass {

	/**
	 * Add all of the managers before the game has loaded up
	 */
	void addManagers();

}
