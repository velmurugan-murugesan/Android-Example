package com.example.velmurugan.expandablerecyclerviewexample;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
        headerText.setText(mHeaderText);
    }

    @Expand
    private void onExpand(){
        Toast.makeText(mContext, "onExpand "+mHeaderText, Toast.LENGTH_SHORT).show();
    }

    @Collapse
    private void onCollapse(){
        Toast.makeText(mContext, "onCollapse "+mHeaderText, Toast.LENGTH_SHORT).show();
    }
}
