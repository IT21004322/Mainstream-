package com.example.myapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sculptr.database.DBHandler.MealsDBHandler;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryCaloriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryCaloriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiaryCaloriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryCaloriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryCaloriesFragment newInstance(String param1, String param2) {
        DiaryCaloriesFragment fragment = new DiaryCaloriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    double carbohydrates, proteins, fats, vitamins, minerals, fibre, total;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.diary_fragment_calories, container, false);
        TextView carb, prot,fat,vit,min, fib, tot;

        getData();

        carb = inf.findViewById(R.id.carbVal);
        prot = inf.findViewById(R.id.protVal);
        fat = inf.findViewById(R.id.fatVal);
        vit = inf.findViewById(R.id.vitVal);
        min = inf.findViewById(R.id.minVal);
        fib = inf.findViewById(R.id.fibreVal);
        tot = inf.findViewById(R.id.totalVal);

        DecimalFormat formatter = new DecimalFormat("#0.00");

        carb.setText(String.valueOf(formatter.format(carbohydrates)));
        prot.setText(String.valueOf(formatter.format(proteins)));
        fat.setText(String.valueOf(formatter.format(fats)));
        vit.setText(String.valueOf(formatter.format(vitamins)));
        min.setText(String.valueOf(formatter.format(minerals)));
        fib.setText(String.valueOf(formatter.format(fibre)));
        tot.setText(String.valueOf(formatter.format(total)));

        // Inflate the layout for this fragment
        return inf;
    }

    public void getData(){
        MealsDBHandler dbHandler = new MealsDBHandler(getContext());

        carbohydrates = dbHandler.totalCarbohydrates();
        proteins = dbHandler.totalProteins();
        fats = dbHandler.totalFats();
        vitamins = dbHandler.totalVitamins();
        minerals = dbHandler.totalMinerals();
        fibre = dbHandler.totalFibre();

        total = carbohydrates + proteins + fats + vitamins + minerals + fibre;
    }
}