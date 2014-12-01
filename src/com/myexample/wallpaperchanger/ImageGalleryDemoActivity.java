package com.myexample.wallpaperchanger;

import java.io.IOException;
import java.io.InputStream;

import com.myexample.wallpaperchanger.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

@SuppressLint("NewApi")
public class ImageGalleryDemoActivity extends ActionBarActivity {

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
			"Embracing the Deeper Connection to Self brings and instant and abundant connection to Spirit. Be One!",
			"At times I notice ordinary things adding up to extraordinary experiences. I ask for humility to be watchful for those times where the marvelous of simplicity is embraced as an extraordinary experience."
			,"It is in those moments of peace, few but precious, when we can think, thank and pray. It is in that quietness when we can look deeply into the purpose we have within our families, when we assess the rich value of time spent together.",
			"Giving Receiving - Darma Karma - Love & Love",
			"I continue to walk with a daily refreshed look of how to mold the shape of my life, it takes time, it takes practice, it takes trust and openness to receive and experience it at its fullest. "
			,"It is the journey of one in the world of all. We cross paths, we knit the Universe. Your strength gives shape and balance. Your love goes beyond what you can see, it travels, it journeys... It is the extension of you to the world. How Do You Share The Power Of Your Journey?"
			};
	private int index=0;
	
	private int screenWidth;
	private int screenHeight;
	
	private static final String TAG = "MyActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_gallery_demo);

		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenHeight = displaymetrics.heightPixels;
		screenWidth = displaymetrics.widthPixels;
		
		
		myWallPaperManager=WallpaperManager.getInstance(getApplicationContext());
		hand.postDelayed(run, time_interval);

		 LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		    for(int x=0;x<images.length;x++) {
		    	
		    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		        //RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    	
		     	ImageView mImageView = new ImageView(ImageGalleryDemoActivity.this);
	          //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	            //params.setMargins(10, 10, 10, 0);
	            
	            mImageView.setLayoutParams(params);
	           // mImageView.setBackgroundResource(images[x]);
	            
	            mImageView.setImageBitmap(
					    decodeSampledBitmapFromResource(getResources(),images[x], screenWidth/3, screenHeight/2));
				
	            //mImageView.setPadding(1, 1, 1, 1);
		        mImageView.setBackgroundColor(Color.WHITE);
		        linearLayout1.addView(mImageView);
		        
		        //add textview on top of imageview
		        TextView text = new TextView(ImageGalleryDemoActivity.this);
		        text.setTextSize(12);
		        text.setLayoutParams(params);
		        text.setPadding(10, 1, 10, 0);
		        
		        text.setId(x);
		        text.setBackgroundColor(Color.WHITE);
		        text.setText(message[x]);
		        linearLayout1.addView(text);
		        
		    }
		    
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	/** Called when the user clicks the 'add new image' button */
	public void openImageGallery(View view) {
		// Do something in response to button
		Intent i = new Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startActivityForResult(i, RESULT_LOAD_IMAGE);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	private ShareActionProvider mShareActionProvider;
	private Intent shareIntent;
	private NumberPicker mSeconds,mMinutes;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
	
			   Intent intent = new Intent(this, ChangeTimerActivity.class);
			   startActivity(intent);

		}
		else if (id ==R.id.menu_item_share){
			shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, "SAMPLE TEXT");
			shareIntent.setType("text/plain");
			Log.v(TAG,shareIntent.toString());

			startActivity(shareIntent);
		}
		else if(id==R.id.more){
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("More Inspiration:");
			final TextView message = new TextView(this);
			  // i.e.: R.string.dialog_message =>
			            // "Test this dialog following the link to dtmilano.blogspot.com"
			  final SpannableString s = 
			               new SpannableString(
			               		"http://www.sandra-dempsey.com	"+
			        					"http://www.pinterest.com/sandradempsey/pins "+
			        					"https://www.etsy.com/shop/teelight	 "+ 
			        					"http://www.zazzle.com/serenitees	"+
			        					"https://www.facebook.com/sandra.dempsey.7	 "+
			        					"http://instagram.com/sandradempsey7  "+
			        					"https://twitter.com/sandradempsey7  "+
			        					"http://about.me/sandradempsey	 "+
			        					"https://www.linkedin.com/in/sandradempsey1 ");
			  Linkify.addLinks(s, Linkify.WEB_URLS);
			  message.setText(s);
			  message.setMovementMethod(LinkMovementMethod.getInstance());

			  builder2.setView(message);
            
            builder2.setCancelable(true);
            builder2.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alert2 = builder2.create();
            alert2.show();
		}
		else if (id==R.id.about_app){
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("About this App:");
            builder1.setMessage("My art  is a multidimensional reflection of my " +
            		"spiritual connections flowing to various media in a delightful " +
            		"and moving display that draws your mind, body and soul into " +
            		"inspiration and understanding of yourself and who you are. " +
            		"Center your heart, soften your gaze and relax your mind while " +
            		"you immerse yourself in the beauty of each piece. Each piece " +
            		"will help you to find balance, beauty and harmony throughout " +
            		"your day. Your wallpaper will change automatically every 4 " +
            		"seconds, rotating the pieces. You can also determine how often " +
            		"you want the wallpaper to change. Use the pieces as profile " +
            		"pictures, post, pin, tweet and share with friends and family " +
            		"and allow them to also benefit from the positive energy " +
            		"radiating from each piece.");
            
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alert11 = builder1.create();
            alert11.show();
		}
		return super.onOptionsItemSelected(item);
	}


	// Call to update the share intent
	private void setShareIntent(Intent shareIntent) {
		if (mShareActionProvider != null) {
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_image_gallery_demo, container, false);
			return rootView;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			//ImageView imageView = (ImageView) findViewById(R.id.imgView);
			//imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

			LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

			ImageView imageView = new ImageView(ImageGalleryDemoActivity.this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.setMargins(10, 10, 10, 0);
			imageView.setLayoutParams(params);
			//image.setBackgroundResource(images[x]);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

			//image.setOnClickListener(clickListener);
			linearLayout1.addView(imageView);

		}


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
