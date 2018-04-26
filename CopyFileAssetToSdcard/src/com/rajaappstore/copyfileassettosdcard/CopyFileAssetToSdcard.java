package com.rajaappstore.copyfileassettosdcard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class CopyFileAssetToSdcard extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("Files");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        
        for(String filename : files) {
        	System.out.println("File name => "+filename);
            InputStream in = null;
            OutputStream out = null;
            try {
              in = assetManager.open("Files/"+filename);   // if files resides inside the "Files" directory itself
              out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/" + filename);
              copyFile(in, out);
              in.close();
              in = null;
              out.flush();
              out.close();
              out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }       
    }
    
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
          out.write(buffer, 0, read);
        }
    }
}