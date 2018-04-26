package com.rajaappstore.missedcallalert;

import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.os.Bundle;  
import android.telephony.PhoneStateListener;  
import android.telephony.TelephonyManager;  
import android.telephony.gsm.SmsManager;
import android.util.Log;  
import android.widget.Toast;
  
public class MainActivity extends BroadcastReceiver {  
  
    private static final String TAG = "BroadcastReceiver";  
    Context mContext;  
    String incoming_nr;  
    private int prev_state;  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); //TelephonyManager object  
        CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();  
        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE); //Register our listener with TelephonyManager  
        Bundle bundle = intent.getExtras();  
        String phoneNr= bundle.getString("incoming_number");  
        Log.v(TAG, "phoneNr: "+phoneNr);  
        mContext=context;  
    }  
        /* Custom PhoneStateListener */  
    public class CustomPhoneStateListener extends PhoneStateListener {  
  
        private static final String TAG = "CustomPhoneStateListener";  
  
        @Override  
        public void onCallStateChanged(int state, String incomingNumber){  
  
            if(incomingNumber!=null&&incomingNumber.length()>0) incoming_nr=incomingNumber;   
            switch(state){  
                case TelephonyManager.CALL_STATE_RINGING:  
                        Log.d(TAG, "CALL_STATE_RINGING");  
                        prev_state=state;  
                        break;  
                case TelephonyManager.CALL_STATE_OFFHOOK:  
                Log.d(TAG, "CALL_STATE_OFFHOOK");  
                prev_state=state;  
                break;  
                case TelephonyManager.CALL_STATE_IDLE:  
                    Log.d(TAG, "CALL_STATE_IDLE==>"+incoming_nr);  
                    if((prev_state==TelephonyManager.CALL_STATE_OFFHOOK)){  
                        prev_state=state;  
                        //Answered Call which is ended
                        Toast.makeText(mContext, "answered call end", 5).show();
                    }  
                    if((prev_state==TelephonyManager.CALL_STATE_RINGING)){  
                        prev_state=state;  
                        //Rejected or Missed call  
                        Toast.makeText(mContext, " missed call end"+incomingNumber, 5).show();
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage("5554", null, "Missed call from"+incomingNumber, null, null);
                       
                    }  
                    break;  
            }  
        }  
    }  
}  