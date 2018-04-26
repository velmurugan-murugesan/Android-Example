package com.example.contactprovider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

public class MainActivity extends ListActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Uri allcontacts=Uri.parse("content://contacts/people");
		Cursor c;
		if(android.os.Build.VERSION.SDK_INT<11)
		{
		c=managedQuery(allcontacts, null, null, null, null);
		}
		else
		{
		CursorLoader cursorLoader=new CursorLoader(this,allcontacts,null,null,null,null);
		c=cursorLoader.loadInBackground();
		}
		String[] columns=new String[] {ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID};
	int[] views=new int[] {R.id.contactName,R.id.contactID};
	SimpleCursorAdapter adapter;
	if(android.os.Build.VERSION.SDK_INT<11)
	{
		adapter =new SimpleCursorAdapter(this, R.layout.activity_main,c,columns, views);
	}
	else
	{
	adapter=new SimpleCursorAdapter(this,R.layout.activity_main,c,columns,views,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	
	}
	this.setListAdapter(adapter);
	}
	

	
}
