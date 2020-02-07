package com.example.shuleapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.res.Resources;
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

import com.example.shuleapp.Models.LocationModel;
import com.example.shuleapp.Models.Student;
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


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

Student student = new Student();
Decrypter hashpassword = new Decrypter();
    RadioButton rd;
    RadioGroup bt;
//    Button bt = (Button) findViewById(R.id.reg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Stetho.initializeWithDefaults(this);

        Location b = new Location();
        String URL = "content://com.example.shuleapp.StudentProvider";
        Uri regions = Uri.parse(URL);
        Cursor c = managedQuery(regions, null, null, null, null);
        System.out.println("DATA ARE: "+ c.getCount());
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


yearSpinner();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);

        Button b = (Button) findViewById(R.id.datePicker);


        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        student.setBirthdate(currentDate);
        b.setText(currentDate);


    }

    public void courseSpinner(){
    MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.course_spinner);


    spinner.setItems("CEIT", "CS", "ELECTRONICS", "TE");

    spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

        @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
            student.setDepartment(item);
            Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
        }
    });
}

    public void regionSpinner(){

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.region_spinner);
        final LocationModel locationModel = new LocationModel();
        Resources reg2 = getResources();
        String []region = reg2.getStringArray(R.array.regions);
        System.out.println(region);
        spinner.setItems(region);


        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                student.setRegion(item);

                districtSpinner(item.toString());
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });



    }


    public void districtSpinner(String s){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.district_spinner);
        final LocationModel locationModel = new LocationModel();
        Resources reg = getResources();

        String []district;
        switch (s)
        {
            case "Dar es Salaam":
              district = reg.getStringArray(R.array.dar_districts);
                spinner.setItems( district);
                break;

            case "Arusha":
                district = reg.getStringArray(R.array.arusha_districts);
                spinner.setItems( district);
                break;

            case "Dodoma":
                district = reg.getStringArray(R.array.dom_districts);
                spinner.setItems( district);
                break;

            case "Geita":
                district = reg.getStringArray(R.array.geita_districts);
                spinner.setItems( district);
                break;

            case "Iringa":
                district = reg.getStringArray(R.array.iringa_districts);
                spinner.setItems( district);
                break;

            case "Kagera":
                district = reg.getStringArray(R.array.kagera_districts);
                spinner.setItems( district);
                 break;

            case "Katavi":
                district = reg.getStringArray(R.array.katavi_districts);
                spinner.setItems( district);
                break;

            case "Kigoma":
                district = reg.getStringArray(R.array.kigoma_districts);
                spinner.setItems( district);
                break;

            case "Kilimanjaro":
                district = reg.getStringArray(R.array.kilimanjaro_districts);
                spinner.setItems( district);
                break;

            case "Pemba Kusini":
                district = reg.getStringArray(R.array.pemba_kusini_districts);
                spinner.setItems(district);
                break;

            case "Unguza Kusini":
                district = reg.getStringArray(R.array.unguja_kusini_districts);
                spinner.setItems( district);
                break;

            case "Lindi":
                district = reg.getStringArray(R.array.lindi_districts);
                spinner.setItems(district);
                break;

            case "Manyara":
                district = reg.getStringArray(R.array.manyara_districts);
                spinner.setItems( district);
                break;

            case "Mara":
                district = reg.getStringArray(R.array.mara_districts);
                spinner.setItems( district);
                break;

            case "Mbeya":
                district = reg.getStringArray(R.array.mbeya_districts);
                spinner.setItems(district);
                break;

            case "Unguja Mjini Magharibi":
                district = reg.getStringArray(R.array.unguja_mjini_magharibi_districts);
                spinner.setItems( district);
                break;

            case "Morogoro":
                district = reg.getStringArray(R.array.moro_districts);
                spinner.setItems( district);
                break;

            case "Mtwara":
                district = reg.getStringArray(R.array.mtwara_districts);
                spinner.setItems( district);
                break;

            case "Mwanza":
                district = reg.getStringArray(R.array.mwanza_districts);
                spinner.setItems( district);
                break;

            case "Njombe":
                district = reg.getStringArray(R.array.njombe_districts);
                spinner.setItems( district);
                break;

            case "Pwani":
                district = reg.getStringArray(R.array.pwani_districts);
                spinner.setItems( district);
                break;

            case "Rukwa":
                district = reg.getStringArray(R.array.rukwa_districts);
                spinner.setItems( district);
                break;

            case "Ruvuma":
                district = reg.getStringArray(R.array.ruvuma_districts);
                spinner.setItems( district);
                break;

            case "Shinyanga":
                district = reg.getStringArray(R.array.shinyanga_districts);
                spinner.setItems( district);
                break;

            case "Simiyu":
                district = reg.getStringArray(R.array.simiyu_districts);
                spinner.setItems( district);
                break;

            case "Singida":
                district = reg.getStringArray(R.array.singida_districts);
                spinner.setItems( district);
                break;

            case "Songwe":
                district = reg.getStringArray(R.array.songwe_districts);
                spinner.setItems( district);
                break;

            case "Tabora":
                district = reg.getStringArray(R.array.tabora_districts);
                spinner.setItems( district);
                break;

            case "Tanga":
                district = reg.getStringArray(R.array.tanga_districts);
                spinner.setItems( district);
                break;

            case "Unguja Kaskazini":
                district = reg.getStringArray(R.array.unguja_kaskazini_districts);
                spinner.setItems( district);
                break;

            case "Pemba Kaskazini":
                district = reg.getStringArray(R.array.pemba_kaskazini_districts);
                spinner.setItems( district);
                break;
        }



        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                student.setDistrict(item);
                wardSpinner(item.toString());
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }


    public void wardSpinner(String s){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.ward_spinner);
        final LocationModel locationModel = new LocationModel();
        Resources reg2 = getResources();

        String []ward;


        switch (s)
        {
            // Dar es Salaam Districts
            case "Ilala":
                ward = reg2.getStringArray(R.array.ilala_wards);
                spinner.setItems(ward);
                break;

            case "Kinondoni":
                ward = reg2.getStringArray(R.array.kinondoni_wards);
                spinner.setItems(ward);
                break;

            case "Temeke":
                ward = reg2.getStringArray(R.array.temeke_wards);
                spinner.setItems(ward);
                break;

            case "Kigamboni":
                ward = reg2.getStringArray(R.array.kigamboni_wards);
                spinner.setItems(ward);
                break;

            case "Ubungo":
                ward = reg2.getStringArray(R.array.ubungo_wards);
                spinner.setItems(ward);;
                break;

            // Arusha Districts
            case "Meru":
                ward = reg2.getStringArray(R.array.meru_wards);
                spinner.setItems(ward);
                break;

            case "Arusha":
                ward = reg2.getStringArray(R.array.arusha_wards);
                spinner.setItems(ward);
                break;

            case "Karatu":
                ward = reg2.getStringArray(R.array.karagwe_wards);
                spinner.setItems(ward);
                break;

            case "Longido":
                ward = reg2.getStringArray(R.array.longido_wards);
                spinner.setItems(ward);
                break;

            case "Monduli":
                ward = reg2.getStringArray(R.array.monduli_wards);
                spinner.setItems(ward);
                break;

            case "Ngorongoro":
                ward = reg2.getStringArray(R.array.ngorongoro_wards);
                spinner.setItems(ward);
                break;

            // Dodoma disticts
            case "Bahi":
                ward = reg2.getStringArray(R.array.bahi_wards);
                spinner.setItems(ward);
                break;

            case "Chamwino":
                ward = reg2.getStringArray(R.array.chamwino_wards);
                spinner.setItems(ward);
                break;

            case "Chemba":
                ward = reg2.getStringArray(R.array.chemba_wards);
                spinner.setItems(ward);
                break;

            case "Dodoma":
                ward = reg2.getStringArray(R.array.dodoma_wards);
                spinner.setItems(ward);
                break;

            case "Kondoa":
                ward = reg2.getStringArray(R.array.kondoa_wards);
                spinner.setItems(ward);
                break;

            case "Kongwa":
                ward = reg2.getStringArray(R.array.kongwa_wards);
                spinner.setItems(ward);;
                break;

            case "Mpwapwa":
                ward = reg2.getStringArray(R.array.mpwapwa_wards);
                spinner.setItems(ward);
                break;

            // Geita Districts
            case "Bukombe":
                ward = reg2.getStringArray(R.array.bukoba_wards);
                spinner.setItems(ward);
                break;

            case "Chato":
                ward = reg2.getStringArray(R.array.chato_wards);
                spinner.setItems(ward);;
                break;

            case "Geita":
                ward = reg2.getStringArray(R.array.geita_wards);
                spinner.setItems(ward);
                break;

            case "Mbogwe":
                ward = reg2.getStringArray(R.array.mbogwe_wards);
                spinner.setItems(ward);
                break;

            case "Nyanghwale":
                ward = reg2.getStringArray(R.array.nyanghwale_wards);
                spinner.setItems(ward);
                break;

            // Iringa Districts
            case "Iringa":
                ward = reg2.getStringArray(R.array.iringa_wards);
                spinner.setItems(ward);
                break;

            case "Kilolo":
                ward = reg2.getStringArray(R.array.kilolo_wards);
                spinner.setItems(ward);
                break;

            case "Mafinga":
                ward = reg2.getStringArray(R.array.mafia_wards);
                spinner.setItems(ward);;
                break;

            case "Mufindi":
                ward = reg2.getStringArray(R.array.mufindi_wards);
                spinner.setItems(ward);
                break;

            // Kagera Districts
            case "Biharamulo":
                ward = reg2.getStringArray(R.array.biharamulo_wards);
                spinner.setItems(ward);
                break;

            case "Bukoba":
                ward = reg2.getStringArray(R.array.bukoba_wards);
                spinner.setItems(ward);
                break;

            case "Karagwe":
                ward = reg2.getStringArray(R.array.karagwe_wards);
                spinner.setItems(ward);
                break;

            case "Kyerwa":
                ward = reg2.getStringArray(R.array.kyerwa_wards);
                spinner.setItems(ward);
                break;

            case "Missenyi":
                ward = reg2.getStringArray(R.array.missenyi_wards);
                spinner.setItems(ward);
                break;

            case "Muleba":
                ward = reg2.getStringArray(R.array.muleba_wards);
                spinner.setItems(ward);
                break;

            case "Ngara":
                ward = reg2.getStringArray(R.array.ngara_wards);
                spinner.setItems(ward);
                break;

            // Katavi Districts
            case "Mlele":
                ward = reg2.getStringArray(R.array.mlele_wards);
                spinner.setItems(ward);
                break;

            case "Mpanda":
                ward = reg2.getStringArray(R.array.mpanda_wards);
                spinner.setItems(ward);
                break;

            case "Mpanda Urban":
                ward = reg2.getStringArray(R.array.mpanda_urban_wards);
                spinner.setItems(ward);
                break;

            // Kigoma Districts
            case "Buhigwe":
                ward = reg2.getStringArray(R.array.buhigwe_wards);
                spinner.setItems(ward);
                break;

            case "Kakonko":
                ward = reg2.getStringArray(R.array.kakonko_wards);
                spinner.setItems(ward);
                break;

            case "Kasulu":
                ward = reg2.getStringArray(R.array.kasulu_wards);
                spinner.setItems(ward);
                break;

            case "Kibondo":
                ward = reg2.getStringArray(R.array.kibondo_wards);
                spinner.setItems(ward);
                break;

            case "Kigoma":
                ward = reg2.getStringArray(R.array.kigoma_wards);
                spinner.setItems(ward);
                break;

            case "Kigoma-Ujiji":
                ward = reg2.getStringArray(R.array.kigoma_ujiji_wards);
                spinner.setItems(ward);
                break;

            case "Uvinza":
                ward = reg2.getStringArray(R.array.uvinza_wards);
                spinner.setItems(ward);
                break;

            // Kilimanjaro Districts
            case "Hai":
                ward = reg2.getStringArray(R.array.hai_wards);
                spinner.setItems(ward);
                break;

            case "Moshi":
                ward = reg2.getStringArray(R.array.moshi_wards);
                spinner.setItems(ward);;
                break;

            case "Moshi Urban":
                ward = reg2.getStringArray(R.array.moshi_urban_wards);
                spinner.setItems(ward);
                break;

            case "Mwanga":
                ward = reg2.getStringArray(R.array.mwanga_wards);
                spinner.setItems(ward);
                break;

            case "Rombo":
                ward = reg2.getStringArray(R.array.rombo_wards);
                spinner.setItems(ward);
                break;

            // Geita Districts
            case "Same":
                ward = reg2.getStringArray(R.array.same_wards);
                spinner.setItems(ward);
                break;

            case "Siha":
                ward = reg2.getStringArray(R.array.siha_wards);
                spinner.setItems(ward);
                break;

            // Pemba Kusini Districts
            case "Chake Chake":
                ward = reg2.getStringArray(R.array.chake_chake_wards);
                spinner.setItems(ward);
                break;

            case "Mkoani":
                ward = reg2.getStringArray(R.array.mkoani_wards);
                spinner.setItems(ward);
                break;

            // Unguja Kusini Districts
            case "Kati":
                ward = reg2.getStringArray(R.array.kati_wards);
                spinner.setItems(ward);
                break;

            case "Kusini":
                ward = reg2.getStringArray(R.array.kusini_wards);
                spinner.setItems(ward);
                break;

            // Lindi Districts
            case "Kilwa":
                ward = reg2.getStringArray(R.array.kilwa_wards);
                spinner.setItems(ward);
                break;

            case "Lindi":
                ward = reg2.getStringArray(R.array.lindi_wards);
                spinner.setItems(ward);
                break;

            case "Lindi Urban":
                ward = reg2.getStringArray(R.array.lindi_urban_wards);
                spinner.setItems(ward);
                break;

            case "Liwale":
                ward = reg2.getStringArray(R.array.liwale_wards);
                spinner.setItems(ward);
                break;

            case "Nachingwea":
                ward = reg2.getStringArray(R.array.nachingwea_wards);
                spinner.setItems(ward);
                break;

            case "Ruangwa":
                ward = reg2.getStringArray(R.array.ruangwa_wards);
                spinner.setItems(ward);
                break;

            // Manyara Districts
            case "Babati":
                ward = reg2.getStringArray(R.array.babati_wards);
                spinner.setItems(ward);
                break;

            case "Babati Urban":
                ward = reg2.getStringArray(R.array.babati_urban_wards);
                spinner.setItems(ward);
                break;

            case "Hanang":
                ward = reg2.getStringArray(R.array.hanang_wards);
                spinner.setItems(ward);
                break;

            case "Kiteto":
                ward = reg2.getStringArray(R.array.kiteto_wards);
                spinner.setItems(ward);
                break;

            case "Mbulu":
                ward = reg2.getStringArray(R.array.mbulu_wards);
                spinner.setItems(ward);
                break;

            case "Simanjiro":
                ward = reg2.getStringArray(R.array.simanjiro_wards);
                spinner.setItems(ward);
                break;

            // Mara Districts
            case "Bunda":
                ward = reg2.getStringArray(R.array.bunda_wards);
                spinner.setItems(ward);
                break;

            case "Butiama":
                ward = reg2.getStringArray(R.array.butiama_wards);
                spinner.setItems(ward);
                break;

            case "Musoma":
                ward = reg2.getStringArray(R.array.musoma_wards);
                spinner.setItems(ward);
                break;

            case "Musoma Municipal":
                ward = reg2.getStringArray(R.array.musoma_municipal_wards);
                spinner.setItems(ward);
                break;

            case "Rorya":
                ward = reg2.getStringArray(R.array.rorya_wards);
                spinner.setItems(ward);
                break;

            case "Serengeti":
                ward = reg2.getStringArray(R.array.serengeti_wards);
                spinner.setItems(ward);
                break;

            case "Tarime":
                ward = reg2.getStringArray(R.array.tarime_wards);
                spinner.setItems(ward);
                break;

            // Mbeya disticts
            case "Chunya":
                ward = reg2.getStringArray(R.array.chunya_wards);
                spinner.setItems(ward);
                break;

            case "Kyela":
                ward = reg2.getStringArray(R.array.kyela_wards);
                spinner.setItems(ward);
                break;

            case "Mbarali":
                ward = reg2.getStringArray(R.array.mbarali_wards);
                spinner.setItems(ward);
                break;

            case "Mbeya":
                ward = reg2.getStringArray(R.array.mbeya_wards);
                spinner.setItems(ward);
                break;

            case "Mbeya Urban":
                ward = reg2.getStringArray(R.array.mbeya_urban_wards);
                spinner.setItems(ward);
                break;

            case "Rungwe":
                ward = reg2.getStringArray(R.array.ruangwa_wards);
                spinner.setItems(ward);
                break;

            // Morogoro Districts
            case "Gairo":
                ward = reg2.getStringArray(R.array.gairo_wards);
                spinner.setItems(ward);
                break;

            case "Kilombero":
                ward = reg2.getStringArray(R.array.kilombero_wards);
                spinner.setItems(ward);
                break;

            case "Kilosa":
                ward = reg2.getStringArray(R.array.kilosa_wards);
                spinner.setItems(ward);
                break;

            case "Morogoro":
                ward = reg2.getStringArray(R.array.morogoro_wards);
                spinner.setItems(ward);
                break;

            case "Morogoro Urban":
                ward = reg2.getStringArray(R.array.morogoro_urban_wards);
                spinner.setItems(ward);
                break;

            case "Mvomero":
                ward = reg2.getStringArray(R.array.mvomero_wards);
                spinner.setItems(ward);
                break;

            case "Ulanga":
                ward = reg2.getStringArray(R.array.ulanga_wards);
                spinner.setItems(ward);
                break;

            // Mtwara Districts
            case "Masasi":
                ward = reg2.getStringArray(R.array.masasi_wards);
                spinner.setItems(ward);
                break;

            case "Mtwara":
                ward = reg2.getStringArray(R.array.mtwara_wards);
                spinner.setItems(ward);
                break;

            case "Mtwara Urban":
                ward = reg2.getStringArray(R.array.mtwara_urban_wards);
                spinner.setItems(ward);
                break;

            case "Nanyumbu":
                ward = reg2.getStringArray(R.array.nanyumbu_wards);
                spinner.setItems(ward);
                break;

            case "Newala":
                ward = reg2.getStringArray(R.array.newala_wards);
                spinner.setItems(ward);
                break;

            case "Tandahimba":
                ward = reg2.getStringArray(R.array.tandahimba_wards);
                spinner.setItems(ward);
                break;

            // Mwanza Districts
            case "Ilemela":
                ward = reg2.getStringArray(R.array.ilemela_wards);
                spinner.setItems(ward);
                break;

            case "Kwimba":
                ward = reg2.getStringArray(R.array.kwimba_wards);
                spinner.setItems(ward);
                break;

            case "Magu":
                ward = reg2.getStringArray(R.array.magu_wards);
                spinner.setItems(ward);
                break;

            case "Misungwi":
                ward = reg2.getStringArray(R.array.misungwi_wards);
                spinner.setItems(ward);
                break;

            case "Nyamagana":
                ward = reg2.getStringArray(R.array.nyamagana_wards);
                spinner.setItems(ward);
                break;

            case "Sengerema":
                ward = reg2.getStringArray(R.array.sengerema_wards);
                spinner.setItems(ward);
                break;

            case "Ukerewe":
                ward = reg2.getStringArray(R.array.ukerewe_wards);
                spinner.setItems(ward);
                break;

            // Njombe Districts
            case "Ludewa":
                ward = reg2.getStringArray(R.array.ludewa_wards);
                spinner.setItems(ward);
                break;

            case "Makambako":
                ward = reg2.getStringArray(R.array.makambako_wards);
                spinner.setItems(ward);
                break;

            case "Makete":
                ward = reg2.getStringArray(R.array.makete_wards);
                spinner.setItems(ward);
                break;

            case "Njombe":
                ward = reg2.getStringArray(R.array.njombe_wards);
                spinner.setItems(ward);
                break;

            case "Njombe Urban":
                ward = reg2.getStringArray(R.array.njombe_urban_wards);
                spinner.setItems(ward);
                break;

            case "Wangingmbe":
                ward = reg2.getStringArray(R.array.wangingombe_wards);
                spinner.setItems(ward);
                break;

            // Pwani Districts
            case "Bagamoyo":
                ward = reg2.getStringArray(R.array.bagamoyo_wards);
                spinner.setItems(ward);
                break;

            case "Kibaha":
                ward = reg2.getStringArray(R.array.kibaha_wards);
                spinner.setItems(ward);
                break;

            case "Kibaha Urban":
                ward = reg2.getStringArray(R.array.kibaha_urban_wards);
                spinner.setItems(ward);
                break;

            case "Kisarawe":
                ward = reg2.getStringArray(R.array.kisarawe_wards);
                spinner.setItems(ward);
                break;

            case "Mafia":
                ward = reg2.getStringArray(R.array.mafia_wards);
                spinner.setItems(ward);
                break;

            case "Mkuranga":
                ward = reg2.getStringArray(R.array.mkuranga_wards);
                spinner.setItems(ward);
                break;

            case "Rufiji":
                ward = reg2.getStringArray(R.array.rufiji_wards);
                spinner.setItems(ward);
                break;

            // Unguja Mjini Magharibi Districts
            case "Magharibi":
                ward = reg2.getStringArray(R.array.magharibi_wards);
                spinner.setItems(ward);
                break;

            case "Mjini":
                ward = reg2.getStringArray(R.array.mjini_wards);
                spinner.setItems(ward);
                break;

            // Rukwa Districts
            case "Kalambo":
                ward = reg2.getStringArray(R.array.kalambo_wards);
                spinner.setItems(ward);
                break;

            case "Nkasi":
                ward = reg2.getStringArray(R.array.nkasi_wards);
                spinner.setItems(ward);
                break;

            case "Sumbawanga":
                ward = reg2.getStringArray(R.array.sumbawanga_wards);
                spinner.setItems(ward);
                break;

            case "Sumbawanga Urban":
                ward = reg2.getStringArray(R.array.sumbawanga_urban_wards);
                spinner.setItems(ward);
                break;

            // Ruvuma Districts
            case "Mbinga":
                ward = reg2.getStringArray(R.array.mbinga_wards);
                spinner.setItems(ward);
                break;

            case "Namtumbo":
                ward = reg2.getStringArray(R.array.namtumbo_wards);
                spinner.setItems(ward);
                break;

            case "Nyasa":
                ward = reg2.getStringArray(R.array.nyasa_wards);
                spinner.setItems(ward);
                break;

            case "Songea":
                ward = reg2.getStringArray(R.array.songea_wards);
                spinner.setItems(ward);
                break;

            case "Songea Urban":
                ward = reg2.getStringArray(R.array.songea_urban_wards);
                spinner.setItems(ward);
                break;

            case "Tunduma":
                ward = reg2.getStringArray(R.array.tunduma_wards);
                spinner.setItems(ward);
                break;

            // Shinyanga Districts
            case "Kahama":
                ward = reg2.getStringArray(R.array.kahama_wards);
                spinner.setItems(ward);
                break;

            case "Kahama Township Authority":
                ward = reg2.getStringArray(R.array.kahama_township_authority_wards);
                spinner.setItems(ward);
                break;

            case "Kishapu":
                ward = reg2.getStringArray(R.array.kishapu_wards);
                spinner.setItems(ward);
                break;

            case "Shinyanga":
                ward = reg2.getStringArray(R.array.shinyanga_wards);
                spinner.setItems(ward);
                break;

            case "Shinyanga Urban":
                ward = reg2.getStringArray(R.array.shinyanga_urban_wards);
                spinner.setItems(ward);
                break;

            // Simiyu Districts
            case "Bariadi":
                ward = reg2.getStringArray(R.array.kibaha_wards);
                spinner.setItems(ward);
                break;

            case "Busega":
                ward = reg2.getStringArray(R.array.busega_wards);
                spinner.setItems(ward);
                break;

            case "Itilima":
                ward = reg2.getStringArray(R.array.itilima_wards);
                spinner.setItems(ward);
                break;

            case "Maswa":
                ward = reg2.getStringArray(R.array.maswa_wards);
                spinner.setItems(ward);
                break;

            case "Meatu":
                ward = reg2.getStringArray(R.array.meatu_wards);
                spinner.setItems(ward);
                break;

            // Singida Districts
            case "Ikungi":
                ward = reg2.getStringArray(R.array.ikungi_wards);
                spinner.setItems(ward);
                break;

            case "Iramba":
                ward = reg2.getStringArray(R.array.iramba_wards);
                spinner.setItems(ward);
                break;

            case "Manyoni":
                ward = reg2.getStringArray(R.array.manyoni_wards);
                spinner.setItems(ward);
                break;

            case "Mkalama":
                ward = reg2.getStringArray(R.array.mkalama_wards);
                spinner.setItems(ward);
                break;

            case "Singida":
                ward = reg2.getStringArray(R.array.singida_wards);
                spinner.setItems(ward);
                break;

            case "Singida Urban":
                ward = reg2.getStringArray(R.array.singida_urban_wards);
                spinner.setItems(ward);
                break;

            // Songwe Districts
            case "Ileje":
                ward = reg2.getStringArray(R.array.ileje_wards);
                spinner.setItems(ward);
                break;

            case "Mbozi":

                        ward = reg2.getStringArray(R.array.mbozi_wards);
                spinner.setItems(ward);
                break;

            case "Momba":
                ward = reg2.getStringArray(R.array.momba_wards);
                spinner.setItems(ward);
                break;

            // Tabora Districts
            case "Igunga":
                ward = reg2.getStringArray(R.array.igunga_wards);
                spinner.setItems(ward);
                break;

            case "Kaliua":
                ward = reg2.getStringArray(R.array.kaliua_wards);
                spinner.setItems(ward);
                break;

            case "Nzega":
                ward = reg2.getStringArray(R.array.nzega_wards);
                spinner.setItems(ward);
                break;

            case "Sikonge":
                ward = reg2.getStringArray(R.array.sikonge_wards);
                spinner.setItems(ward);
                break;

            case "Tabora Urban ":
                ward = reg2.getStringArray(R.array.tabora_urban_wards);
                spinner.setItems(ward);
                break;

            case "Urambo":
                ward = reg2.getStringArray(R.array.urambo_wards);
                spinner.setItems(ward);
                break;

            case "Uyui":
                ward = reg2.getStringArray(R.array.uyui_wards);
                spinner.setItems(ward);
                break;

            // Tanga Districts
            case "Handeni":
                ward = reg2.getStringArray(R.array.handeni_wards);
                spinner.setItems(ward);
                break;

            case "Handeni Township Authority":
                ward = reg2.getStringArray(R.array.handeni_township_authority);
                spinner.setItems(ward);
                break;

            case "Kilindi":
                ward = reg2.getStringArray(R.array.kilindi_wards);
                spinner.setItems(ward);
                break;

            case "Korogwe":
                ward = reg2.getStringArray(R.array.korogwe_wards);
                spinner.setItems(ward);
                break;

            case "Korogwe Township Authority":
                ward = reg2.getStringArray(R.array.korogwe_township_authority);
                spinner.setItems(ward);
                break;

            case "Lushoto":
                ward = reg2.getStringArray(R.array.lushoto_wards);
                spinner.setItems(ward);
                break;

            case "Mkinga":
                ward = reg2.getStringArray(R.array.mkinga_wards);
                spinner.setItems(ward);
                break;

            case "Muheza":
                ward = reg2.getStringArray(R.array.muheza_wards);
                spinner.setItems(ward);
                break;

            case "Pangani":
                ward = reg2.getStringArray(R.array.pangani_wards);
                spinner.setItems(ward);
                break;

            case "Tanga":
                ward = reg2.getStringArray(R.array.tanga_wards);
                spinner.setItems(ward);
                break;

            // Arusha Districts
            case "Kaskazini A":
                ward = reg2.getStringArray(R.array.kaskazini_a_wards);
                spinner.setItems(ward);
                break;

            case "Kaskazini B":
                ward = reg2.getStringArray(R.array.kaskazini_b_wards);
                spinner.setItems(ward);
                break;

            case "Micheweni":
                ward = reg2.getStringArray(R.array.micheweni_wards);
                spinner.setItems(ward);
                break;

            case "Wete":
                ward = reg2.getStringArray(R.array.wete_wards);
                spinner.setItems(ward);
                break;
        }

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                student.setWard(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }


    public  void  yearSpinner()
    {
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.year_spinner);
        spinner.setItems("Year 1","Year 2","Year 3");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                regNO(item);
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

         student.setFirstname(fname.getText().toString());
         student.setMiddlename(mname.getText().toString());
         student.setSurname(sname.getText().toString());
         student.setEmail(email.getText().toString());
         onClickAddName();
         radioButton();
//         regNO("Year 1");
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
         bt =  findViewById(R.id.st_radioButton);
//        bt.clearCheck();
        int selectedId = bt.getCheckedRadioButtonId();


        // find the radiobutton by returned id
        rd =  findViewById(selectedId);



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
        values.put(StudentProvider.FIRSTNAME,
                student.getFirstname());

        values.put(StudentProvider.MIDDLENAME,
               student.getMiddlename());
        values.put(StudentProvider.SURNAME,
                student.getSurname()
                );
        values.put(StudentProvider.EMAIL,
                student.getEmail()
                );

        values.put(StudentProvider.BIRTHDATE,
                student.getBirthdate()
                );

        values.put(StudentProvider.COURSEDEG,
                student.getDepartment()
                );
        values.put(StudentProvider.REGIION,
                student.getRegion()
                );
        values.put(StudentProvider.DISTRICT,
                student.getDistrict()
                );

        values.put(StudentProvider.WARD,
                student.getWard()
                );
        values.put(StudentProvider.GENDER,
                "male"
                );
        values.put(StudentProvider.PASSWORD,
                student.getPassword());

  values.put(StudentProvider.ROLE,
          "student"
          );

  values.put(StudentProvider.REGNO,
          student.getRegno()
          );
        try{
            Uri uri = getContentResolver().insert(
                    StudentProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            System.out.println(e);
        }


    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.shuleapp.StudentProvider";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");


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


    public  boolean passwordReg(String password,String confirmPassword) throws Exception {

        System.out.println("test password");

          if (password.equalsIgnoreCase(confirmPassword))
          {
              System.out.println("in the test password");
//             String newHash =  hashpassword.encrypt(password);
              byte [] outputdata = new byte[0];
              outputdata =  Decrypter.encryptSHA(password,"SHA-1");
              BigInteger shaData = new BigInteger(1,outputdata);
             student.setPassword(shaData.toString(16));
             return true;

          }

          else
              return false;
    }



    public void regNO(String yearofStudy){


        String   reg = uniqueNO();

        if (yearofStudy == "Year 1")
        {
           student.setRegno("2017-04-"+reg);
        }
        else if(yearofStudy == "Year 2")
        {
           student.setRegno("2018-04-"+reg);
        }
        else if(yearofStudy == "Year 3")
        {
  student.setRegno("2019-04-"+reg);
        }
        else
        {
            Toast.makeText(this,"YOUR NOT ELIGIBLE ENTER CORRECT REGNO",Toast.LENGTH_LONG).show();
        }
    }

    public String uniqueNO(){
        System.out.println("HERE");

        ArrayList<Integer> regno = new ArrayList<Integer>();
        Random random =new Random();
     for (int i = 0;i<5;i++)
     {
       int v =  random.nextInt(random.nextInt(8));
        regno.add(v);
     }
        String strOfInts = Arrays.toString(regno.toArray()).replaceAll("\\[|\\]|,|\\s", "");

     System.out.println(strOfInts);
return  strOfInts;

    }


}
