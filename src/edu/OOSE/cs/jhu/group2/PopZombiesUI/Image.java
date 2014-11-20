package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import edu.OOSE.cs.jhu.group2.PopZombiesUI.Graphics.ImageFormat;

/**Image interface that manages an image.
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
public interface Image {
	
	/**Returns the width of the image.
	 * 
	 * @return The width
	 */
    public int getWidth();
    
    /**Returns the height of the image.
	 * 
	 * @return The height
	 */
    public int getHeight();
    
    /**Returns the format of the image.
     * 
     * @return The format
     */
    public ImageFormat getFormat();
    
    /**Deletes the image.*/
    public void dispose();
}