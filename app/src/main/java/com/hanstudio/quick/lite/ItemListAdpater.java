package com.hanstudio.quick.lite;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by L on 2015-10-20.
 */
public class ItemListAdpater extends BaseAdapter {

    Context mContext;
    ArrayList<ItemItem> items = new ArrayList<>();


    public ItemListAdpater(Context context) {

        mContext = context;


    }

    public void addItem(ItemItem item){
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

        SetItemListView View = new SetItemListView(mContext);
        View.setTitle(items.get(position).getText());
        View.setSubTitle(items.get(position).getSub());
        View.setIcon(items.get(position).getItem());

        return  View;
    }
}
