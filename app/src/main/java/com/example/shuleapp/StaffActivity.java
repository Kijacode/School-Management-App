package com.example.shuleapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.shuleapp.Models.Staff;
import com.example.shuleapp.passwordEncry.Decrypter;
import com.facebook.stetho.Stetho;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.math.BigInteger;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;


public class StaffActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Staff staff = new Staff();
    Decrypter hashpassword = new Decrypter();
//    Button bt = (Button) findViewById(R.id.reg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffadmin);
        Stetho.initializeWithDefaults(this);

//        bt.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                //OnCLick Stuff
//                try {
//                    save();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        courseSpinner();
        regionSpinner();
        districtSpinner();
        wardSpinner();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);

        Button b = (Button) findViewById(R.id.datePicker);


        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
       staff.setBirthdate(currentDate);
        b.setText(currentDate);


    }

    public void courseSpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.course_spinner);


        spinner.setItems("ETE", "CSE");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                staff.setDepartment(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void regionSpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.region_spinner);


        spinner.setItems("Region", "CS", "ELECTRONICS", "TE");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                staff.setRegion(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }


    public void districtSpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.district_spinner);


        spinner.setItems("District", "CS", "ELECTRONICS", "TE");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                staff.setDistrict(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }


    public void wardSpinner(){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.ward_spinner);


        spinner.setItems("Ward", "CS", "ELECTRONICS", "TE");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                staff.setWard(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }




    public void save(View pay) throws Exception {
//        Button bt = (Button) findViewById(R.id.reg);
        View parentView = (View) pay.getParent();
        EditText fname = (EditText) parentView.findViewById(R.id.fname);
        EditText mname = (EditText) parentView.findViewById(R.id.mname);
        EditText sname = (EditText) parentView.findViewById(R.id.sname);
        EditText email = (EditText) parentView.findViewById(R.id.email);
        EditText password = (EditText) parentView.findViewById(R.id.pword);
        EditText confirmpassword = (EditText) parentView.findViewById(R.id.cword);
        System.out.println("were herer before test password");
        if(passwordReg(password.getText().toString(),confirmpassword.getText().toString()))
        {
            System.out.println("before test password");

            staff.setFirstname(fname.getText().toString());
            staff.setMiddlename(mname.getText().toString());
            staff.setSurname(sname.getText().toString());
            staff.setEmail(email.getText().toString());
            onClickAddName();
         staff.setRegno(uniqueNO());
        }
        else
        {
            Toast.makeText(this,"PASSWORD NOT CORRECT",Toast.LENGTH_LONG).show();

        }

//
//        bt.setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                       radioButton();
//
//                    }});
    }

    public  void radioButton(){
        RadioGroup bt =  findViewById(R.id.st_radioButton);
//        bt.clearCheck();
        int selectedId = bt.getCheckedRadioButtonId();
        RadioButton rd;

        // find the radiobutton by returned id
        rd =  findViewById(selectedId);

        Toast.makeText(StaffActivity.this,
                rd.getText(), Toast.LENGTH_SHORT).show();

    }

    public void birthDatePicker(View v){
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");

    }


    //remove view
    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();
//        values.put(StudentProvider.ID,
//                1);
        values.put(StaffProvider.FIRSTNAME,
                staff.getFirstname());

        values.put(StaffProvider.MIDDLENAME,
                staff.getMiddlename());
        values.put(StaffProvider.SURNAME,
                staff.getSurname()
        );
        values.put(StaffProvider.EMAIL,
                staff.getEmail()
        );

        values.put(StaffProvider.BIRTHDATE,
                staff.getBirthdate()
        );

        values.put(StaffProvider.DEPARTMENT,
                staff.getDepartment()
        );
        values.put(StaffProvider.REGIION,
                staff.getRegion()
        );
        values.put(StaffProvider.DISTRICT,
                staff.getDistrict()
        );

        values.put(StaffProvider.WARD,
                staff.getWard()
        );
        values.put(StaffProvider.GENDER,
                "male"
        );
        values.put(StaffProvider.PASSWORD,
                staff.getPassword());

  values.put(StaffProvider.ROLE,
          StudentProvider.ROLE
          );
  values.put(StaffProvider.STAFFNO,
          staff.getRegno()
          );


        try{
            Uri uri = getContentResolver().insert(
                    StaffProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            System.out.println(e);
        }


    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.shuleapp.StaffProvider";

        Uri staff = Uri.parse(URL);
        Cursor c = managedQuery(staff, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StaffProvider.FIRSTNAME)) +
                                ", " +  c.getString(c.getColumnIndex( StaffProvider.FIRSTNAME)) +
                                ", " + c.getString(c.getColumnIndex( StaffProvider.MIDDLENAME)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }


    public  boolean passwordReg(String password,String confirmPassword) throws Exception {

        System.out.println("test password");

         staff.setPassword("12");
        if (password.equalsIgnoreCase(confirmPassword))
        {
            System.out.println("in the test password");
//             String newHash =  hashpassword.encrypt(password);
            byte [] outputdata = new byte[0];
            outputdata =  Decrypter.encryptSHA(password,"SHA-1");
            BigInteger shaData = new BigInteger(1,outputdata);
            staff.setPassword(shaData.toString(16));
            return true;

        }

        else
            return false;
    }


    public String uniqueNO(){
        System.out.println("HERE");

        ArrayList<Integer> staffno = new ArrayList<Integer>();
        Random random =new Random();
        String alphabet = "TANZANIA";

        for (int i = 0;i<4;i++)
        {
            int v =  random.nextInt(9);
            staffno.add(v);
        }
  ArrayList<Character> b = new ArrayList<Character>();
        for(int i=0;i<2;i++)
        {
              char v = alphabet.charAt(random.nextInt(alphabet.length()-2));
             b.add(v);
        }

        String strChar = Arrays.toString(b.toArray()).replaceAll("\\[|\\]|,|\\s","");
        String strOfInts = Arrays.toString(staffno.toArray()).replaceAll("\\[|\\]|,|\\s", "");

  String gh = strChar+strOfInts;
  System.out.println("Data are "+gh);
        return gh;

    }






}
