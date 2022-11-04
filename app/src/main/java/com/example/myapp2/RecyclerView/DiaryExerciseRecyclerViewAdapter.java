package com.example.myapp2.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp2.R;
import com.example.myapp2.models.ExerciseRecords;

import java.util.ArrayList;

public class DiaryExerciseRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryExerciseRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "diary.recyclerview.RecyclerViewAdapter";
    private ArrayList<ExerciseRecords> exercises;
    private Context mContext;

    private  final DiaryRecyclerViewInterface diaryRecyclerViewInterface;

    public DiaryExerciseRecyclerViewAdapter( Context mContext,ArrayList<ExerciseRecords> exercises, DiaryRecyclerViewInterface recyclerViewInterface) {
        this.exercises = exercises;
        this.mContext = mContext;
        this.diaryRecyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_exercise_tbl_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view,diaryRecyclerViewInterface);
        return viewHolder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.exercises.setText(exercises.get(position).getName());
//            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(TAG, "onClick: clicked on"+dayNames.get(position));
//
//                    Toast.makeText(mContext,dayNames.get(position), Toast.LENGTH_SHORT).show();
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView exercises;
        //RelativeLayout parentLayout;
        public MyViewHolder(@NonNull View itemView, DiaryRecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            exercises = itemView.findViewById(R.id.tblexercise);
            //parentLayout = itemView.findViewById(R.id.table_weight_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onExerciseItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
