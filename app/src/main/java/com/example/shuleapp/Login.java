package com.example.shuleapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    String adm = "admin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }


    public void login2(View view) {
        View parentView = (View) view.getParent();
        username = (EditText) parentView.findViewById(R.id.username);
        password = (EditText) parentView.findViewById(R.id.passord);
        verify(username.getText().toString(),password.getText().toString());


    }

    public void admin() {
        Intent i = new Intent(this, Adminpanel.class);
        startActivity(i);
    }

    public void student() {
        Intent i = new Intent(this, StudentLogin.class);
        startActivity(i);
    }

    public void verify(String username,String password ) {

        String[] Role = {"role"};


        String URL = "content://com.example.shuleapp.StudentProvider";
        String URL2 = "content://com.example.shuleapp.StaffProvider";

        Uri staff = Uri.parse(URL2);
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "firstname");
        Cursor c2 = managedQuery(staff, null, null, null, null);

        if (username.startsWith("2")) {
            student();
            c = this.getContentResolver().query(students, new String[]{"password"}, "regno =" + username, null, null);
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                System.out.println(c.getString(c.getColumnIndex("Data are here " + StudentProvider.PASSWORD)));
                student();
            }

        } else if ((username.startsWith("T"))) {
            c2 = this.getContentResolver().query(staff, new String[]{"password"}, "staffnumber =" + username, null, null);
            for (c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext()) {
                System.out.println(c2.getString(c2.getColumnIndex("Data are here " + StudentProvider.PASSWORD)));

            }
        } else if (username.equalsIgnoreCase(adm)) {
            admin();

        } else {

            Toast.makeText(this, "YOUR NOT REGISTER", Toast.LENGTH_LONG).show();
        }


    }
}