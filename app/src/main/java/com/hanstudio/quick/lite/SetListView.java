package com.hanstudio.quick.lite;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by L on 2015-10-20.
 */
public class SetListView extends RelativeLayout{

    public SetListView(Context context) {
        super(context);
        init(context);
    }


    public void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.set_list_view, this);


    }


    public void setTitle(String text){

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(text);
        title.setTypeface(Typeface.MONOSPACE);

    }

    public void setSubTitle(String text){
        TextView sub = (TextView) findViewById(R.id.sub);
        sub.setText(text);
        sub.setTypeface(Typeface.MONOSPACE);

    }


    public void setTileColor(int colorTitle, int colorSub){
        TextView title = (TextView) findViewById(R.id.title);
        TextView sub = (TextView) findViewById(R.id.sub);
        title.setTextColor(colorTitle);
        sub.setTextColor(colorSub);
    }


}
