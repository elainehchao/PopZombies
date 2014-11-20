package edu.OOSE.cs.jhu.group2.PopZombiesUI;

/**Audio interface that runs the sounds of the game.
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
public interface Audio {
	
	/**Takes in an audio file to create background music.
	 * 
	 * @param file The audio file
	 * @return Audio converted in Music class
	 */
    public Music createMusic(String file);

    /**Takes an audio file to create sound effects.
     * 
     * @param file The audio file
     * @return audio converted to Sound class
     */
    public Sound createSound(String file);
}