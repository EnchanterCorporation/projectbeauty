package com.example.enchanter19.projectbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText phone,pass;
    Button button;
    String sphone,spass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        phone=(EditText)findViewById(R.id.contact);
        pass=(EditText)findViewById(R.id.pass299);
        button=(Button)findViewById(R.id.logbut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sphone = phone.getText().toString();
                spass = pass.getText().toString();
                logininto(sphone,spass);
            }
        });


    }
    public void logininto(final String sphone1, final String spass1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, globalurl.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String uname1 = users.getString("phonenumber");
                        String uage1 = users.getString("password");
                        Intent intent=new Intent(Login.this,MainActivity.class);
                       intent.putExtra("ghtw",uname1);
                        intent.putExtra("sssw",uage1);
                        startActivity(intent);
                        //   Toast.makeText(getApplicationContext(),mobile_number,Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Server Busy",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> insert = new HashMap<String, String>();
                insert.put("phonenumber", sphone1);
                insert.put("password", spass1);

                return insert;

            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


}
