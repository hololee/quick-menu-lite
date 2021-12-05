package com.hanstudio.quick.lite;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by L on 2015-10-20.
 */
public class ListAdpater extends BaseAdapter {

    Context mContext;
    ArrayList<Item> items = new ArrayList<>();


    public ListAdpater(Context context) {

        mContext = context;


    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SetListView View = new SetListView(mContext);

        if (position == 0) {

            SharedPreferences pref = mContext.getSharedPreferences("event", Context.MODE_PRIVATE);
            if (pref.getBoolean("unlock", false)) {
                View.setTileColor(Color.argb(170, 0, 0, 0), Color.argb(80, 0, 0, 0));

            } else {
                View.setTileColor(Color.argb(60, 0, 0, 0), Color.argb(60, 0, 0, 0));
            }

        }

        if (position == 2) {
            View.setTileColor(Color.argb(60, 0, 0, 0), Color.argb(60, 0, 0, 0));
        }

        if (position == 3) {
            View.setTileColor(Color.argb(200, 255, 0, 0), Color.argb(80, 0, 0, 0));
        }


        View.setTitle(items.get(position).getText());
        View.setSubTitle(items.get(position).getSub());


        return View;
    }
}
