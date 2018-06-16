package com.example.velmurugan.expandablerecyclerviewexample;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.SingleTop;

@Parent
@SingleTop
@Layout(R.layout.header_layout)
public class HeaderView {

    private static String TAG = "HeaderView";

    @View(R.id.header_text)
    TextView headerText;

    private Context mContext;
    private String mHeaderText;

    public HeaderView(Context context,String headerText) {
        this.mContext = context;
        this.mHeaderText = headerText;
    }

    @Resolve
    private void onResolve(){
        Log.d(TAG, "onResolve");
        headerText.setText(mHeaderText);
    }

    @Expand
    private void onExpand(){
        Log.d(TAG, "onExpand");
    }

    @Collapse
    private void onCollapse(){
        Log.d(TAG, "onCollapse");
    }
}
