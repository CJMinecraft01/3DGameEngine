package cjminecraft.engine.util;

/**
 * All managers can hook into the initialisation phases and also the main game
 * loop along with the ending game cleanup
 * 
 * @author CJMinecraft
 *
 */
public interface IManager {

	/**
	 * Pre-initialisation phase. For use with registering of textures, models
	 * etc.
	 * 
	 * @throws Exception
	 *             Allows exceptions to be passed on further
	 */
	void preInit() throws Exception;

	/**
	 * Initialisation phase. For use with creation of game objects. E.g. loading
	 * terrain, registering guis, network stuff etc.
	 * 
	 * @throws Exception
	 *             Allows exceptions to be passed on further
	 */
	void init() throws Exception;

	/**
	 * Post-initialisation phase. For compatibility use, allow the game to run
	 * with everything the user can change
	 * 
	 * @throws Exception
	 *             Allows exceptions to be passed on further
	 */
	void postInit() throws Exception;

	/**
	 * The main game loop. For logic and rendering
	 * 
	 * @throws Exception
	 *             Allows exceptions to be passed on further
	 */
	void loop() throws Exception;

	/**
	 * Clean up all the mess you made. Clear data from the RAM mainly
	 * 
	 * @throws Exception
	 *             Allows exceptions to be passed on further
	 */
	void cleanUp() throws Exception;

}
