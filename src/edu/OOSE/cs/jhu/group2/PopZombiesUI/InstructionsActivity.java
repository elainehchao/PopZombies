package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import edu.OOSE.cs.jhu.group2.popzombies.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Instructions menu that will provide user instructions on how to play the game
 * 
 * @author Connie Chang
 * @author Ted Staley
 * @author Elaine Chao 
 *
 */
public class InstructionsActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_screen);
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
     * Change screen back to the main activity menu
     * @param view the view to change
     */
    public void backToMenu(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

}
