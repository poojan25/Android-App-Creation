package com.example.poojanaik.buildtech;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Rating;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.jar.Attributes;

import static com.example.poojanaik.buildtech.Apis.NAME;

public class Add extends AppCompatActivity {
    EditText loc,area,bhk,price,cname,cno;
    Button add,iv;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    SharedPreferences sharedPreferences=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        SharedPreferences mSharedPreferences;
        final String sharedPreference = "MyData";
        final EditText loc=(EditText)findViewById(R.id.editText);
        final EditText area=(EditText)findViewById(R.id.editText8);
        final EditText bhk=(EditText)findViewById(R.id.editText9);
        final EditText price=(EditText)findViewById(R.id.editText10);
        final EditText cname=(EditText)findViewById(R.id.editText11);
        final EditText cno=(EditText)findViewById(R.id.editText12);
        final Button add=(Button)findViewById(R.id.button6);
        final Button addimg=(Button)findViewById(R.id.button7);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        addimg.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v) {



            }
        });



        add.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
                Toast.makeText(Add.this,radioButton.getText(),Toast.LENGTH_SHORT).show();

                addDetails();
            }
            private void addDetails() {
                AndroidNetworking.post(Apis.add)
                        .addBodyParameter(Apis.CName, cname.getText().toString())
                        .addBodyParameter(Apis.CNumber, cno.getText().toString())
                        .addBodyParameter(Apis.LOC, loc.getText().toString())
                        .addBodyParameter(Apis.AREA, area.getText().toString())
                        .addBodyParameter(Apis.BHK, bhk.getText().toString())
                        .addBodyParameter(Apis.PRICE, price.getText().toString())
                        .addBodyParameter(Apis.TYPE, radioButton.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(Add.this,name, Toast.LENGTH_SHORT).show();


                                // do anything with response
                                if (response != null) {
                                    if(response.toString().contains("Added successfully.")) {
                                        Toast.makeText(Add.this,"Added Successfully", Toast.LENGTH_SHORT).show();


                                    }
                                    else if(response.toString().contains("Failed.")) {
                                        Toast.makeText(Add.this,"Please try again", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                    else if(response.toString().contains("Already exists")) {
                                        Toast.makeText(Add.this,"Property already added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }

                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Toast.makeText(Add.this,error.toString(), Toast.LENGTH_SHORT).show();
                                Log.e("Error",error.toString());

                            }
                        });
            }

        });


    }
}
