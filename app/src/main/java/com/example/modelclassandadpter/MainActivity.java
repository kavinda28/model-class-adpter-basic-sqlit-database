package com.example.modelclassandadpter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private EditText edtname,edtage,edtgrade;
private Button add,view;
private RecyclerView studentlist_view;
private List<student_model> studentList=new ArrayList<>();
private student_adapter student_adapter;
LinearLayout edittext_fileds;
Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

database=new Database(MainActivity.this);

        edtname =findViewById(R.id.name);
        edtage=findViewById(R.id.age);
        edtgrade=findViewById(R.id.grade);

        view=findViewById(R.id.view);
        add=findViewById(R.id.add);

        studentlist_view=findViewById(R.id.studentlist_view);//recycal view
        studentlist_view.setVisibility(View.GONE);
        edittext_fileds=findViewById(R.id.inputrefer);//linear layout
        edittext_fileds.setVisibility(View.VISIBLE);

        add.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_model students=new student_model();
                students.name=edtname.getText().toString();
                students.age=Integer.parseInt(edtage.getText().toString());
                students.grade=Integer.parseInt(edtgrade.getText().toString());
              //  studentList.add(students);
                 Boolean isInsert=database.insertStudent(students);
                System.out.println("_is_insert_"+isInsert);

                edtname.setText("");
                edtage.setText("");
                edtgrade.setText("");

            }
        }));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentList=database.getStudents();
                student_adapter=new student_adapter(studentList);
                studentlist_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                studentlist_view.setAdapter(student_adapter);

                studentlist_view.setVisibility(View.VISIBLE);
                edittext_fileds.setVisibility(View.GONE);
            }
        });



    }
}
