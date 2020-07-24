package com.example.poojanaik.buildtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class admin extends AppCompatActivity {
    Button add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        Button add = (Button) findViewById(R.id.button4);
        add.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(admin.this, Add.class);
                startActivity(i);
            }


        });

    }
}
