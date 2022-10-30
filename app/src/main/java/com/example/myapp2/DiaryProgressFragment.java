package com.example.myapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapp2.database.DBHandler.ExerciseDBHandler;
import com.example.myapp2.database.DBHandler.MealsDBHandler;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryProgressFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiaryProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryProgressFragment newInstance(String param1, String param2) {
        DiaryProgressFragment fragment = new DiaryProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    double minutes, progress, target, calBurnt, calGained;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.diary_fragment_progress, container, false);
        TextView tar, food,exercise, totProg,totMin;

        getData();

       // tar = inf.findViewById(R.id.calVal);
        food = inf.findViewById(R.id.foodVal);
        exercise = inf.findViewById(R.id.exerciseVal);
        totProg = inf.findViewById(R.id.totalProgVal);
        totMin = inf.findViewById(R.id.totalMinVal);

        DecimalFormat formatter = new DecimalFormat("#0.00");

        //tar.setText(String.valueOf(target));
        food.setText(String.valueOf(formatter.format(calGained)));
        exercise.setText(String.valueOf(formatter.format(calBurnt)));
        totProg.setText(String.valueOf(formatter.format(progress)));
        totMin.setText(String.valueOf(formatter.format(minutes)));

        // Inflate the layout for this fragment
        return inf;
    }

    public void getData(){
        MealsDBHandler mealsDBHandler = new MealsDBHandler(getContext());
        ExerciseDBHandler exerciseDBHandler = new ExerciseDBHandler(getContext());

        calGained = mealsDBHandler.totalFoodCalories();
        calBurnt = exerciseDBHandler.totalCalBurnt();
        //change this
        //target = 0;
        progress = calGained - calBurnt;
        minutes = exerciseDBHandler.totalMinutes();
    }
}
