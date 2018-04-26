package com.example.progressdialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt=(Button)findViewById(R.id.button1);
		
	}
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ProgressDialog dialog=ProgressDialog.show(this, "Doing Something", "Please wait",true);
				new Thread(new Runnable() 
				{
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try
						{
							Thread.sleep(5000);
							dialog.dismiss();
						}
						catch (InterruptedException e) 
						{
						// TODO: handle exception
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
