package com.example.alerm;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
public class MainActivity extends Activity {
	TimePicker tp;
	Button bt1,bt2;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	tp=(TimePicker)findViewById(R.id.timePicker1);
	bt1=(Button)findViewById(R.id.button1);
	bt2=(Button)findViewById(R.id.button2);
	Calendar cal=Calendar.getInstance();
	final int hour=cal.get(Calendar.HOUR_OF_DAY);
	String hour1=String.valueOf(hour);
	final int minute=cal.get(Calendar.MINUTE);
	String minute1=String.valueOf(minute);
	//int second=cal.get(Calendar.SECOND);
bt1.setText(hour1+":"+minute1);	
bt2.setOnClickListener(new OnClickListener() 
{
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int s11=tp.getCurrentHour();
		String s1=tp.getCurrentHour().toString();
		int s12=tp.getCurrentMinute();
		String s2=tp.getCurrentMinute().toString();
		int al=s11-hour;
		int alm=s12-minute;
		//String am=((Button)tp.getChildAt(0)).getText().toString();
		Toast.makeText(getApplicationContext(),al+":"+alm+ "remaining" , Toast.LENGTH_SHORT).show();
			
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
