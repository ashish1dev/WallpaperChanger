package com.myexample.wallpaperchanger;

import com.myexample.wallpaperchanger.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewImagesName extends Activity {
	ListView listView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_android_example);

		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);

		// Defined Array values to show in ListView
		String[] values = new String[] {
				"Abundance Self Spirit", 
				"Extra Ordinary",
				"Family Peace",
				"Loving Hand",
				"Re Evolution",
				"Shared Journey"
		};

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);


		// Assign adapter to ListView
		listView.setAdapter(adapter); 

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition     = position;

				// ListView Clicked item value
				String  itemValue    = (String) listView.getItemAtPosition(position);

				// Show Alert 
				Toast.makeText(getApplicationContext(),
						"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
						.show();
				if (itemValue.equals("Abundance Self Spirit")) {
					Intent image1Intent = new Intent(ListViewImagesName.this, Image1Activity.class);
				       startActivity(image1Intent);
				}
				else if (itemValue.equals("Extra Ordinary")){
					Intent image1Intent = new Intent(ListViewImagesName.this, Image2Activity.class);
				       startActivity(image1Intent);
				}
				else if (itemValue.equals("Family Peace")){
					Intent image1Intent = new Intent(ListViewImagesName.this, Image3Activity.class);
				       startActivity(image1Intent);
					
				}else if (itemValue.equals("Loving Hand")){
					Intent image1Intent = new Intent(ListViewImagesName.this, Image4Activity.class);
				       startActivity(image1Intent);
					
				}else if (itemValue.equals("Re Evolution")){
					Intent image1Intent = new Intent(ListViewImagesName.this, Image5Activity.class);
				       startActivity(image1Intent);
					
				}else if (itemValue.equals("Shared Journey")){
					Intent image1Intent = new Intent(ListViewImagesName.this, Image6Activity.class);
				       startActivity(image1Intent);
					
				}   

			}



		}); 
	}

}