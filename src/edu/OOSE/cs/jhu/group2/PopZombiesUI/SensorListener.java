package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Sensor that takes notifications of a change in orientation of the device and responds accordingly.
 * 
 * @author Elaine Chao
 * @author Connie Chang
 * @author Stephanie Chew
 * @author Ted Staley
 * @author Kevin Zhang 
 *
 */
public class SensorListener implements SensorEventListener {
	private SensorManager manager;
	
	@Override
	public void onSensorChanged(SensorEvent e) {
		// Animate screen to change view
	}
	
	@Override
	public void onAccuracyChanged(Sensor s, int accuracy) {
		//TODO
	}
}
