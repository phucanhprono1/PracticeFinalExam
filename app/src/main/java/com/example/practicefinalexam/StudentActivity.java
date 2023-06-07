package com.example.practicefinalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.practicefinalexam.dbhelper.ClassHelper;
import com.example.practicefinalexam.dbhelper.StudentHelper;

public class StudentActivity extends AppCompatActivity {
    EditText stName,stDOB,stYear,stQue;
    StudentHelper studentHelper;
    RecyclerView rvStu;
    Bundle b = new Bundle();
    ClassHelper classHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        stName = findViewById(R.id.studentname);
        stDOB = findViewById(R.id.studentdob);
        stQue = findViewById(R.id.studentque);
        stYear = findViewById(R.id.studentyear);
        studentHelper = new StudentHelper(getBaseContext());
        classHelper = new ClassHelper(getBaseContext());
        rvStu = findViewById(R.id.student_list);
        int id = getIntent().getIntExtra("id",0);
        StudentAdapter studentAdapter = new StudentAdapter(studentHelper.getAllByClassId(id));


        findViewById(R.id.buttonAddStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentHelper.addStudenttoClass(stName.getText().toString(),stDOB.getText().toString(),stQue.getText().toString(),stYear.getText().toString(),id);
                stName.setText("");
                stDOB.setText("");
                stQue.setText("");
                stYear.setText("");
                studentAdapter.setList(studentHelper.getAllByClassId(id));
            }
        });
        rvStu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvStu.setAdapter(studentAdapter);
    }
}