package com.rajaappstore.detect_noice;


import java.io.IOException;
import android.media.MediaRecorder;

public class SoundMeter {
	    // This file is used to record voice
        static final private double EMA_FILTER = 0.6;

        private MediaRecorder mRecorder = null;
        private double mEMA = 0.0;

        public void start() {
               
        	if (mRecorder == null) {
                	
                        mRecorder = new MediaRecorder();
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mRecorder.setOutputFile("/dev/null"); 
                        
	                    try {
							mRecorder.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
                       mRecorder.start();
                       mEMA = 0.0;
                }
        }
        
        public void stop() {
                if (mRecorder != null) {
                        mRecorder.stop();       
                        mRecorder.release();
                        mRecorder = null;
                }
        }
        
        public double getAmplitude() {
                if (mRecorder != null)
                        //return  (mRecorder.getMaxAmplitude()/2700.0);
                return   20 * Math.log10(mRecorder.getMaxAmplitude() / 2700.0);
                else
                        return 0;

        }

        public double getAmplitudeEMA() {
                double amp = getAmplitude();
                mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
                return mEMA;
        }
}
