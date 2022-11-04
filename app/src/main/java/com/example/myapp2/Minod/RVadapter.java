package com.example.myapp2.Minod;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.ViewHolder> {

    List<stepsModel> stepList;
    rvClickListener listener;
    DBhelper DB;
    stepFunction LVP;

    public RVadapter(List<stepsModel> steps, rvClickListener listener){
        stepList  = steps;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.steps_log_entry, parent, false);
        DB = new DBhelper(context);
        return new ViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        stepsModel step = stepList.get(position);

        // Set item views based on your views and data model
        holder.stepTextView.setText(String.valueOf(step.getSteps()));
        holder.dateTextView.setText(String.valueOf(step.getDate()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.DeleteStepsData(stepList.get(position).getDate());
                stepList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), updateStepsInfo.class);
                intent.putExtra("date", stepList.get(position).getDate());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView stepTextView, dateTextView;
        public ImageButton edit,delete;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            stepTextView = (TextView) itemView.findViewById(R.id.stepCountRvEntry);
            dateTextView = (TextView) itemView.findViewById(R.id.dateRvEntry);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
            edit = (ImageButton) itemView.findViewById(R.id.edit);


            /*edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(LVP.getApplicationContext(), updateUserInfo.class);
                    LVP.startActivity(intent);

                }
            });*/

         /*   itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        DBhelper DB = new DBhelper(RVadapter.this);
                }
            });*/
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface rvClickListener{
        void onClick(View v, int position);
    }
}
