package com.project.myschedule;

/**
 * Created by cruzor on 1/11/16.
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

public class TaskRecyclerAdapter extends RecyclerView
        .Adapter<TaskRecyclerAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<TaskDataObject> mDataset;
    private static MyClickListener myClickListener;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView taskTitle;
        TextView description;
        Button toggleNotification;
        TextView startEndTime;


        public DataObjectHolder(View itemView) {
            super(itemView);
            taskTitle= (TextView) itemView.findViewById(R.id.schedule_title);
            description= (TextView) itemView.findViewById(R.id.schedule_to_date);
            toggleNotification= (Button) itemView.findViewById(R.id.btn_edit);
            startEndTime = (Button) itemView.findViewById(R.id.btn_delete);
            DataBase delete = new DataBase(MyActivity.appContext);


        }




        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
            Log.i(LOG_TAG, "hmmmmmmm " + getPosition());

        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public TaskRecyclerAdapter (ArrayList<TaskDataObject> myDataset) {
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
        holder.taskTitle.setText(mDataset.get(position).getTaskName());
        holder.description.setText(mDataset.get(position).getDescription());
        holder.startEndTime.setText(mDataset.get(position).getStartEndTime());

        //action to delete button
        //holder.delete.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
           //     DataBase delete = new DataBase(MyActivity.appContext);
            //    delete.open();
            //    delete.deleteSchedule(mDataset.get(position).getScheduleId());
             //   delete.close();
             //   deleteItem(position);
                //MyRecyclerViewAdapter.notifyDataSetChanged();
            }
        //});

    //}

    public void addItem(TaskDataObject dataObj, int index) {
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


    public void updateResults(ArrayList<TaskDataObject> results) {
        mDataset = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

}


