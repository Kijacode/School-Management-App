package com.example.shuleapp;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.app.Activity;


public class SplashScreen extends Activity{



    Handler handler;



    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

        try{

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            },3000);


        }catch (Exception e)
        {
            System.out.println(e);
        }
    }




}

