package com.rajaappstore.custom_chooser;

import java.util.Collections;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Custom_chooser extends Activity {
  AppAdapter adapter=null;
  Button btn1;
  Intent email = new Intent(Intent.ACTION_SEND);
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    btn1=(Button)findViewById(R.id.button1);
    btn1.setOnClickListener(new OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 show_custom_chooser();
		}
		
	});
  }
  public  void show_custom_chooser() {
		// TODO Auto-generated method stub
	  final Dialog dialog = new Dialog(Custom_chooser.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
		WMLP.gravity = Gravity.CENTER;
		dialog.getWindow().setAttributes(WMLP);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(R.layout.about_dialog);
		dialog.setCancelable(true);
		ListView lv=(ListView)dialog.findViewById(R.id.listView1);
		 PackageManager pm=getPackageManager();
			email.putExtra(Intent.EXTRA_EMAIL, new String[]{"velmurugan@androidtoppers.com"});          
			email.putExtra(Intent.EXTRA_SUBJECT, "Hi");
			email.putExtra(Intent.EXTRA_TEXT, "Hi,This is Test");
			email.setType("text/plain");
	List<ResolveInfo> launchables=pm.queryIntentActivities(email, 0);
	    
	    Collections.sort(launchables,
	                     new ResolveInfo.DisplayNameComparator(pm)); 
	    
	    adapter=new AppAdapter(pm, launchables);
	    lv.setAdapter(adapter);	
	    lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				ResolveInfo launchable=adapter.getItem(position);
			    ActivityInfo activity=launchable.activityInfo;
			    ComponentName name=new ComponentName(activity.applicationInfo.packageName,
			                                         activity.name);
				email.addCategory(Intent.CATEGORY_LAUNCHER);
				email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
			                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				email.setComponent(name);
			    startActivity(email);    
			}
		});	
		dialog.show();
	}
  
  class AppAdapter extends ArrayAdapter<ResolveInfo> {
    private PackageManager pm=null;
    
    AppAdapter(PackageManager pm, List<ResolveInfo> apps) {
      super(Custom_chooser.this, R.layout.row, apps);
      this.pm=pm;
    }
    
    @Override
    public View getView(int position, View convertView,
                          ViewGroup parent) {
      if (convertView==null) {
        convertView=newView(parent);
      }
      
      bindView(position, convertView);
      
      return(convertView);
    }
    
    private View newView(ViewGroup parent) {
      return(getLayoutInflater().inflate(R.layout.row, parent, false));
    }
    
    private void bindView(int position, View row) {
      TextView label=(TextView)row.findViewById(R.id.label);
      
      label.setText(getItem(position).loadLabel(pm));
      
      ImageView icon=(ImageView)row.findViewById(R.id.icon);
      
      icon.setImageDrawable(getItem(position).loadIcon(pm));
    }
  }
}