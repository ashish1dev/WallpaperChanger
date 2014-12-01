package com.myexample.wallpaperchanger;

import com.myexample.wallpaperchanger.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.os.Build;

public class ChangeTimerActivity extends ActionBarActivity {


	private NumberPicker numberPicker1;
	private NumberPicker numberPicker2;
	private Button saveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_timer);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		saveBtn=(Button) findViewById(R.id.saveBtn);
		numberPicker1= (NumberPicker)findViewById(R.id.numberPicker1);
		numberPicker2 = (NumberPicker) findViewById(R.id.numberPicker2);
		
		numberPicker1.setMaxValue(59); numberPicker1.setMinValue(0); numberPicker1.setValue(0);
		numberPicker2.setMaxValue(59); numberPicker2.setMinValue(1); numberPicker2.setValue(4);
		
		
		saveBtn.setOnClickListener(saveListener);
	}

	private OnClickListener saveListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ImageGalleryDemoActivity.time_interval=( (numberPicker1.getValue()*60) + numberPicker2.getValue() )*1000;
			Intent intent = new Intent(getApplicationContext(), ImageGalleryDemoActivity.class);
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_timer, menu);
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
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_change_timer,
					container, false);
			return rootView;
		}
	}

}
