package com.project.myschedule;

/**
 * Created by sushil on 12/24/15.
 */
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView scheduleTitle;
        TextView toDate;
        TextView fromDate;
        Button edit;
        Button delete;

        public DataObjectHolder(View itemView) {
            super(itemView);
            scheduleTitle = (TextView) itemView.findViewById(R.id.schedule_title);
            toDate = (TextView) itemView.findViewById(R.id.schedule_to_date);
            fromDate = (TextView) itemView.findViewById(R.id.schedule_from_date);
            edit = (Button) itemView.findViewById(R.id.btn_edit);
            delete = (Button) itemView.findViewById(R.id.btn_delete);
            DataBase delete = new DataBase(MyActivity.appContext);

            //action to button
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }




        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
            Log.i(LOG_TAG, "hmmmmmmm " + getPosition());
            Context context = itemView.getContext();
            Intent intent = new Intent(context, TaskListActivity.class);
            intent.putExtra("index",""+getPosition());
            context.startActivity(intent);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        holder.scheduleTitle.setText(mDataset.get(position).getScheduleTitle());
        holder.toDate.setText(mDataset.get(position).getToDate());
        holder.fromDate.setText(mDataset.get(position).getFromDate());

        //action to delete button
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase delete = new DataBase(MyActivity.appContext);
                delete.open();
                delete.deleteSchedule(mDataset.get(position).getScheduleId());
                delete.close();
                deleteItem(position);
                //MyRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }


    public void updateResults(ArrayList<DataObject> results) {
        mDataset = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

}


