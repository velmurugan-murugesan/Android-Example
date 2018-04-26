package com.rajaappstore.sharelinkinfacebook;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Share_link extends Activity {
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String urlToShare = "http://androidtoppers.com";
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
				// See if official Facebook app is found
				boolean facebookAppFound = false;
				List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
				for (ResolveInfo info : matches) {
				    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
				        intent.setPackage(info.activityInfo.packageName);
				        facebookAppFound = true;
				        break;
				    }
				}
				//If facebook app not found, load sharer.php in a browser
				if (!facebookAppFound) {
				    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
				    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
				}
				startActivity(intent);
			}
		});
	}
}
