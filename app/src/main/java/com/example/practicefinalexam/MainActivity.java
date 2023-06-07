package com.example.practicefinalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practicefinalexam.dbhelper.ClassHelper;
import com.example.practicefinalexam.dbhelper.DatabaseHelper;
import com.example.practicefinalexam.dbhelper.StudentHelper;

public class MainActivity extends AppCompatActivity implements ClassAdapter.ClassClickListener {
    EditText edName,EdDescription;
    Button add;
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    StudentHelper studentHelper;
    ClassHelper classHelper;
    ClassAdapter classAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.classname);
        EdDescription = findViewById(R.id.description_class);
        recyclerView = findViewById(R.id.class_list);

        classHelper = new ClassHelper(getBaseContext());
        studentHelper = new StudentHelper(getBaseContext());

        studentAdapter = new StudentAdapter(studentHelper.getAllNamnam2());
        classAdapter = new ClassAdapter(classHelper.getAll(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(classAdapter);
        findViewById(R.id.buttonClass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classHelper.insertClass(edName.getText().toString(),EdDescription.getText().toString());
                edName.setText("");
                EdDescription.setText("");
                classAdapter.setList(classHelper.getAll());
            }
        });
        findViewById(R.id.btn_desc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classAdapter.setList(classHelper.getDesc());
                recyclerView.setAdapter(classAdapter);
            }
        });
        findViewById(R.id.nam_nam2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentAdapter.setList(studentHelper.getAllNamnam2());
                recyclerView.setAdapter(studentAdapter);
            }
        });
    }

    @Override
    public void onClassClick(CreditClass creditClass) {
        Intent StudentIntent = new Intent(this,StudentActivity.class);
        StudentIntent.putExtra("id",creditClass.id);
        StudentIntent.putExtra("name",creditClass.name);
        StudentIntent.putExtra("description",creditClass.description);
        startActivity(StudentIntent);

    }
    @Override
    public void onResume(){
        super.onResume();
        classAdapter = new ClassAdapter(classHelper.getAll(),this);
        recyclerView.setAdapter(classAdapter);
    }
}