package com.example.myapp2.Minod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mealsRVadapter extends RecyclerView.Adapter<mealsRVadapter.ViewHolder> {

    List<mealPlanModel> mealList;
    rvClickListener listener;
    DBhelper DB;
    stepFunction LVP;

    public mealsRVadapter(List<mealPlanModel> steps){
        mealList = steps;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.meal_plan_entry, parent, false);
        DB = new DBhelper(context);
        return new ViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mealPlanModel meal = mealList.get(position);

        StringBuilder SB = new StringBuilder();
        SB.append(String.valueOf(meal.getCalories())).append(" calories, with ")
                .append(meal.getCarbs()).append("g of Carbs, ")
                .append(meal.getProteins()).append("g of Protein, and ")
                .append(meal.getFats()).append("g of Fats, ");

        // Set item views based on your views and data model
        holder.nameTextView.setText(String.valueOf(meal.getName()));
        holder.descTextView.setText(String.valueOf(meal.getDescription()));
        holder.caloriesTextView.setText(SB);
        holder.typeTextView.setText(String.valueOf(meal.getType()));
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView, descTextView, caloriesTextView, typeTextView ;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.mealNameRvEntry);
            descTextView = (TextView) itemView.findViewById(R.id.descRvEntry);
            caloriesTextView = (TextView) itemView.findViewById(R.id.caloriesRvEntry);
            typeTextView = (TextView) itemView.findViewById(R.id.typeRvEntry);

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
            //itemView.setOnClickListener(this);
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
