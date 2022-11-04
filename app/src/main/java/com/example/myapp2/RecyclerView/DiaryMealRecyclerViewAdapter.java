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
import com.example.myapp2.models.MealRecords;

import java.util.ArrayList;

public class DiaryMealRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryMealRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "diary.recyclerview.RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<MealRecords> mealRecords;

    private final DiaryRecyclerViewInterface recyclerViewInterface;

    public DiaryMealRecyclerViewAdapter(Context context, ArrayList<MealRecords> mealRecords,
               DiaryRecyclerViewInterface recyclerViewInterface){
        this.mContext = context;
        this.mealRecords = mealRecords;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the layout by giving a look to the rows
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_meal_tbl_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view,recyclerViewInterface,mealRecords);
        return viewHolder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //assigning values to each of the views created in the layout file
        //based on the position of the recycler view
        Log.d(TAG, "onBindViewHolder: called");

        holder.meals.setText(mealRecords.get(position).getName());
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
        //no of items to be displayed
        return mealRecords.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
    //like an onCreate method assigns the views in the recycler view layout to variables
        TextView meals;
        //RelativeLayout parentLayout;
        public MyViewHolder(@NonNull View itemView, DiaryRecyclerViewInterface recyclerViewInterface,ArrayList<MealRecords> mealRecords) {
            super(itemView);
            meals = itemView.findViewById(R.id.tblmeal);
            //parentLayout = itemView.findViewById(R.id.table_weight_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            System.out.println(mealRecords.get(pos).getType());
                            recyclerViewInterface.onMealItemClick(pos,mealRecords.get(pos).getType());
                        }
                    }
                }
            });
        }
    }

}
