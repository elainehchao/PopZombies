package edu.OOSE.cs.jhu.group2.PopZombiesUI;

/**The Music class that represents one piece of background music.
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
public interface Music {
	
	/**Starts playing the music.*/
    public void play();

    /**Stops playing the music.*/
    public void stop();

    /**Pauses the music.*/
    public void pause();

    /**Sets whether Music loops.
     * 
     * @param looping Whether the music loops
     */
    public void setLooping(boolean looping);

    /**Sets the volume of the music.
     * 
     * @param volume The new volume
     */
    public void setVolume(float volume);

    /**Returns whether the music is playing.
     * 
     * @return True if playing, false if not.
     */
    public boolean isPlaying();

    /**Returns whether the music is not playing.
     * 
     * @return True if not playing, otherwise false
     */
    public boolean isStopped();

    /**Returns whether the music is looping.
     * 
     * @return True if looping, otherwise false
     */
    public boolean isLooping();

    /**Deletes the music.*/
    public void dispose();

    /**Gets the beginning of the music file.*/
    void seekBegin();
}