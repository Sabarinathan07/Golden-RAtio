package com.sabari.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sabari.bottomnav.ui.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    Button login;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocaleHelper.setLocale(this,SharedPreferencesUtil.getLanguage(getApplicationContext()));

        setContentView(R.layout.activity_login);

        //Toast.makeText(this, "lang"+SharedPreferencesUtil.getLanguage(this), Toast.LENGTH_SHORT).show();

        username=(EditText) findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login= (Button)findViewById(R.id.login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));

        login.setOnClickListener(this);

  
    }


    public void CheckConnection(){
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(null!=nInfo){
            userLogin();
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.check_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void userLogin(){
        final String sUsername = username.getText().toString().trim();
        final String sPassword = password.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_USER_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharePrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("email")
                                        );

                                SharePrefManager.getInstance(getApplicationContext()).name(obj.getString("username"));



                                Toast.makeText(getApplicationContext(), getString(R.string.valid_login), Toast.LENGTH_LONG).show();

                                boolean isError = obj.getBoolean("error");
                                if(isError){

                                }
                                else{

                                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                    finish();
//                                    Bundle bundle = new Bundle();
//                                    bundle.putInt("teacher",0);
//                                    i.putExtras(bundle);
//                                    startActivity(i);

                                }

                            }else{
                                Toast.makeText(getApplicationContext(), getString(R.string.invalid_login), Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }


                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    };
                }
        ){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", sUsername);
                params.put("password",sPassword);

                return params;
            }


        };
//				  RequestQueue requestQueue = Volley.newRequestQueue(this);
//			        requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }









    @Override
    protected void attachBaseContext(Context newBase) {
        LocaleHelper.setLocale(newBase, SharedPreferencesUtil.getLanguage(newBase));
        Log.e( "attachBaseContext: ","inside" );
        super.attachBaseContext(newBase);

    }

    @Override
    public void onClick(View view) {
        if(view == login){


            CheckConnection();

        }
    }
}