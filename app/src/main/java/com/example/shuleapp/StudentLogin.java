package com.example.shuleapp;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class StudentLogin extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseview_activity);

         onClickRetrieveStudents();
    }


    public void onClickRetrieveStudents() {
        // Retrieve student records
        String URL = "content://com.example.shuleapp.CourseProvider";
        Uri course = Uri.parse(URL);
        Cursor c = managedQuery(course, null, null, null, null);
        CourseAdapter adapter = new CourseAdapter(getBaseContext(),c,0);
        ListView listView = (ListView) findViewById(R.id.listView);
         listView.setAdapter(adapter);
//    int total = c.getCount();


    }
}
