package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import java.util.List;

/**Input interface that manages the user input.
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
public interface Input {
    
	/**Class representing different types of events.
	 * 
	 * @author kilobolt
	 *
	 */
    public static class TouchEvent {
    	/**Event type is down.*/
        public static final int TOUCH_DOWN = 0;
        /**Event type is up.*/
        public static final int TOUCH_UP = 1;
        /**Event type is drag.*/
        public static final int TOUCH_DRAGGED = 2;
        /**Event type is hold.*/
        public static final int TOUCH_HOLD = 3;

        /**The event type.*/
        public int type;
        /**The location of event.*/
        public int x, y;
        
        /**The point of contact.*/
        public int pointer;


    }

    /**Get the X coordinate of event.
     * 
     * @param pointer Which point of contact.
     * @return The X coordinate
     */
    public int getTouchX(int pointer);

    /**Get the Y coordinate of event.
     * 
     * @param pointer Which point of contact.
     * @return The Y coordinate
     */
    public int getTouchY(int pointer);

    /**Returns the list of events in order.
     * 
     * @return The list of events
     */
    public List<TouchEvent> getTouchEvents();
}