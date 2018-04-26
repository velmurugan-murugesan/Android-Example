package com.rajaappstore.customfont;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	Typeface font;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView)findViewById(R.id.txt);
		font=Typeface.createFromAsset(getAssets(), "android.ttf");
		tv.setTypeface(font);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
