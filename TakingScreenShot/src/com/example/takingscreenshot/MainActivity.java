package com.example.takingscreenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button bt;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onclick(View v)
	{
		bitmap=getscreenshot();
		savebitmap(bitmap);
		Toast.makeText(getApplicationContext(), "ScreenShot Taken", Toast.LENGTH_SHORT).show();
	}
	private void savebitmap(Bitmap bitmap) {
		// TODO Auto-generated method stub

		File imagepath=new File(Environment.getExternalStorageDirectory()+"/ScreenShot.jpg");
		FileOutputStream fos;
		try
		{
			fos=new FileOutputStream(imagepath);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO: handle exception
		e.getMessage();
		}
		catch (IOException e) 
		{
			// TODO: handle exception
		e.printStackTrace();
		}
		
	}
	private Bitmap getscreenshot() {
		// TODO Auto-generated method stub
		View rootview=findViewById(R.id.textView1).getRootView();
		rootview.setDrawingCacheEnabled(true);
		return rootview.getDrawingCache();
	}
}
