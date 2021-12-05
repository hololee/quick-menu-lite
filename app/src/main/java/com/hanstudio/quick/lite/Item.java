package com.hanstudio.quick.lite;

/**
 * Created by L on 2015-10-20.
 */
public class Item {

    String text;
    String sub;


    public Item() {

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
}
