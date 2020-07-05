package com.example.database;

public class Student {
    int _id;
    String _name;
    String _phone;
    public  Student(){}

    public Student(int id, String name, String _phone) {
        this._id = id;
        this._name = name;
        this._phone = _phone;

    }

    public Student(String name, String _phone) {
        this._name = name;
        this._phone = _phone;

    }

    public int getID(){
        return  this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getPhone(){
        return this._phone;
    }

    public void setPhone(String phone){
        this._phone = phone;
    }


}
