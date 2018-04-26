package com.example.listviewanimation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class MainActivity extends Activity {
 
 private ListView listview;
 private DisplayMetrics metrics;
 
 private int mode = 1;
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
   
  metrics = new DisplayMetrics();
  getWindowManager().getDefaultDisplay().getMetrics(metrics);
 
  listview = new ListView(this);
  listview.setFadingEdgeLength(0);
  ArrayList<String> strings = new ArrayList<String>();
 
  for (int i = 0; i < 300; i++) {
   strings.add("Item:#" + (i + 1));
  }
 
  MainAdapter mAdapter = new MainAdapter(this, strings, metrics);
  listview.setAdapter(mAdapter);
  setContentView(listview);
 }
 
 public boolean onCreateOptionsMenu(Menu menu) {
  boolean result = super.onCreateOptionsMenu(menu);
  menu.add(Menu.NONE, 1, 0, "TranslateAnimation1");
  menu.add(Menu.NONE, 2, 0, "TranslateAnimation2");
  menu.add(Menu.NONE, 3, 0, "ScaleAnimation");
  menu.add(Menu.NONE, 4, 0, "fade_in");
  menu.add(Menu.NONE, 5, 0, "hyper_space_in");
  menu.add(Menu.NONE, 6, 0, "hyper_space_out");
  menu.add(Menu.NONE, 7, 0, "wave_scale");
  menu.add(Menu.NONE, 8, 0, "push_left_in");
  menu.add(Menu.NONE, 9, 0, "push_left_out");
  menu.add(Menu.NONE, 10, 0, "push_up_in");
  menu.add(Menu.NONE, 11, 0, "push_up_out");
  menu.add(Menu.NONE, 12, 0, "shake");
  return result;
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  mode = item.getItemId();
  return super.onOptionsItemSelected(item);
 }
 
 public class MainAdapter extends ArrayAdapter<String> {
  private Context context;
  private LayoutInflater mInflater;
  private ArrayList<String> strings;
  private DisplayMetrics metrics_;
 
  private class Holder {
   public TextView textview;
  }
 
  public MainAdapter(Context context, ArrayList<String> strings,
    DisplayMetrics metrics) {
   super(context, 0, strings);
   this.context = context;
   this.mInflater = (LayoutInflater) this.context
     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   this.strings = strings;
   this.metrics_ = metrics;
  }
 
  @Override
  public View getView(final int position, View convertView,
    ViewGroup parent) {
   final String str = this.strings.get(position);
   final Holder holder;
 
   if (convertView == null) {
    convertView = mInflater.inflate(
      android.R.layout.simple_list_item_1, null);
    convertView.setBackgroundColor(0xFF202020);
 
    holder = new Holder();
    holder.textview = (TextView) convertView
      .findViewById(android.R.id.text1);
    holder.textview.setTextColor(0xFFFFFFFF);
    holder.textview.setBackgroundResource(R.drawable.ic_launcher);
 
    convertView.setTag(holder);
   } else {
    holder = (Holder) convertView.getTag();
   }
 
   holder.textview.setText(str);
 
   Animation animation = null;
 
   
    animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_top);
 
   animation.setDuration(200);
   convertView.startAnimation(animation);
   animation = null;
 
   return convertView;
  }
 }
}

