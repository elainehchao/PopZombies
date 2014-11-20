package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import edu.OOSE.cs.jhu.group2.popzombies.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Pause menu that will allow user to pause the game
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Staley
 * @author Kevin Zhang
 *
 */
public class PauseActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_screen);
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
    
    /**
     * Allows user to go back to the main activity menu
     * @param view to return to main menu
     */
    public void backToMenu(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Allows user to restart the game and go back to the beginning of the game
     * @param view to restart the game
     */
    public void restart(View view) {
    	Intent intent = new Intent(this, StillImageActivity.class);
    	startActivity(intent);
    }
	
}
