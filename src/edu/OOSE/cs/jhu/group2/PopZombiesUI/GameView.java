package edu.OOSE.cs.jhu.group2.PopZombiesUI;

import java.io.InputStream;
import java.util.Iterator;

import edu.OOSE.cs.jhu.group2.PopZombiesModel.PopZombieModel;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.PopZombieModelListener;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.Popcorn;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.Position;
import edu.OOSE.cs.jhu.group2.PopZombiesModel.Zombie;
import edu.OOSE.cs.jhu.group2.popzombies.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * GameView class that is responsible for rendering the view of the game.
 * 
 * @author Connie Chang
 * @author Ted Staley
 * @author Elaine Chao 
 *
 */
public class GameView extends ImageView {
	/** 
	 * Paint object that will create the view
	 */
	private Paint paint = new Paint();
	/**
	 * Model used to control the logic of the game
	 */
	private PopZombieModel model;
	/**
	 * Game background
	 */
	private Bitmap backgroundImage;
	/**
	 * Image of the popcorn
	 */
	private Bitmap popcornImageOriginal;
	
	/**
	 * Image of the microwave
	 */
	private Bitmap microwaveOriginal;
	
	/**
	 * Image of the gameover screen
	 */
	private Bitmap gameover;
	/**
	 * Image that will flash when the level is over
	 */
	private Bitmap levelover;
	/**
	 * Picture of the zombie
	 */
	private Bitmap zombieImageOriginal;
	/**
	 * Signals if the game is over yet
	 */
	private Boolean endgame;
	/**
	 * Signals if the level is over yet
	 */
	private Boolean endlevel;
	/**
	 * Keeps track of the zombie's position and the screen to image ratio for the zombie
	 */
	private float x, y, z, zz, zombieAspectRatio;
	/**
	 * Keeps track of the zombie's depth
	 */
	private int zx, zy;
	
	/**
	 * Animations used to move the zombies
	 */
	private Movie movie, movie1;
	InputStream is = null, is1 = null;
	/**
	 * Tracks the length of the animation
	 */
	private long moviestart;

	/**
	 * Constructor for GameView object
	 * @param context to provide information about the android environment
	 */
	public GameView(Context context) {
		super(context);
	}
	
	/**
	 * Overloaded GameView object constructor
	 * @param context of the android environment
	 * @param attrs attributes of the environment
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initializes model to follow responses to change in game logic
	 * @param m the model used to keep track of logic of the game 
	 */
	public void setModel(PopZombieModel m) {
		this.model = m;
		this.endlevel = false;
		this.endgame = false;
	}
	
	/**
	 * Flash a response when the user provides input in the screen 
	 * @param e the event to indicate user input
	 * @return true if the user tapped the screen
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
	    // MotionEvent reports input details from the touch screen
	    // and other input controls. In this case, you are only
	    // interested in events where the touch position changed.

		int microwavebegx = this.getWidth() - this.microwaveOriginal.getWidth();
		int microwavebegy = this.getHeight() - this.microwaveOriginal.getHeight();
		
	    if (e.getAction() == MotionEvent.ACTION_UP) {
	        	float x = e.getX();
	    	    float y = e.getY();
	    	    
	    	    if (x >= microwavebegx && y >= microwavebegy) {
	    	    	this.model.microwavePopcorn();
	    	    } else {
	    	    	this.model.throwPopcorn(new Position(x, y, 0));
	    	    }
	    	//    this.invalidate();
	        	
	    }

	    return true;
	}
	
	/**
	 * Indicates when the game is over and paint has to render the game over image
	 */
	public void drawGameOver() {
		this.endgame = true;
	}
	
	/**
	 * Indicates when the level is over and paint has to render the level over image
	 */
	public void endLevel() {
		this.endlevel = true;
	}
	
	/**
	 * Indicates when the level is starting and paint has to render the beginning of the level image
	 */
	public void startLevel() {
		this.endlevel = false;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		
		if (this.model != null) {
			
			Paint paint = new Paint(); 
			paint.setColor(Color.WHITE); 
			paint.setTextSize(30); 
			canvas.drawText("Score: " + this.model.getCurrentScore(), 50, 50, paint);
			canvas.drawText("Ammo: " + this.model.getPlayerAmmo(), 50, 90, paint);
			canvas.drawText("Supply: " + this.model.getPlayerSupply(), 50, 120, paint);
			
			if (this.endlevel) {
				if (this.levelover == null) {
					this.levelover = BitmapFactory.decodeResource(getResources(), R.drawable.levelup);
				}	
				Bitmap levelupimage = Bitmap.createScaledBitmap(this.levelover, 900, 500, false);	
				canvas.drawBitmap(levelupimage, 0, 0, null);
					
				//canvas.drawColor(0, Mode.CLEAR);
			}
			
			if (this.microwaveOriginal == null) {
				this.microwaveOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.microwave);
			}
			
			this.microwaveOriginal = Bitmap.createScaledBitmap(this.microwaveOriginal, 200, 200, false);
			int mheight = this.microwaveOriginal.getHeight();
			int mwidth = this.microwaveOriginal.getWidth();	
			canvas.drawBitmap(this.microwaveOriginal, this.getWidth() - mwidth, this.getHeight() - mheight, null);
			
			Iterator<Zombie> i = this.model.getCopyOfZombies().iterator();
	    	while(i.hasNext()) {
	    		Zombie z = i.next();
				Position zpos = z.getPosition();
				zx = (int) zpos.getX();
				zy = (int) zpos.getY();
				zz = zpos.getZ();
				
				if (this.zombieImageOriginal == null) {
					this.zombieImageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.zombie);
					zombieAspectRatio = this.zombieImageOriginal.getHeight()/this.zombieImageOriginal.getWidth();
				}

				double percentTotalTravelCompleted = (this.model.getZombieInitialDepth()-zz)/(this.model.getZombieInitialDepth() - this.model.getZombieFinalDepth());
				double scalingFactor = ((this.model.getZombieFinalScale()-this.model.getZombieInitialScale())*percentTotalTravelCompleted)+this.model.getZombieInitialScale();
				
				int zheight = (int) (this.zombieImageOriginal.getHeight()*scalingFactor);
				int zwidth = (int) (int) (this.zombieImageOriginal.getWidth()*scalingFactor);


				Bitmap zImage = Bitmap.createScaledBitmap(this.zombieImageOriginal, zwidth, zheight, false);
				zheight = zImage.getHeight();
				zwidth = zImage.getWidth();	
				canvas.drawBitmap(zImage, zx-(zwidth/2), zy, null);
				
				//draw the health bar background
				Paint healthbgPaint = new Paint();
				healthbgPaint.setColor(Color.RED);
				int startx = zx-(zwidth/4);
				canvas.drawRect(startx, zy-30, startx+(zwidth/2), zy-15, healthbgPaint);
				
				//draw the health bar HP
				Paint healthPaint = new Paint();
				healthPaint.setColor(Color.GREEN);
				canvas.drawRect(startx, zy-30, startx+(int)(((double)z.getCurrentHealth()/(double)z.getTotalHealth())*((double)zwidth/2.0)), zy-15, healthPaint);
				
				
				/*
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
				Bitmap movieBitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888);
				Canvas movieCanvas = new Canvas(movieBitmap);
				movie.draw(movieCanvas, this.getWidth()/zx, this.getHeight()/zy);
				Rect dst = new Rect(zx - zwidth, zy - zheight, zx + zwidth, zy + zheight);
				canvas.drawBitmap(movieBitmap, null, dst, null);
				*/	
			}
	    	Iterator<Popcorn> j = this.model.getCopyOfPopcorns().iterator();
	    	while(j.hasNext()) {
				Popcorn pop = j.next();
				Position pos = pop.getPosition();
				x = pos.getX();
				y = pos.getY();
				z = pos.getZ();

				if (this.popcornImageOriginal == null) {
					this.popcornImageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.popcorn);
				}
				
				int height = (int)(this.popcornImageOriginal.getHeight()*(1.0-((double)z/400.0)));
				int width = (int)(this.popcornImageOriginal.getWidth()*(1.0-((double)z/400.0)));
				
				//this should be changed later
				if (height <= 0 || width <= 0) {
					height = 1;
					width = 1;
				}
				
				Bitmap popcornImage = Bitmap.createScaledBitmap(this.popcornImageOriginal, height, width, false);
				height = popcornImage.getHeight();
				width = popcornImage.getWidth();	
				canvas.drawBitmap(popcornImage, x-(width/2), y-(height/2), null);
				
			}
			
			if (this.endgame) {
				if (this.gameover == null) {
					this.gameover = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
				}
				
				Bitmap gameOverImage = Bitmap.createScaledBitmap(this.gameover, 900, 500, false);	
				canvas.drawBitmap(gameOverImage, 0, 0, null);
			}
			
		}

		//this.model.gameLoop();
		
		//invalidate();
		postInvalidateDelayed(25);
	}
}
