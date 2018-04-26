package com.example.receivesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{

	String number;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle=intent.getExtras();
		SmsMessage[] msgs=null;
		String str="SMS from";
		if(bundle!=null)
		{
			Object[] pdus=(Object[]) bundle.get("pdus");
			msgs=new SmsMessage[pdus.length];
			for(int i=0;i<msgs.length;i++)
			{
		
			msgs[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
			if(i==0)
			{
				str +=msgs[i].getDisplayOriginatingAddress();
				number=msgs[i].getDisplayMessageBody();
				str +=":";
			}
			str +=msgs[i].getDisplayMessageBody().toString();
			
		}
			Toast.makeText(context, str, Toast.LENGTH_LONG).show();
			Log.d("SMSReceiver",str);
			//this.abortBroadcast();
	}

}
}
