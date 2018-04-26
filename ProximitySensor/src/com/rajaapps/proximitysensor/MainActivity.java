package com.rajaapps.proximitysensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	 TextView ProximitySensor, ProximityMax, ProximityReading;

	 SensorManager mySensorManager;
	 Sensor myProximitySensor;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        ProximitySensor = (TextView)findViewById(R.id.proximitySensor);
	        ProximityMax = (TextView)findViewById(R.id.proximityMax);
	        ProximityReading = (TextView)findViewById(R.id.proximityReading);
	        // Now in the SensorActivity we access the device sensor using SensorManager, an instance of this class is got by calling getSystemService(SENSOR_SERVICE) . We implement the class with theSensorEventListener interface to override its methods to do actions on sensor value change.
	        mySensorManager = (SensorManager)getSystemService(
	          Context.SENSOR_SERVICE);
	        myProximitySensor = mySensorManager.getDefaultSensor(
	          Sensor.TYPE_PROXIMITY);
	        
	        if (myProximitySensor == null){
	         ProximitySensor.setText("No Proximity Sensor!"); 
	        }else{
	         ProximitySensor.setText(myProximitySensor.getName());
	         ProximityMax.setText("Maximum Range: "
	           + String.valueOf(myProximitySensor.getMaximumRange()));
	         mySensorManager.registerListener(proximitySensorEventListener,
	           myProximitySensor,
	           SensorManager.SENSOR_DELAY_NORMAL);
	        }
	    }
	    
	    SensorEventListener proximitySensorEventListener
	    = new SensorEventListener(){

	  @Override
	  public void onAccuracyChanged(Sensor sensor, int accuracy) {
	   // TODO Auto-generated method stub
	   
	  }

	  @Override
	  public void onSensorChanged(SensorEvent event) {
	   // TODO Auto-generated method stub

	   if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
	   {
	    ProximityReading.setText("Proximity Sensor Reading:"
	      + String.valueOf(event.values[0]));
	   
	   
	   }
	  }
	    };
	}