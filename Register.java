package com.example.poojanaik.buildtech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;


public class Register extends AppCompatActivity{
    EditText name,pass,cpass,email,contact;
    Button reg;
    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name=(EditText) findViewById(R.id.editTextname);
        pass=(EditText) findViewById(R.id.editText4);
        cpass=(EditText) findViewById(R.id.editText5);
        email=(EditText) findViewById(R.id.editText6);
        contact=(EditText) findViewById(R.id.editText7);
        reg=(Button)findViewById(R.id.button2);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkparams();
            }

        });


    }

    public void checkparams(){

        if (name.getText().length() < 3) {
            name.setError("Name should be at least 3 characters big");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            email.setError("Invalid Email");
        }  else if (contact.getText().length() < 8) {
            contact.setError("Phone Number should be of minimum 8 characters");
        } else if (pass.getText().length() < 6) {
            pass.setError("Password should be of minimum 6 characters");
        }
        else {
            addDetails();
        }
    }
    public  void addDetails(){
        //reg.startAnimation();
        AndroidNetworking.post(Apis.REG)
                .addBodyParameter(Apis.NAME, name.getText().toString())
                .addBodyParameter(Apis.PASSWORD, pass.getText().toString())
                .addBodyParameter(Apis.EMAIL, email.getText().toString())
                .addBodyParameter(Apis.CONTACT, contact.getText().toString())
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        if (response != null) {
                            if(response.toString().contains("Registered successfully")) {
                                Toast.makeText(Register.this,"Registered", Toast.LENGTH_SHORT).show();
                                name.setText("");
                            }
                            else if(response.toString().contains("User Already Registered.")) {
                                Toast.makeText(Register.this,"User Already Registered.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(Register.this,error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("Error",error.toString());

                    }
                });
    }


}
