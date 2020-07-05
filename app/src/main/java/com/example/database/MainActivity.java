package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        //Inserting students
        Log.d("Insert: ","Inserting..");
        db.addStudent(new Student("Eustero", "0718273753"));
        db.addStudent(new Student("Mwai", "0718273743"));
        db.addStudent(new Student("Triser", "0718273723"));
        db.addStudent(new Student("Aguero", "0718263753"));
        db.addStudent(new Student("GreenWood", "0718253753"));

        //Reading Students
        Log.d("Reading:","Reading students..");
        List<Student> students = db.getAllStudents();

        for(Student sd : students){
            String log = "Id: " + sd.getID() + " ,NAME: " + sd.getName() + " ,PHONE " + sd.getPhone();

            //Writing contacts the the log
            Log.d("NAME: ", log);
        }



    }
}
