package com.example.modelclassandadpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class student_adapter extends RecyclerView.Adapter<student_adapter.Myviewholde> {
    private List<student_model> studentList;

    public student_adapter(List<student_model> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public student_adapter.Myviewholde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.student_cart,parent,false);

        return new student_adapter.Myviewholde(view);
    }


    @Override
    public void onBindViewHolder(@NonNull student_adapter.Myviewholde holder, int position) {
          student_model students=studentList.get(position);

          holder.shw_name.setText(students.name);
        holder.shw_age.setText(students.age+"");
        holder.shw_grade.setText(students.grade+"");


    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class Myviewholde extends RecyclerView.ViewHolder {
        TextView shw_name,shw_age,shw_grade;
        public Myviewholde(@NonNull View itemView) {
            super(itemView);

            shw_name=itemView.findViewById(R.id.show_name);
            shw_age=itemView.findViewById(R.id.show_age);
            shw_grade=itemView.findViewById(R.id.show_grade);

        }
    }


}
