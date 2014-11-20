package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import edu.OOSE.cs.jhu.group2.PopZombiesModel.PopZombieModel;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.PopZombieModelListener;
import edu.OOSE.cs.jhu.group2.popzombies.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Generates the view for the game
 * 
 * @author Connie Chang
 * @author Ted Staley
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Kevin Zhang
 *
 */
public class StillImageActivity extends Activity {
    /**
     * Model to keep track of game logic
     */
    private PopZombieModel model;
    /**
     * Generates the game view
     */
    private GameView view;
    /**
     * Animates images
     */
    private GifView gif;
    /**
     * Plays music on menus and during gameplay
     */
    private MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);
        this.model = new PopZombieModel();

        music = MediaPlayer.create(StillImageActivity.this, R.raw.hansend);
        music.setVolume(1, 1);
        music.setLooping(true);
        music.start();
       
    	this.view = (GameView) findViewById(R.id.background);
    	this.view.setModel(this.model);

    	this.model.gameLoop();
    	
    	this.model.addListener(this.view, new PopZombieModelListener() {

			@Override
			public void levelEnded() {
				view.endLevel();
			}

			@Override
			public void gameEnded() {
				view.drawGameOver();
			}

			@Override
			public void levelStarted() {
				view.startLevel();
				
			}
			
			@Override
			public void outOfAmmo(String s) {
				//TODO print out given string
			}
    		
    	});
    	//this.model.setupLevel();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	music.stop();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	music.start();
    }
    
    /**
     * Allows user to go back to main menu
     * @param view to change
     */
    public void backToMenu(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Allows user to go to the pause menu
     * @param view to change
     */
    public void pause(View view) {
    	Intent intent = new Intent(this, PauseActivity.class);
    	startActivity(intent);
    }
}
