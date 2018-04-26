package com.example.findmisscall;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends Activity {
SimpleCursorAdapter adapter;
ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    lv=(ListView)findViewById(R.id.list);
	    String[] strFields = {
	            android.provider.CallLog.Calls.CACHED_NAME, android.provider.CallLog.Calls.NUMBER,
	            android.provider.CallLog.Calls.DATE, android.provider.CallLog.Calls.TYPE
	    };
	    String strzOrder = android.provider.CallLog.Calls.DATE + " DESC";

	    Cursor mCallCursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,
	            strFields, null, null, null);

	    if (mCallCursor.moveToFirst()) {

	        do {

	            boolean missed = mCallCursor.getInt(mCallCursor.getColumnIndex(CallLog.Calls.TYPE)) == CallLog.Calls.MISSED_TYPE;

	            if (missed) {

	                String name = mCallCursor.getString(mCallCursor
	                        .getColumnIndex(CallLog.Calls.CACHED_NAME));

	                String number = mCallCursor.getString(mCallCursor
	                        .getColumnIndex(CallLog.Calls.NUMBER));
/*
	                String time = DateFormat.getDateTimeInstance(DateFormat.CAPITAL_AM_PM, DateFormat.CAPITAL_AM_PM)
	                        .format(mCallCursor.getLong(mCallCursor
	                                .getColumnIndex(CallLog.Calls.DATE)));*/
	                String[] from=new String[] {android.provider.CallLog.Calls.CACHED_NAME,android.provider.CallLog.Calls.NUMBER};
	                int[] to =new int[] {R.id.Name,R.id.Number};
	                SimpleCursorAdapter adapter;
	                adapter=new SimpleCursorAdapter(this, R.layout.lise, mCallCursor, from, to);
	                this.setListAdapter(adapter);	               
	                Toast.makeText(getBaseContext(), name+","+number, Toast.LENGTH_SHORT).show();

	                Log.d("PhoneLog", "You have a missed call from " + name + " on " + number);
	            }

	        } while (mCallCursor.moveToNext());

	    }

	}
	private void setListAdapter(SimpleCursorAdapter adapter2) {
		// TODO Auto-generated method stub
		
	}
}
