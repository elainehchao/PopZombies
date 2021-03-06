package edu.OOSE.cs.jhu.group2.PopZombiesModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.OOSE.cs.jhu.group2.PopZombiesUI.GameView;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Model for implementing the logic of Pop Zombies. On each level, player tries to eliminate all zombies with popcorn.
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Staley
 * @author Kevin Zhang
 */
public class PopZombieModel {

	/**
	 * Speed that refreshes the frame in seconds.
	 */
    private final int FRAME_RATE;
    /**
     * Time lag between each parachute launch, in seconds.
     */
    private final double DELAY_BETWEEN_PARACHUTES;
    /**
     * Number of kernels each parachute supplies.
     */
    private final int KERNELS_PER_PARACHUTE;
    /**
     * Time lag in between each level before the next level begins, in seconds.
     */
    private final double TIME_BETWEEN_LEVELS;
    //final int HEALTH_PER_POPCORN = 2; //Player gains 2HP (of 100) when eating one popcorn
    //final int DAMAGE_PER_POPCORN = 25; //Zombie loses 25HP when hit (out of 100)
    /**
     * Initial number of kernels per parachute is 25
     */
    final int KERNELS_PER_MICROWAVE_BATCH = 25; //microwave 25 kernels at a time    
    /**
     * Width of the user's screen.
     */
    private final int GAME_WIDTH;
    /**
     * Height of the user's screen.
     */
    private final int GAME_HEIGHT; //this should match the screen height
    /**
     * Depth of the room.
     */
    private final int GAME_DEPTH;
    //final int ATTACK_RANGE = 50; //zombies attack if within this distance
    /**
     * Constant to assist image scaling
     */
    private final int POPCORN_SIZE;
    /**
     * Gives zombie's depth in the screen
     */
    private final int ZOMBIE_INITIAL_DEPTH;
    /**
     * Zombie's attack range
     */
    private final int ZOMBIE_FINAL_DEPTH;
    /**
     * Assist zombie image scaling
     */
    private final double ZOMBIE_INITIAL_SCALE;
    /** 
     * Size of the zombie image
     */
    private final int ZOMBIE_SIZE;
    /**
     * Scale's zombie attack range
     */
    private final double ZOMBIE_FINAL_SCALE;
    /**
     * Zombie's beginning Y
     */
    private final int ZOMBIE_START_Y;
    /**
     * Countdown to the next parachute launch.
     */
    private int parachuteDelay;
    /**
     * Countdown til the next level begins.
     */
    private int nextLevelDelay;
    /**
     * Number of zombies in room limit.
     */
    private int zombiesInRoomCap;
    /**
     * Number of zombies left in level that need to be killed.
     */
    private int totalZombiesRemainingInLevel;
    /**
     * Keeps track of whether the game is currently in between levels or not.
     */
    private boolean betweenLevels;
    /**
     * Keeps track of whether or not a parachute has been sent out.
     */
    private boolean activeParachute;
    /**
     * Microwave that the player uses to microwave popcorn.
     */
    private Microwave microwave;
    /**
     * Parachute that the player taps on to collect ammo.
     */
    private Parachute parachute;
    /**
     * Player that user uses to interact with game.
     */
    private Player player;
    /**
     * List of popcorn kernels player has sent out on the screen.
     */
    private List<Popcorn> popcorns;
    /**
     * List of zombies currently in the room.
     */
    private List<Zombie> zombies;
    /**
     * Current level the player is on.
     */
    private int level;
    /**
     * Player's current point score.
     */
    private int currentScore;
    /**
     * List of model listeners to interact between UI and model.
     */
    private List<PopZombieModelListener> listeners;
    
    Position p;
    Timer timer;
    /**
     * Constructor for model -- also sets up the level.
     */
    public PopZombieModel() {
        this.microwave = new Microwave(p);
        this.KERNELS_PER_PARACHUTE = 40;
        this.parachute = new Parachute(new Position(-100,0,0), KERNELS_PER_PARACHUTE);
        this.player = new Player(100);
        this.popcorns = new ArrayList<Popcorn>(); //popcorn that is being thrown (in mid-air)
        this.zombies = new ArrayList<Zombie>(); //zombies active in the game
        this.listeners = new ArrayList<PopZombieModelListener>(); //list of model listeners
        this.level = 1;
        this.currentScore = 0;
        this.totalZombiesRemainingInLevel = 1;
        this.p = new Position(0, 0, 0);
        this.activeParachute = false;
        this.betweenLevels = true;
        this.zombiesInRoomCap =  2;
        this.parachuteDelay = 0;
        this.nextLevelDelay = 0;
        this.GAME_WIDTH = 800;
        this.ZOMBIE_SIZE = 20000;
        this.GAME_DEPTH = 5000;
        this.GAME_HEIGHT = 400;
        this.POPCORN_SIZE = 150;
        this.TIME_BETWEEN_LEVELS = 10.0;
        this.DELAY_BETWEEN_PARACHUTES = 15.0;
        this.FRAME_RATE = 30;
        this.ZOMBIE_INITIAL_DEPTH = 500;
        this.ZOMBIE_FINAL_DEPTH = 50;
        this.ZOMBIE_INITIAL_SCALE = 0.20;
        this.ZOMBIE_FINAL_SCALE = 1.0;
        this.ZOMBIE_START_Y = 100;
        this.setupLevel();
        
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                gameLoop();
                if (checkEndLevel()) {
	                try {
	        			Thread.sleep(6000);
	        		} catch (InterruptedException e) {
	        			e.printStackTrace();
	        		}
	                startLevel();
                }
            }
        }, 0, 1000/FRAME_RATE);
    }
    
    
    /**
     * Generates a random integer within the bounds of the screen.
     * @return integer that will be used for positioning
     */
    private int getRandomXInRoom() {
        Random rand = new Random();
        return rand.nextInt(GAME_WIDTH) + 1;
    }

    /**
     * Sets up the level by increasing the number of zombies
     * according to the level difficulty.
     * @param level the player is currently on
     */
    private void setupLevel() {
        // only setup each level one time
        if(!betweenLevels) {
            return;
        } else {
            betweenLevels = false;
        }

        // increase max zombies that can exist at once
        zombiesInRoomCap = 1 + this.level;

        // increase number of zombies for the level
        totalZombiesRemainingInLevel = this.level*3 + 1;

        // setup zombies to be introduced based on current level
        for(int i = 0; i<zombiesInRoomCap; i++) {
            addZombieToGame();
        }
    }

    /**
     * Loops through the game to update it.
     */
    public void gameLoop() {
        // add zombie to room if necessary
        if(this.zombies.size() < zombiesInRoomCap) {
            if(totalZombiesRemainingInLevel > 0) {
                this.addZombieToGame();
            }
        }

        // create parachute if delay between parachutes has ended
        parachuteDelay += 1000/FRAME_RATE;
        if(parachuteDelay>DELAY_BETWEEN_PARACHUTES*1000) {
            parachuteDelay = 0;
            this.sendParachute();
        }

        // update location of parachute, zombies, and popcorn
        this.moveEntities();
        //removes popcorns that didn't hit any zombies but are far away
        this.removeTooFarPopcorn();
        // check for collisions (popcorn & zombie)
        this.collisionCheck();        
        // check to see which zombies are still alive and kill the ones that are not
        this.killZombie();
        
        // check if zombies are within attacking range and attack
        for (int i = 0; i < this.zombies.size(); i++) {
            this.zombies.get(i).update();
            if (this.zombies.get(i).isAttacking()) {
                this.player.hurt();
            }
        }
        
        // check if player has missed parachute and make it inactive if missed
        if (this.activeParachute) {           
            if (this.parachute.getPosition().getY() > this.GAME_HEIGHT) {
                //this.activeParachute = false;
                this.parachuteDelay = 0;
            }
        }
        
        // check to see if player is still alive
        if (!this.player.isAlive()) {
            this.gameOver();
        }

        // check for level complete
        if (this.checkEndLevel()) {
        	this.betweenLevels = true;
            this.endLevel();
        }
        
    }
    
    /**
     * Get copy of the zombie list
     * @return a copy of this.zombies
     */
    public List<Zombie> getCopyOfZombies() {
    	List<Zombie> zombiesCopy = new ArrayList<Zombie>();
    	for(Zombie z : this.zombies) {
    		zombiesCopy.add(z);
    	}
    	return zombiesCopy;
    }
    
    /**
     * Get copy of the popcorn list
     * @return a copy of this.popcorns
     */
    public List<Popcorn> getCopyOfPopcorns() {
    	List<Popcorn> popcornCopy = new ArrayList<Popcorn>();
    	for(Popcorn p : this.popcorns) {
    		popcornCopy.add(p);
    	}
    	return popcornCopy;
    }
    
    /**
     * Checks to see if player has defeated all the zombies in the level.
     * @return true if the level is over
     */
    public boolean checkEndLevel() {
        if (this.zombies.size() == 0 && this.player.getCurrentHealth() > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Updates all the listeners that the level has ended and advance game onto next level.
     */
    private void endLevel() {
        this.advanceLevel();

        for(PopZombieModelListener l : this.listeners) {
            l.levelEnded();
        }
    }
    
    /**
     * Sets up the new level, and starts it.
     */
    private void startLevel() {
    	setupLevel();
    	for(PopZombieModelListener l : this.listeners) {
            l.levelStarted();
        }
    }

    /** 
     * Updates location of parachute downwards, zombies closer, and popcorn further.
     */
    private void moveEntities() {
        // move parachute, if active
        if (this.activeParachute) {
            this.parachute.update();
        }

        // move all zombies
        for (Zombie z : this.zombies) {
            z.update();
        }
    
        // move all popcorns that are being thrown
        for (Popcorn p : this.popcorns) {
            p.update();
        }
    }
    
    /**
     * Create a zombie to add to the game.
     * @return z the zombie that was created
     */
    public Zombie addZombieToGame() {
        Zombie z = new Zombie(new Position(
        		this.getRandomXInRoom(), ZOMBIE_START_Y, ZOMBIE_INITIAL_DEPTH), 50
                );
        this.zombies.add(z);
        this.totalZombiesRemainingInLevel -= 1;
        return z;
    }

    /**
     * Adds new listener to current list of listeners.
     * @param l the listener that interacts between model and ui
     */
    public void addListener(GameView v, PopZombieModelListener l) {
        this.listeners.add(l);
    }
    
    /**
     * Get the initial depth of the zombie
     * @return ZOMBIE_INITIAL_DEPTH
     */
    public int getZombieInitialDepth(){
    	return ZOMBIE_INITIAL_DEPTH;
    }
    
    /**
     * Get the final depth of the zombie
     * @return ZOMIBIE_FINAL_DEPTH
     */
    public int getZombieFinalDepth(){
    	return ZOMBIE_FINAL_DEPTH;
    }
    
    /**
     * Get the initial scaling factor for the zombie image
     * @return ZOMBIE_INITIAL_SCALE
     */
    public double getZombieInitialScale(){
    	return ZOMBIE_INITIAL_SCALE;
    }
    
    /**
     * Get the final scaling factor of the zombie image
     * @return ZOMBIE_FINAL_SCALE
     */
    public double getZombieFinalScale(){
    	return ZOMBIE_FINAL_SCALE;
    }
    
    /**
     * Launches a parachute to float down the screen.
     */
    private void sendParachute() {
        this.activeParachute = true;
        // set parachute's position randomly in the room, at y=0 TODO
        this.parachute.getPosition().setX(this.getRandomXInRoom());
        // later this should be changed to (height of parachute)*-1
        this.parachute.getPosition().setY(0);
    }
    
    /**
     * Gets parachute's status.
     * @return activeParachute true if the parachute is launched 
     */
    public boolean getActiveParachute() {
        return this.activeParachute;
    }

    /**
     * Get the popcorns that have been launched.
     * @return popcorns the list of popcorns player launched
     */
    public List<Popcorn> getPopcorns() {
        return this.popcorns;
    }
    
    /**
     * Get the zombies on the screen.
     * @return zombies the list of zombies on the screen
     */
    public List<Zombie> getZombies() {
    	return this.zombies;
    }

    /**
     * Check if popcorn makes contact with the zombie.
     */
    public void collisionCheck() {
    	float zombieX, zombieY, zombieZ;
    	float popX, popY, popZ;

    	for (int i = 0; i < this.popcorns.size(); i++) {
    		for (Zombie z : this.zombies) {
            /*
             * run through all popcorns
             * a collision should be detected if
             *     1) the popcorn is at a similar depth to the zombie
             *     2) the popcorn's x and y are with some rectangle that
             *        confines the zombie (size of zombie image, probably)
             */
    			//Checks that popcorn position is close to x-y position of zombie
    			zombieX = z.getPosition().getX();
    			zombieY = z.getPosition().getY();
    			zombieZ = z.getPosition().getZ();
    			popX = this.popcorns.get(i).getPosition().getX();
    			popY = this.popcorns.get(i).getPosition().getY();
    			popZ = this.popcorns.get(i).getPosition().getZ();
    			if (popX < (zombieX - ZOMBIE_SIZE/zombieZ) || popX > (zombieX + ZOMBIE_SIZE/zombieZ)) {
    				continue;
    			}

    			if (popY < (zombieY - ZOMBIE_SIZE/zombieZ) || popY > (zombieY + ZOMBIE_SIZE/zombieZ)) {
    				continue;
    			}

    			// if popcorn position is greater than the zombie position, we have a hit

    			if (popZ >= zombieZ) {
    				// if popcorn hits zombie,
    				// remove the piece of popcorn and decrease the zombie's health
    				this.popcorns.remove(this.popcorns.get(i));
    				z.decreaseHealth();
    				i--;
    				break;
    			}

    		}
        }
    }

    /**
     * Kills zombies that are out of health by removing them from the list of zombies.
     */
    private void killZombie() {
    	
    	Iterator<Zombie> i = this.zombies.iterator();
    	while(i.hasNext()) {
			Zombie z = i.next();
    		if (z.getCurrentHealth() == 0) {
    			i.remove();
    			// Player gets 5 points for every zombie killed
    			this.currentScore += 5;
    			this.totalZombiesRemainingInLevel -= 1;
    		}
    	}
    	
    }

    /**
     * Get the number of zombies current out in the level.
     * @return zombies the list of zombies player sees on screen
     */
    public int getNumZombiesInLevel() {
        return zombies.size();
    }

    /**
     * Get the player's current score.
     * @return the number of points player currently has
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Get the height of the screen.
     * @return GAME_HEIGHT the height of the user's screen
     */
    public int getGameHeight() {
        return this.GAME_HEIGHT;
    }
    
    
    /**
     * Get the depth of the screen.
     * @return GAME_DEPTH the height of the user's screen
     */
    public int getGameDepth() {
    	return this.GAME_DEPTH;
    }

    
    /**
     * Get the default size of the popcorn.
     * @return POPCORN_SIZE the default size
     */
    public int getPopcornSize() {
    	return this.POPCORN_SIZE;
    }
    
    /**
     * Get the default size of the zombie (used for scaling).
     * @return ZOMBIE_SIZE the default size
     */
    public int getZombieSize() {
    	return this.ZOMBIE_SIZE;
    }
    
    /**
     * Retrieves the parachute used to send ammo.
     */
    public Parachute getParachute() {
    	return this.parachute;
    }
    
    /**
     * Advances player onto the next level and increases level by 1.
     */
    private void advanceLevel(){
        this.level++;
        
        /*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	*/
        //Break in action, display to the user "Wave [level]" or similar
        //wait to call "setupLevel()" until after the delay is finished
       // this.setupLevel();
    }

    /**
     * Ends game when player is out of health.
     */
    private void gameOver() {
    	this.popcorns.clear();
    	for (PopZombieModelListener l : this.listeners) {
    	    l.gameEnded();
    	}
    }

    /**
     * Player throws popcorn so creates a popcorn to appear on the screen
     * and takes popcorn away from player's supply.
     * @param P the position of the popcorn thrown at
     * @return
     */
    public Popcorn throwPopcorn(Position P){
        //check to make sure ammo is remaining
        //create a new piece of popcorn at depth zero and at the (x,y) of the player's tap
        //decrease player's ammo supply by one
        //add the piece to this.popcorns so that it will be used in the game
        Popcorn pop = new Popcorn(P);
        if (this.player.throwPopcorn()) {
            this.popcorns.add(pop);
        } else {
        	for (PopZombieModelListener l : this.listeners) {
        		l.outOfAmmo("Out of ammo!");
        	}
        }
        return pop;
    }
    
    
    /**
     * Removes popcorn from the list that didn't hit any zombies and have gone too far
     */
    public void removeTooFarPopcorn() {
    	Iterator<Popcorn> i = this.popcorns.iterator();
    	while(i.hasNext()) {
    		Popcorn p = i.next();
    		if (p.getPosition().getZ() > GAME_DEPTH) {
    			i.remove();
    		} else if (this.POPCORN_SIZE/(p.getPosition().getZ() + 1) <= 0) {
    		
				i.remove();
			} 
    	}
    }
    
    /**
     * Microwave popcorn to convert it from kernel to popcorn.
     */
    public void microwavePopcorn(){
        this.player.microwavePopcorn();
    }
    
    /**
     * Gets the amount of ammo at player's disposal
     * @return the ammount of ammo
     */
    public int getPlayerAmmo() {
    	return this.player.getAmmo();
    }
    
    /**
     * Gets the ammount of kernels in the player's supply
     * @return the size of the supply
     */
    public int getPlayerSupply() {
    	return this.player.getSupply();
    }

    /**
     * Player tapped on parachute and collects it to add kernels to ammo.
     */
    public void collectParachute() {
    	if (this.activeParachute) {
    		//move the parachute off-screen
    		parachute.getPosition().setX(GAME_WIDTH);
    		parachute.getPosition().setY(GAME_HEIGHT);
    		parachute.getPosition().setZ(GAME_DEPTH);
 
    		//reset the parachute delay
    		this.parachuteDelay = 0;
        
    		//increase supply
    		this.player.takeParachute(this.KERNELS_PER_PARACHUTE);
    		this.activeParachute = false;
    	}
    }

}
