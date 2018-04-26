package com.vel.draganddroplistview;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.commonsware.cwac.tlv.TouchListView;

public class TouchListViewDemo extends ListActivity {
	String[] head_list={"A","B","C","D","E","F","G","H","I"};
	private IconicAdapter adapter=null;
	private ArrayList<String> array=new ArrayList<String>(Arrays.asList(head_list));
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		TouchListView tlv=(TouchListView)getListView();
		adapter=new IconicAdapter();
		setListAdapter(adapter);
		
		tlv.setDropListener(onDrop);
		tlv.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				
			}
		});
	}
	
	private TouchListView.DropListener onDrop=new TouchListView.DropListener() {
		@Override
		public void drop(int from, int to) {
				String item=adapter.getItem(from);
				
				adapter.remove(item);
				adapter.insert(item, to);
		}
	};
	
	
	
	class IconicAdapter extends ArrayAdapter<String> {
		IconicAdapter() {
			super(TouchListViewDemo.this, R.layout.row2, array);
		}
		
		public View getView(int position, View convertView,
												ViewGroup parent) {
			View row=convertView;
			
			if (row==null) {													
				LayoutInflater inflater=getLayoutInflater();
				
				row=inflater.inflate(R.layout.row2, parent, false);
			}
			
			TextView label=(TextView)row.findViewById(R.id.label);
			
			label.setText(array.get(position));
			
			return(row);
		}
	}
}
