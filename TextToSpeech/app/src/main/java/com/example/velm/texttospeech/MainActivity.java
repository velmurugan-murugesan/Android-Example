package com.example.velm.texttospeech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech tts;
    @BindView(R.id.inputText)
    EditText inputText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
    }

    @OnClick(R.id.buttonSpeak)
    public void speak(){
        String text = inputText.getText().toString();
        if (text!=null && text.length()>0) {
            Toast.makeText(getApplicationContext(), "Saying: " + text, Toast.LENGTH_LONG).show();
            tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
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
            Toast.makeText(getApplicationContext(),
                    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
        }
        else if (status == TextToSpeech.ERROR) {
            Toast.makeText(getApplicationContext(),
                    "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
        }
    }

}