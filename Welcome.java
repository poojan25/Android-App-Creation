package com.example.poojanaik.buildtech;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;


import org.json.JSONException;
import org.json.JSONObject;

import static com.example.poojanaik.buildtech.Apis.NAME;


public class Welcome extends AppCompatActivity{

    EditText registername,registerphone,registeremail,registerage,registerusername,registerpass,registerconfirmpass;
    Button button,reg;
    //SharedPreferences sharedPreferences=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        //  SharedPreferences.Editor mEditor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        registername=(EditText) findViewById(R.id.editText3);
        registerpass=(EditText) findViewById(R.id.editText2);
        final Button button=(Button)findViewById(R.id.button);
        final Button reg=(Button)findViewById(R.id.button2);
        //String name=registername.getText().toString();
       // mEditor.putString(getString(R.string.name),name);
       // mEditor.commit();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Welcome.this,Register.class);
               //String Value = i.getStringExtra(registername.toString());

                startActivity(i);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
            }
        });


    }




    public  void addDetails(){
       // button.startAnimation();
        AndroidNetworking.post(Apis.LOGIN)
                .addBodyParameter(NAME, registername.getText().toString())
                .addBodyParameter(Apis.PASSWORD, registerpass.getText().toString())

                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SharedPreferences mSharedPreferences;
                        final String sharedPreference = "MyData";
                        String status="false";
                        String name="";

                        mSharedPreferences = getSharedPreferences(sharedPreference, Context.MODE_PRIVATE);
                        try {
                            String my=response.getJSONObject("data").toString();
                            JSONObject jsonObject = new JSONObject(my);
                            name=jsonObject.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        if(status.equals("true")) {
                            editor.putString(NAME,name);
                            editor.apply();
                        }
                        Log.e("values", name);



                        // do anything with response
                        if (response != null) {
                            if(response.toString().contains("Invalid Password")) {
                                Toast.makeText(Welcome.this,"Invalid password", Toast.LENGTH_SHORT).show();
                                registerpass.setText("");
                            }
                            else if(response.toString().contains("User Login Successfull")) {

                                Toast.makeText(Welcome.this,"Welcome"+name, Toast.LENGTH_SHORT).show();

                                Intent i=new Intent(Welcome.this,MainActivity.class);
                                startActivity(i);
                            }
                           // button.revertAnimation();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(Welcome.this,error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("Error",error.toString());


                       // button.revertAnimation();
                    }
                });
    }


}

/*
public class Welcome extends AppCompatActivity {
    Button button2,button;
    EditText editText2,editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button = (Button) findViewById(R.id.button);
        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText pass=(EditText)findViewById(R.id.editText2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Welcome.this, Register.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EditText name=(EditText) findViewById(R.id.username);
                //final EditText pass=(EditText)findViewById(R.id.password);
                String us=name.getText().toString();
                String ps=pass.getText().toString();
                String type="login";


                Intent i = new Intent(Welcome.this, MainActivity.class);
              //
                startActivity(i);
            }
        });

    }
    public class backgroundworker extends AsyncTask<String,Void,String> {
        Context context;
        //AlertDialog alertDialog;
        backgroundworker(View.OnClickListener context)
        {


        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String login_url="http://localhost/wizcraft/connect.php";
            if(type.equals("login")){
                try {
                    String us=params[1];
                    String ps=params[2];


                    URL url=new URL(login_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("us","UTF-8")+"="+URLEncoder.encode(us,"UTF-8")+"&"+URLEncoder.encode("ps","UTF-8")+"="+URLEncoder.encode(ps,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while ((line=bufferedReader.readLine())!=null)
                    {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            //alertDialog = new AlertDialog.Builder(context).create();
            //alertDialog.setTitle("Login Status");
        }

        @Override
        protected void onPostExecute(String result) {
            //   alertDialog.setMessage(result);
            // alertDialog.show();
            Toast.makeText(Welcome.this, "Login", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
*/