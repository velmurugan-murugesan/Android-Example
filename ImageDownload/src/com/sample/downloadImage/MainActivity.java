package com.sample.downloadImage;
 
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
 
public class downloadImage extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        Bitmap bitmap = DownloadImage(
            "http://www.allindiaflorist.com/imgs/arrangemen4.jpg");
        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageBitmap(bitmap);
    }
   
    private InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        InputStream in = null;
        int response = -1;
                
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
                  
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
         
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect(); 
 
            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");            
        }
        return in;     
    }
    private Bitmap DownloadImage(String URL)
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;                
    }
}