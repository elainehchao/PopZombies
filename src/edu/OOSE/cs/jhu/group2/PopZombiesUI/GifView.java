package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import java.io.InputStream;

import edu.OOSE.cs.jhu.group2.PopZombiesModel.PopZombieModel;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.Position;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.Zombie;
import edu.OOSE.cs.jhu.group2.popzombies.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/** GifView class that will animate images
 * 
 * @author Connie Chang
 * @author Elaine Chao
 * @author Stephanie Chew
 * @author Ted Stanley
 * @author Kevin Zhang
 *
 */
public class GifView extends View {
	/**
	 * Movie to animate images
	 */
	private Movie movie, movie1;
	InputStream is = null, is1 = null;
	/**
	 * Time of the animation
	 */
	private long moviestart;
	/**
	 * Model used to follow logic of the game
	 */
	private PopZombieModel model;
	
	/**
	 * Constructor for GifView object
	 * @param context to provide information about the android environment
	 */
	public GifView(Context context) {
		super(context);
		is = context.getResources().openRawResource(R.drawable.zombie);
		movie = Movie.decodeStream(is);
	}
	
	/**
	 * Initializes model to follow responses to change in game logic
	 * @param m the model used to keep track of logic of the game 
	 */
	public void setModel(PopZombieModel m) {
		this.model = m;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		if (this.model != null) {
			for (Zombie z : this.model.getZombies()) {
				Position zpos = z.getPosition();
				int zx = (int) zpos.getX();
				int zy = (int) zpos.getY();
				int zz = (int) zpos.getZ();
				
				if (this.is == null) {
					is = getResources().openRawResource(R.drawable.zombie);
					this.movie = Movie.decodeStream(is);
				}
				
				canvas.drawColor(Color.TRANSPARENT);
				super.onDraw(canvas);
				long now = android.os.SystemClock.uptimeMillis();
				if (moviestart == 0) {
					moviestart = now;
				}
				
				int relTime = (int) ((now - moviestart) % movie.duration());
				movie.setTime(relTime);
				//movie.draw(canvas,  this.getWidth()/2-20,  this.getHeight()/2 - 40);
				//canvas.scale(1.9f, 1.21f);
				//Bitmap movieBitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888);
				//Canvas movieCanvas = new Canvas(movieBitmap);
				//movie.draw(movieCanvas,  0,  0);
				//Rect src = new Rect(0, 0, movie.width(), movie.height());
				//Rect dst = new Rect(zx, zy, this.getWidth(), this.getHeight());
				//canvas.drawBitmap(movieBitmap,  src,  dst, null);
				
			}
		}
		this.model.gameLoop();
		invalidate();
		
	}
}