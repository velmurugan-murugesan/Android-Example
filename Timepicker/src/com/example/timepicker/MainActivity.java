package com.example.timepicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
public class MainActivity extends Activity {
	TimePicker tp;
	TextView tv1,tv2;
	int time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Calendar cal = Calendar.getInstance(); 

		 // millisecond = cal.get(Calendar.MILLISECOND);
		  //int second = cal.get(Calendar.SECOND);
		  int minute = cal.get(Calendar.MINUTE);
		        //12 hour format
		  //int hour = cal.get(Calendar.HOUR);
		        //24 hour format
		  int hourofday = cal.get(Calendar.HOUR_OF_DAY);
//		  int month = cal.get(Calendar.MONTH);
//		  int year = cal.get(Calendar.YEAR);
//		  int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
//		  int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
	  String c=String.valueOf(minute);
		String b=String.valueOf(hourofday);
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv1.setText(b);
		tv2.setText(c);
		int sethour=tp.getCurrentHour();
		int setminute=tp.getCurrentMinute();
		int dishour=sethour-hourofday;
		int dismin=setminute-minute;
		 time=(dishour*60*60*1000)+(dismin*60*1000);
	}
		public void onClick(View v)
		{
		new Thread(new Runnable() 
		{
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		try {
			Thread.sleep(time);
			final NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			final Notification nd=new Notification(R.drawable.ic_launcher,"HELLO",System.currentTimeMillis());
			Intent i =new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.google.co.in"));
			PendingIntent pi=PendingIntent.getActivity(MainActivity.this, 0, i, android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
			nd.setLatestEventInfo((Context) getApplicationContext(), "HEllo", "Welcome", pi);
			//nd.vibrate=new long[]{100,100,100,100};
			
			nm.notify(0,nd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
			}
		}).start();

		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
