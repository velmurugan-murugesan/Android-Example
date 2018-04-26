package com.example.datepicket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	DatePicker dp;
	Button bt;
	TextView tv1,tv2,tv3;
	int i1,i2;
	int s1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dp=(DatePicker)findViewById(R.id.DP1);
		bt=(Button)findViewById(R.id.button1);
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		bt.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i1=dp.getDayOfMonth();
				s1=dp.getMonth();
				i2=dp.getYear();
				tv1.setText(String.valueOf(i1));
				tv2.setText(String.valueOf(s1));
				tv3.setText(String.valueOf(i2));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
