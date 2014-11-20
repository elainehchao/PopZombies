package edu.OOSE.cs.jhu.group2.PopZombiesUI;

/**Game interface that brings the UI components together.
 * Created with refence to http://www.kilobolt.com/game-development-tutorial.html
 * 
 * @author kilobolt
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Staley
 * @author Kevin Zhang
 *
 */
public interface Game {

	/**Returns the Audio used for the game.
	 * 
	 * @return the Audio used for the game
	 */
    public Audio getAudio();

    /**Returns the Input used for the game.
     * 
     * @return the Input in use
     */
    public Input getInput();

    /**Returns the Graphics used for the game.
     * 
     * @return The Graphics in use
     */
    public Graphics getGraphics();

    /**Sets the current Screen.
     * 
     * @param screen The new Screen
     */
    public void setScreen(Screen screen);

    /**Returns the Screen currently in use.
     * 
     * @return The current Screen
     */
    public Screen getCurrentScreen();

    /**Returns the screen for initializing game.
     * 
     * @return The initializing screen
     */
    public Screen getInitScreen();
}