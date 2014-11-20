package edu.OOSE.cs.jhu.group2.PopZombiesUI;

/**Sound interface that plays the sounds of the game.
 * Created with refence to http://www.kilobolt.com/game-development-tutorial.html
 * 
 * @author kilobolt
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Stanley
 * @author Kevin Zhang
 *
 */
public interface Sound {
	/**Plays the track.
	 * 
	 * @param volume: volume level
	 */
    public void play(float volume);

    /**Stops playing the track.*/
    public void dispose();
}