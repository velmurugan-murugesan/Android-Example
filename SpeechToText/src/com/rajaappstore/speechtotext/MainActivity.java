package com.rajaappstore.speechtotext;

import java.util.ArrayList;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int RESULT_SPEECH = 1;
	private ImageButton btnSpeak;
	private TextView txtText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtText = (TextView) findViewById(R.id.txtText);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
		btnSpeak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
					txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
				if (resultCode == RESULT_OK && null != data) {
	
					ArrayList<String> text = data
							.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	
					txtText.setText(text.get(0));
				}
			break;
		}

		}
	}
}
