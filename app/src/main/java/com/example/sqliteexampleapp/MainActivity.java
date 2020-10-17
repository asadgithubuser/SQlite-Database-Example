package com.example.sqliteexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import models.StudentsModel;
import models.UserModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//1
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insertData(new UserModel("asad", "dhaka", "23232"));
        helper.insertData(new UserModel("abdullah", "rajshahi", "111232"));
//
//        helper.updateData(new UserModel(1,"asadullah khan", " borishal bangladesh ", " 01710200334"));
//         helper.deleteData(new UserModel(2));


         //========== user data list =========
        List<UserModel> user = helper.getUserList();
        for(UserModel in: user){
            String data = "Id: "+in.getId()+ "name: "+in.getName()+"Address: "+in.getAddress()+"Phone: "+in.getPhone();

            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
            Log.d("data", data);
        }

//2
        helper.insertStudentsTable(new StudentsModel(1, "computer science", "bubt"));
        helper.insertStudentsTable(new StudentsModel(2, "Electrical ", "iut"));

        List<StudentsModel> students = helper.tableUserWithStudents();
        for(StudentsModel in: students){
            String data = " id : "+in.getId()+"Dept: "+in.getDept()+" University: "+in.getVersity();
            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        }


    }
}
