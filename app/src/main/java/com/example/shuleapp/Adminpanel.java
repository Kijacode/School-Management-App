package com.example.shuleapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shuleapp.Models.Analyticsmodel;

public class Adminpanel extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpanel);
        setCounts();
    }

    public void studentAdim(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public  void  staffAdim(View view)
    {
        Intent intent = new Intent(this,StaffActivity.class);
        startActivity(intent);
    }

    public  void courseAdim(View view)
    {
        Intent intent = new Intent(this,courseadmin.class);
        startActivity(intent);
    }

    public void analytics(View view)
    {
        Intent  intent = new Intent(this,Analytics.class);
        startActivity(intent);
    }

  public  void setCounts()
  {

      Analyticsmodel data = new Analyticsmodel();
      TextView ctotal = (TextView) findViewById(R.id.coursetotal);
      TextView studenttotal =(TextView) findViewById(R.id.tstudent);
      TextView stafftotal = (TextView)findViewById(R.id.stafftotal);
  int a = 0;
      String URL = "content://com.example.shuleapp.CourseProvider";
      String URL2 = "content://com.example.shuleapp.StudentProvider";
      String URL3 = "content://com.example.shuleapp.StaffProvider";

      Uri courses = Uri.parse(URL);
      Uri students = Uri.parse(URL2);
      Uri staff = Uri.parse(URL3);
      Cursor c = managedQuery(courses, null, null, null, null);
      Cursor c2 = managedQuery(students,null,null,null,null);
      Cursor c3 = managedQuery(staff,null,null,null,null);
      try{
          data.setStaff(String.valueOf(c3.getCount()));
          data.setCourse(String.valueOf(c.getCount()));
          data.setStaff(String.valueOf(c2.getCount()));
      }catch (Exception e)
      {
          System.out.println(e);
      }

//  System.out.println("courses: "+c.getCount());
//  studenttotal.setText(c2.getCount());

}}
