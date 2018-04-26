package com.sunil.address;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.addressfromlatlong.R;


public class MainActivity extends Activity implements OnClickListener{

	private EditText result;
	private Button btngetAddress;
	private Context context=null;
	private ProgressDialog dialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=this;
		
		result=(EditText)findViewById(R.id.editText_result);
		btngetAddress=(Button)findViewById(R.id.button_getAddress);
		btngetAddress.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View arg0) {
		
		dialog = ProgressDialog.show(context, "","Please wait..", true);
		GetCurrentAddress currentadd=new GetCurrentAddress();
		   currentadd.execute();
	}

	
	public  String getAddress(Context ctx, double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
				
				String locality=address.getLocality();
				String city=address.getCountryName();
				String region_code=address.getCountryCode();
				String zipcode=address.getPostalCode();
				double lat =address.getLatitude();
				double lon= address.getLongitude();
			
                result.append(locality+" ");
                result.append(city+" "+ region_code+" ");
				result.append(zipcode);
				
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
	
	private class GetCurrentAddress extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
	// this lat and log we can get from current location but here we given hard coded
			double latitude=13.452296401374042;
			double longitude=82.03048805706203;
			
		String address=	getAddress(context, latitude, longitude);
			return address;
		}

		@Override
		protected void onPostExecute(String resultString) {
			dialog.dismiss();
			result.setText(resultString);
	
		}
	}
}
