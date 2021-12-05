package com.hanstudio.quick.lite;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by L on 2015-10-20.
 */
public class ItemItem {

    String text;
    String sub;
    Drawable item;


    public ItemItem() {

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }


    public String getSub() {

        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Drawable getItem() {
        return item;
    }

    public void setItem(Drawable item) {
        this.item = item;
    }
}


