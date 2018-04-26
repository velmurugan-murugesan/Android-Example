package com.pareshmayani.smsinbox;

import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Paresh N. Mayani
 * (w): http://www.technotalkative.com/
 */
public class SMSInboxActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);
        
        if(fetchInbox()!=null)
        {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fetchInbox());
        	lViewSMS.setAdapter(adapter);
        }
    }

    public ArrayList<String> fetchInbox()
    {
    	ArrayList<String> sms = new ArrayList<String>();
    	
    	Uri uriSms = Uri.parse("content://sms/inbox");
    	Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null); 

    	cursor.moveToFirst();
    	while  (cursor.moveToNext()) 
    	{
    		   String address = cursor.getString(1);
    		   String body = cursor.getString(3);
    		   
    		   System.out.println("======> Mobile number => "+address);
    		   System.out.println("=====> SMS Text => "+body);
    		   
    		   sms.add("Address=> "+address+"\n SMS => "+body);
    	}
    	return sms;
    	
    }
}