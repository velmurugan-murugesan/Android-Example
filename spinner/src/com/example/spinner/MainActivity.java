package com.example.spinner;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	Spinner sp;
	TextView tv;
	string[] country={"india","pak","china"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp=(Spinner)findViewById(R.id.spinner1);
		tv=(TextView)findViewById(R.id.textView1);
		ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(adp);
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3, SpinnerAdapter adp) 
	{
		String b=sp.setAdapter(adp).toString();
		tv.setText(b);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		tv.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
