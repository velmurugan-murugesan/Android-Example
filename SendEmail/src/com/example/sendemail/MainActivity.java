package com.example.sendemail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
      
       final EditText edittextEmailAddress = (EditText)findViewById(R.id.email_address);
       final EditText edittextEmailSubject = (EditText)findViewById(R.id.email_subject);
       final EditText edittextEmailText = (EditText)findViewById(R.id.email_text);
       Button buttonSendEmail_intent = (Button)findViewById(R.id.sendemail_intent);
      
       buttonSendEmail_intent.setOnClickListener(new Button.OnClickListener(){

  @Override
  public void onClick(View arg0) {
   // TODO Auto-generated method stub

   String emailAddress = edittextEmailAddress.getText().toString();
   String emailSubject = edittextEmailSubject.getText().toString();
   String emailText = edittextEmailText.getText().toString();

   String emailAddressList[] = {emailAddress};
  
   Intent intent = new Intent(Intent.ACTION_SEND); 
   intent.setType("plain/text");
   intent.putExtra(Intent.EXTRA_EMAIL, emailAddressList);  
   intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject); 
   intent.putExtra(Intent.EXTRA_TEXT, emailText); 
   startActivity(Intent.createChooser(intent, "Choice App t send email:"));
  
  }});
   }
}