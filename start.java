package com.example.poojanaik.buildtech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import android.app.ProgressDialog;


public class start extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                ProgressDialog myDialog = ProgressDialog.show(start.this, "", "Loading", true);

                Intent intent=new Intent(start.this,Welcome.class);
                startActivity(intent);
                myDialog.dismiss();
                start.this.finish();
            }

        }, 3000);// 3 Seconds
    }
}
