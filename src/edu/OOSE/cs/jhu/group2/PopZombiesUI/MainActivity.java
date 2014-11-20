package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import edu.OOSE.cs.jhu.group2.popzombies.R;

/**
 * Main menu that will provide user options to start game or read instructions
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Staley
 * @author Kevin Zhang
 *
 */
public class MainActivity extends Activity {
    
    //private PopZombieModel model;
	/**
	 * Allows music to be played while user is selecting option
	 */
	private MediaPlayer music;
	/**
	 * Reads in orientation input from device
	 */
	private SensorManager manager;
	/**
	 * Notified when there's a change in orientation and to respond accordingly 
	 */
	private SensorListener sL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = MediaPlayer.create(MainActivity.this, R.raw.poketcg);
        music.setVolume((float)0.3, (float)0.3);
        music.setLooping(true);
        music.start();
        //this.model = new PopZombieModel();
        //final Button playButton = (Button) findViewByID(R.id.button1);
        
        this.sL = new SensorListener();
        
        // Start up android sensor
        this.manager = (SensorManager) getSystemService(SENSOR_SERVICE);
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

    	// Stops listener to save battery
    	this.manager.unregisterListener(this.sL);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	music.start();
    	
    	this.manager.registerListener(this.sL,
    			this.manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
    			SensorManager.SENSOR_DELAY_GAME);
    }
    
    //public void buttonClicked() {
    //	setContentView(R.layout.class);
    //}
    
    /**
     * Allows for user to start playing the game
     * @param view to change to start of game
     */
    public void play(View view) {
    	//Do something in response to button
    	Intent intent = new Intent(this, StillImageActivity.class);
    	//intent.putExtra("model", this.model);
    	//EditText editText = (EditText) findViewById(R.id.edit_message);
    	//String message = editText.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    /**
     * Allows user to go to view instructions
     * @param view of the instructions
     */
    public void instructions(View view) {
    	Intent intent = new Intent(this, InstructionsActivity.class);
    	startActivity(intent);
    }
}