package edu.OOSE.cs.jhu.group2.PopZombiesModel;

/**Position class that represents the Entities location.
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Stanley
 * @author Kevin Zhang
 *
 */
public class Position {

	/**The X position on the plane of the screen.*/
	private float x;
	/**The Y position on the plane of the screen.*/
	private float y;
	/**The depth of the Entity (how far from Player it is).*/
	private float z;
	
	/**Constructor that takes three coordinates for Position.
	 * 
	 * @param x X position on screen
	 * @param y Y position on screen
	 * @param z Z (depth) position
	 */
	public Position(float x, float y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**Returns the X position.
	 * 
	 * @return The X position
	 */
	public float getX() {
		return this.x;
	}
	
	/**Returns the Y position.
	 * 
	 * @return The Y position
	 */
	public float getY() {
		return this.y;
	}
	
	/**Returns the depth.
	 * 
	 * @return The depth
	 */
	public float getZ() {
		return this.z;
	}
	
	/**Sets the X position.
	 * 
	 * @param x The new X position
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**Sets the Y position.
	 * 
	 * @param y The new Y position
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**Sets the depth.
	 * 
	 * @param z The new depth
	 */
	public void setZ(float z) {
		this.z = z;
	}
	
}