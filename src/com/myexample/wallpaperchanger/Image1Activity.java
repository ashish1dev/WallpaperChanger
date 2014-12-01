package com.myexample.wallpaperchanger;

import java.io.IOException;

import com.myexample.wallpaperchanger.R;
import com.myexample.wallpaperchanger.ImageGalleryDemoActivity.PlaceholderFragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Log;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class Image1Activity extends ActionBarActivity {

	private static int RESULT_LOAD_IMAGE = 1;
	 
	int images[] = {
			R.drawable.abundance_self_spirit_1, 
			 
	};
	
	String message[]={
			"Embracing the Deeper Connection to Self brings and instant and abundant connection to Spirit. Be One!",
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
		
		 
		 LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		    for(int x=0;x<images.length;x++) {
		    	
		    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		        //RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    	
		     	ImageView mImageView = new ImageView(Image1Activity.this);
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
		        TextView text = new TextView(Image1Activity.this);
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
            		"your day. Your wallpaper will change automatically every 2 " +
            		"months, rotating the pieces. You can also determine how often " +
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

			ImageView imageView = new ImageView(Image1Activity.this);
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
 

	
	
}
