package com.lazypaddy.resistorcalculator;

/**
 * Created by edgano on 15/03/2015.
 */
// ColorSpinnerAdapter.java
    import java.util.ArrayList;
    //import models.ColorSpinnerAdapterItem;
    import android.content.Context;
    import android.graphics.Color;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.SpinnerAdapter;
    import android.widget.TextView;

    public class ColorSpinnerAdapter extends ArrayAdapter<ColorSpinnerAdapterItem> implements SpinnerAdapter {
        private  ArrayList<ColorSpinnerAdapterItem> colors; // android.graphics.Color list
        //private Context context;

        public ColorSpinnerAdapter(Context context, int textViewResourceId, ArrayList<ColorSpinnerAdapterItem> colors) {
            super(context, textViewResourceId, colors);
            //this.context = context;
            this.colors = colors;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            super.getDropDownView(position, convertView, parent);
            if (convertView == null) {
                convertView = View.inflate(getContext(), android.R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setBackgroundColor(colors.get(position).getColor());
            tv.setTextColor(colors.get(position).getDisplayNameColor());
            tv.setText(colors.get(position).getDisplayName());
            return convertView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            tv.setBackgroundColor(colors.get(position).getColor());
            tv.setTextColor(colors.get(position).getDisplayNameColor());
            tv.setText(colors.get(position).getDisplayName());
            return view;
        }
    }