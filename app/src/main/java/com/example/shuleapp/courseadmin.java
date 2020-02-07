package com.example.shuleapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuleapp.Models.Course;
import com.facebook.stetho.Stetho;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class courseadmin extends Activity {

    Course course = new Course();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseadmin);
        Stetho.initializeWithDefaults(this);
        courseSpinner();
        coursePrioritySpinner();

    }

    public void saveCourse(View view)
    {
        EditText cname = (EditText)findViewById(R.id.cname);
        EditText ccode = (EditText)findViewById(R.id.ccode);
        EditText cgrade = (EditText) findViewById(R.id.ccredit);


        course.setCoursename(cname.getText().toString());
        course.setCoursecode(ccode.getText().toString());
        course.setCoursecode(cgrade.getText().toString());
        onClickAddName();
    }

    public void courseSpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.coursereg_spinner);


        spinner.setItems("Departments", "CSE", "ETE");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                course.setCourseDepartment(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void coursePrioritySpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.core_spinner);


        spinner.setItems("CORE", "OPTIONAL");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                course.setPriority(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    //remove view
    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(CourseProvider.COURSENAME,
                course.getCoursename()
        );

        values.put(CourseProvider.COURSECODE,
                course.getCoursecode()
        );
        values.put(CourseProvider.COURSEDEPARTMENT,
                course.getCourseDepartment()
        );
        values.put(CourseProvider.COURSEDEPARTMENT,
                course.getCourseDepartment()
        );
        values.put(CourseProvider.CREDIT,
                course.getGrade()
                );
        values.put(CourseProvider.PRIORITY,
                course.getPriority()
        );



        try{
            Uri uri = getContentResolver().insert(
                    CourseProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            System.out.println(e);
            Toast.makeText(getBaseContext(),"${e}",Toast.LENGTH_LONG).show();
        }


    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.shuleapp.StudentProvider";

        Uri courses = Uri.parse(URL);
        Cursor c = managedQuery(courses, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentProvider.FIRSTNAME)) +
                                ", " +  c.getString(c.getColumnIndex( StudentProvider.FIRSTNAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentProvider.MIDDLENAME)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}
