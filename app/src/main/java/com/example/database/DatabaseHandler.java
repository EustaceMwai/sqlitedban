package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsDb";
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE= "phone";


    public DatabaseHandler(Context context){
     super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_STUDENTS_TABLE = "CREATE TABLE "+TABLE_STUDENTS+ "("
            +KEY_ID+ "INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"
            +KEY_PHONE+"TEXT"+")";
    db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_STUDENTS);

        onCreate(db);
    }

    void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PHONE, student.getPhone());

        db.insert(TABLE_STUDENTS,null,values);

        db.close();
    }

    Student getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{ KEY_ID,
        KEY_NAME, KEY_PHONE}, KEY_ID + "=?",
                new String[] {String.valueOf(id)},null,null,null,null);
        if(cursor!= null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return student;

    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);



        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setID(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setPhone(cursor.getString(2));

                studentList.add(student);

            }
            while (cursor.moveToNext());
        }
        return  studentList;
    }

    //updateStudent

    public  int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PHONE, student.getPhone());

        //update row
        return db.update(TABLE_STUDENTS, values, KEY_ID + "=?",
                new String[] { String.valueOf(student.getID())}
                );

    }

    //deleteStudent
    public void deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + "=?",
                new String[] {String.valueOf(student.getID())});
        db.close();
    }

    //getting studentsCount
    public int getStudentsCount() {
      String countQuery = "SELECT * FROM "+ TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }



}
