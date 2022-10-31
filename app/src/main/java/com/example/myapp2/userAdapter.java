package com.example.myapp2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.Bolder> {

    private Context context;
    private ArrayList<Model> arrayList;


    public userAdapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Bolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new Bolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Bolder holder, @SuppressLint("RecyclerView") int position) {

        Model model = arrayList.get(position);


        String id = model.getId();
        String name=model.getName();
        String age= model.getAge();
        String birth = model.getBirth();
        String email = model.getEmail();
        String add = model.getAddtime();
        String update = model.getUpdatetime();

        holder.name.setText(name);
        holder.age.setText(age);
        holder.email.setText(email);
        holder.birth.setText(birth);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                    ""+position,
                ""+id,
                ""+name,
                ""+age,
                ""+email,
                ""+birth,
                ""+add,
                ""+update

                );
            }
        });
        
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog();
            }
        });


    }

    private void editDialog(String position, String id, String name, String age, String email, String birth, String add, String update) {

      AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Do you want to update? ");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, UpdateUserActivity.class);
                intent.putExtra("ID " , id);
                intent.putExtra("Name" , name);
                intent.putExtra("Age" ,age);
                intent.putExtra("email" , email);
                intent.putExtra("Birth", birth);
                intent.putExtra("addTime",add);
                intent.putExtra("update" , update);
                intent.putExtra("editMode", true);

                context.startActivity(intent);
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.create().show();

    }

    private void editDialog() {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Bolder extends RecyclerView.ViewHolder{

        TextView name, age , birth, email;
        Button edit;

        public Bolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.Age);
            birth = itemView.findViewById(R.id.birth);
            email = itemView.findViewById(R.id.Email);
            edit = itemView.findViewById(R.id.edbtn);
        }
    }
}
