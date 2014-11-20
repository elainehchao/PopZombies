package edu.OOSE.cs.jhu.group2.PopZombiesModel;

/**Entity class that represents all on-screen objects.
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Stanley
 * @author Kevin Zhang
 *
 */
public class Entity {
    
	/**The Position of the Entity on screen.*/
    protected Position position;
    
    /**Constructor that sets up the position.*/
    public Entity(Position p) {
        this.position = p;
    }
    
    /**Returns the Position.
     * 
     * @return The position
     */
    public Position getPosition(){
        return this.position;
    }
}
