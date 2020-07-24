package com.example.poojanaik.buildtech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button3,button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button view=(Button)findViewById(R.id.button3);
        final Button add=(Button)findViewById(R.id.button4);
        final Button rent=(Button)findViewById(R.id.button5);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, com.example.poojanaik.buildtech.Prop.class);
                startActivity(i);
                }



    });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, admin.class);
                startActivity(i);
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Rent.class);
                startActivity(i);
            }
        });
}
}
