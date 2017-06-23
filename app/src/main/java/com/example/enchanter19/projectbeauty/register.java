package com.example.enchanter19.projectbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class register extends AppCompatActivity {
    EditText edname,edlast,edphone,edpass,edconpass;
    Button button;
    String sedname,sedlast,sedphone,sedpass,sedconpass;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edname=(EditText)findViewById(R.id.first);
        edlast=(EditText)findViewById(R.id.last);
        edphone=(EditText)findViewById(R.id.phone);
        edpass=(EditText)findViewById(R.id.pass1);
        log=(TextView)findViewById(R.id.loginjj);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(register.this,Login.class);
                startActivity(intent);
            }
        });
        edconpass=(EditText)findViewById(R.id.confirm);
        button=(Button)findViewById(R.id.buttonsub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sedname = edname.getText().toString();
                sedlast = edlast.getText().toString();
                sedphone =edphone.getText().toString();
                sedpass = edpass.getText().toString();
                sedconpass = edconpass.getText().toString();
                if(sedname.length()==0)
                {
                    edname.requestFocus();
                    edname.setError("FIELD CANNOT BE EMPTY");
                }

                if(sedlast.length()==0)
                {
                    edlast.requestFocus();
                    edlast.setError("FIELD CANNOT BE EMPTY");
                }
                if(sedphone.length()==0)
                {
                    edphone.requestFocus();
                    edphone.setError("FIELD CANNOT BE EMPTY");
                }
                if(sedphone.length()<10)
                {
                    edphone.requestFocus();
                    edphone.setError(" phone number should be equal to 10");

                }
                if(sedphone.length()>10)
                {
                    edphone.requestFocus();
                    edphone.setError(" phone number should be equal to 10");

                }
                if(sedpass.length()<6)
                {
                    edpass.requestFocus();
                    edpass.setError("Password should be more than 6 characters");
                }
                if(sedpass==sedconpass)
                {
                    edconpass.requestFocus();
                    edconpass.setError("password should be equal to confirm password");
                }

                insertnew(sedname, sedlast, sedphone,sedpass);
                Toast.makeText(getApplicationContext(),"Registration has been successfull",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void insertnew(final String sedname1, final String sedlast1, final String sedphone1, final String sedpass1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, globalurl.URL_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exists");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("users");
                        String uname1 = users.getString("name");
                        String uage1 = users.getString("age");
                        Intent intent=new Intent(register.this,MainActivity.class);
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
                insert.put("firstname", sedname1);
                insert.put("lastname", sedlast1);
                insert.put("phonenumber",sedphone1);
                insert.put("password", sedpass1);

                return insert;

            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

}
