package com.javacodegeeks.android.tts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TtsActivity extends Activity implements OnInitListener {
	
	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts;
	
	private EditText inputText;
	private Button speakButton;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        inputText = (EditText) findViewById(R.id.input_text);
        speakButton = (Button) findViewById(R.id.speak_button);
        
        speakButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String text = inputText.getText().toString();
				if (text!=null && text.length()>0) {
					Toast.makeText(TtsActivity.this, "Saying: " + text, Toast.LENGTH_LONG).show();
					tts.speak(text, TextToSpeech.QUEUE_ADD, null);
				}
			}
		});
        
        Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
		
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}

	}

	@Override
	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) {
			Toast.makeText(TtsActivity.this, 
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(TtsActivity.this, 
					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
	
}