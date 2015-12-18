package com.project.myschedule;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by sushil on 12/17/15.
 */
public class ScheduleAdapter extends ArrayAdapter <String> {
    private final Activity context;
    private final String[] title;
    private final String[] to;
    private final String[] from;


    public ScheduleAdapter(Activity context,
                           String[] title, String[] to, String[] from) {
        super(context, R.layout.listlayout, title);
        this.context = context;
        this.title= title;
        this.to = to;
        this.from = from;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //print the index of the row to examine
        Log.d("CustomArrayAdapter", String.valueOf(position));
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listlayout, null, true);

        //get a reference to all the views on the xml layout
        TextView txtTitle = (TextView) rowView.findViewById(R.id.schedule_title);
        TextView txtToDate = (TextView) rowView.findViewById(R.id.schedule_to_date);
        TextView txtFromDate = (TextView) rowView.findViewById(R.id.schedule_from_date);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //customize the content of each row based on position
        txtTitle.setText(title[position]);
        txtFromDate.setText(from[position]);
        txtToDate.setText(to[position]);
        //imageView.setImageResource(imageIds[position]);
        return rowView;
    }
}

