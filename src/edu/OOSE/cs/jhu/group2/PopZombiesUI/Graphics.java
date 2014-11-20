package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import android.graphics.Paint;

/**Graphics interface that manages the graphics and drawing.
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
public interface Graphics {
	/**The three types of image formats.*/
    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    /**Creates a new image.
     * 
     * @param fileName The image source file
     * @param format The desired format of the image
     * @return The new image
     */
    public Image newImage(String fileName, ImageFormat format);

    /**
     * 
     * @param color
     */
    public void clearScreen(int color);

	/**Draws a line on screen.
	 * 
	 * @param x Starting X
	 * @param y Starting Y
	 * @param x2 Ending X
	 * @param y2 Ending Y
	 * @param color Color of line
	 */
    public void drawLine(int x, int y, int x2, int y2, int color);

    /**Draws a rectangle.
     * 
     * @param x The left X coordinate
     * @param y The top Y coordinate
     * @param width The width of rectangle
     * @param height The height of rectangle
     * @param color The color of rectangle
     */
    public void drawRect(int x, int y, int width, int height, int color);

    /**Draws an image.
     * 
     * @param image The image to be drawn
     * @param x the x position of the image
     * @param y the y position of the image
     * @param srcX where to put scale on screen in x
     * @param srcY where to put scale on screen in y
     * @param srcWidth scale the width of image
     * @param srcHeight scale the height of image
     */
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight);

    /**
     * Overloaded method to draw the image in 2D
     * @param Image the image to be drawn
     * @param x the x position of the image
     * @param y the y position of the image
     */
    public void drawImage(Image Image, int x, int y);

    /**
     * Puts text onto the screen.
     * @param text to print out onto screen
     * @param x the x position of the text
     * @param y the y position of the text
     * @param paint object that will put text up
     */
    void drawString(String text, int x, int y, Paint paint);

    /**
     * Gets the width of the screen
     * @return int the size of the width of the screen
     */
    public int getWidth();

    /**
     * Gets the height of the screen
     * @return int the size of the height of the screen
     */
    public int getHeight();

    /**
     * Draws the color on the screen
     * @param i the alpha component of the color to draw
     * @param j the red component
     * @param k the green component
     * @param l the blue component
     */
    public void drawARGB(int i, int j, int k, int l);

}