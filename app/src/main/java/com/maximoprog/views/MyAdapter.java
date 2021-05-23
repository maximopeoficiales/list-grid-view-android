package com.maximoprog.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> names;

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // EL Problema es que solo renderiza las vistas que puedan observadas en la pantalla, entonces
        // cuando uno hace scroll , se renderiza la vista en ese momento, entonces
        // al usar el findViewById , este proceso es un pesado

//        se instancia el viewHolder
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTextView.setText(names.get(position));
        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;
    }
}
