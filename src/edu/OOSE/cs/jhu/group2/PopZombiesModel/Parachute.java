package edu.OOSE.cs.jhu.group2.PopZombiesModel;

/**Parachute class that provides the player with unpopped kernels.
 * 
 * @author Connie Chang
 * @author Kevin Zhang
 * @author Stephanie Chew
 *
 */
public class Parachute extends Entity {

    /**The amount of kernels in the parachute.*/
    private int kernelCount;
    
    /**Constructor for parachute
     * 
     * @param p: position of parachute
     * @param k: number of kernels
     */
    public Parachute(Position p, int k) {
        super(p);
        this.kernelCount = k;
    }
    
    /**Gets kernel count
     * 
     * @return the kernel count
     */
    public int getKernelCount() {
        return this.kernelCount;
    }
    
    /**Updates the position of the parachute*/
    public void update(){
        //move parachute straight down, for now
        this.getPosition().setY(this.getPosition().getY() + 2);
    }

}
