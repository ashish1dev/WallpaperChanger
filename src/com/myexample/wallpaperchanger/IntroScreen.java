package com.myexample.wallpaperchanger;

 
import java.io.IOException;

import com.myexample.wallpaperchanger.R;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class IntroScreen extends Activity {
           
	private static int RESULT_LOAD_IMAGE = 1;
	Handler hand = new Handler();
	private WallpaperManager myWallPaperManager;
	public static long time_interval = 4000;
	int images[] = {
			R.drawable.abundance_self_spirit_1, 
			R.drawable.extra_ordinary_1,
			R.drawable.family_peace_1,
			R.drawable.loving_hand_1,
			R.drawable.re_evolution_1,
			R.drawable.shared_journey_1
	};
	String message[]={
			"It is the journey of one in the world of all. We cross paths, we knit the Universe. Your strength gives shape and balance. Your love goes beyond what you can see, it travels, it journeys... It is the extension of you to the world. How Do You Share The Power Of Your Journey?"
			};
	private int index=0;
	

	private int screenWidth;
	private int screenHeight;
	
	private static final String TAG = "MyActivity";
        @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_intro_screen);
          
                Button button = (Button) findViewById(R.id.button1);

                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                    	 //  Toast.makeText(this, "Clicked on Button", Toast.LENGTH_SHORT).show();
                           Intent listViewIntent = new Intent(IntroScreen.this, ListViewImagesName.class);
                           startActivity(listViewIntent);
                    }
                });
            	
        		DisplayMetrics displaymetrics = new DisplayMetrics();
        		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        		screenHeight = displaymetrics.heightPixels;
        		screenWidth = displaymetrics.widthPixels;
        		
        		
        		myWallPaperManager=WallpaperManager.getInstance(getApplicationContext());
        		hand.postDelayed(run, time_interval);

        		
        }
          
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         
       return super.dispatchTouchEvent(me);
    }
          
     
     
     
 	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    options.inPreferredConfig = Config.RGB_565;
	    options.inDither = true;
	    
	    return BitmapFactory.decodeResource(res, resId, options);
	}


	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
public int count=1;
Runnable run = new Runnable() {


	@Override
	public void run() {
		//img.setBackgroundDrawable(getResources().getDrawable(images[index++]));
		try{
			//int igotit = getResources().getIdentifier(val, "drawable", getPackageName());
			
			//myWallPaperManager.setResource(images[index++]);
				
			myWallPaperManager.setBitmap(decodeSampledBitmapFromResource(getResources(),images[index++], (int)(screenWidth-(0.1*screenWidth)), (int)( screenHeight-(0.1*screenHeight)) ) );
			
			Toast.makeText(getApplicationContext(), "Wallpaper has been set",   Toast.LENGTH_SHORT).show();            
		}catch(IOException e){
			e.printStackTrace();
		}

		if (index == images.length)
			index = 0;
		hand.postDelayed(run, time_interval);
	}
};
}