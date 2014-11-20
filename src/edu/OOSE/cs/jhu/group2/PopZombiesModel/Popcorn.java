package edu.OOSE.cs.jhu.group2.PopZombiesModel;

import android.graphics.BitmapFactory;
import edu.OOSE.cs.jhu.group2.popzombies.R;

/**Popcorn class representing thrown popcorn.
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Stanley
 * @author Kevin Zhang
 *
 */
public class Popcorn extends Entity {

	/**Constructor for Popcorn class.*/
    public Popcorn(Position p) {
        super(p);
        // TODO Auto-generated constructor stub
    }
    
    /**Updates the position of the Popcorn.*/
    public void update() {
    	this.getPosition().setZ(this.getPosition().getZ() + 20);
    }
    
}
