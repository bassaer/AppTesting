package com.github.bassaer.apptesting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nakayama on 2017/06/24.
 */

public class ListAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mLayoutInflater;

    public ListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.itemIcon = (ImageView) convertView.findViewById(R.id.item_icon);
            holder.itemText = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Item item = getItem(position);
        holder.itemIcon.setImageResource(item.getIcon());
        holder.itemText.setText(item.getText());
        return convertView;
    }

    private class ViewHolder {
        ImageView itemIcon;
        TextView itemText;
    }
}
