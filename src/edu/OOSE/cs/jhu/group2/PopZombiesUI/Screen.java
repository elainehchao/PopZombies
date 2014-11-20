package edu.OOSE.cs.jhu.group2.PopZombiesUI;
/**
 * Interface for the screen user interacts with on mobile device.
 * @author Connie Chang
 * @author Stephanie Chew
 * @author Kevin Zhang
 * @author Elaine Chao
 * @author Ted Stanley
 *
 */
public abstract class Screen {
    protected final Game game;

    /** Constructor for screen.
     * @param game the current game that the user is running
     */
    public Screen(Game game) {
        this.game = game;
    }

    /**
     * Updates the screen to render a new view.
     * @param deltaTime time passed in between before next update
     */
    public abstract void update(float deltaTime);

    /**
     * Paint the graphics to show user play-by-play of player's actions and game's response to actions.
     * @param deltaTime time elapsed before screen should paint new view
     */
    public abstract void paint(float deltaTime);

    /**
     * Pauses the game and stops any timers or counters from continuing. Player cannot
     * hit any zombies and no zombies can attack player. No new view is rendered.
     */
    public abstract void pause();

    /**
     * Picks the game back off where it left off previously and screen goes back to
     * rendering new views.
     */
    public abstract void resume();

    /**
     * Deletes the file object of the screen.
     */
    public abstract void dispose();
    
    /**
     * Previous screen when the user wants to exit game and go back to main menu.
     */
    public abstract void backButton();
}