package com.rajaappstore.customtoast;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		final Dialog dialog;
		dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
		dialog.getWindow().setBackgroundDrawable(
		new ColorDrawable(
		android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.toast_layout);
		final TextView txt_about_head=(TextView)dialog.findViewById(R.id.textView1);
		final TextView txt_about_details=(TextView)dialog.findViewById(R.id.textView2);
		final Button btn_cancel=(Button)dialog.findViewById(R.id.button1);
		dialog.setCancelable(true);
		dialog.show();
		btn_cancel.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			dialog.dismiss();	
			}
		});
		// if you want to hide dialog call dialog.dismiss();

	}

	

}
