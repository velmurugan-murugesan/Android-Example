package cust.list;
import java.util.zip.Inflater;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Customiselistview extends Activity {
    /** Called when the activity is first created. */
    
    ListView l1;
    Button b1;
    String[] text = { "pic1","pic2","pic3","pic4"};
    int[] image = { R.drawable.andro1, R.drawable.andro2, R.drawable.andro1,
            R.drawable.andro2};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        l1=(ListView)findViewById(R.id.listView1);
        l1.setAdapter(new MycustomAdapter(text,image));
    }
    
    class MycustomAdapter extends BaseAdapter
    {
      String[] datatext;
      int[] dataimage;
      MycustomAdapter()
      {
      
      }
      MycustomAdapter(String[] a,int[] b)
      {
    	  datatext=a;
    	  dataimage=b;
      }
		public int getCount() {
			// TODO Auto-generated method stub
			return datatext.length;
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater =getLayoutInflater();
			View row=inflater.inflate(R.layout.atach,parent,false);
			TextView tv=(TextView)row.findViewById(R.id.textView1);
			ImageView iv=(ImageView)row.findViewById(R.id.imageView1);
			tv.setText(datatext[position]);
			iv.setImageResource(dataimage[position]);
			return row;
		}
    }
}