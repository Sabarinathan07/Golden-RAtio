package com.sabari.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.View;
import android.view.View.OnClickListener;

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

public class SignUpActivity extends AppCompatActivity implements OnClickListener {
    EditText name,email,username,password,phone;
    Button signUp;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(this, SharedPreferencesUtil.getLanguage(getApplicationContext()));
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        signUp = (Button) findViewById(R.id.signup);
        progressDialog = new ProgressDialog(this);


        signUp.setOnClickListener(this);
    }

    public void CheckConnection(){
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(null!=nInfo){
            registerUser();
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.check_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void registerUser(){
        final String sName = name.getText().toString().trim();
        final String sEmail = email.getText().toString().trim();
        final String sUserName = username.getText().toString().trim();
        final String sPassword = password.getText().toString().trim();
        final String sPhone = phone.getText().toString().trim();


        SharePrefManager.getInstance(getApplicationContext()).name(sUserName);

        progressDialog.setMessage(getString(R.string.regiter_user));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_USER_REGISTER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String Response){
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(Response);

                           // Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            boolean isError = jsonObject.getBoolean("error");
                            if(isError){
                                Toast.makeText(getApplicationContext(),getString(R.string.toast_try_again),Toast.LENGTH_LONG).show();
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                finish();
                              /*  Bundle bundle = new Bundle();
                                bundle.putInt("teacher",0);
                                i.putExtras(bundle);
                                startActivity(i);
                                */
                            }


                        } catch (JSONException e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }

                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show() ;
                    }
                }


        ){
            @Override
            protected Map<String, String> getParams()throws  AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", sName);
                params.put("email", sEmail);
                params.put("username", sUserName);
                params.put("password", sPassword);
                params.put("phone",sPhone);

                return params;

            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//	 RequestQueue requestQueue = Volley.newRequestQueue(this);
//     requestQueue.add(stringRequest);

    }

    private boolean validateEmail(String email) {
        if(email!=null && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else{
            return false;
        }

    }

    protected boolean validateDetails(String name, String email,String username,String phone) {
        if(name!=null && username!=null && name.length()>3 && username.length()>3 && phone.length()>7){
            return true;
        }else{
            return false;
        }
    }

    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>7){
            return true;
        }else{
            return false;
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        LocaleHelper.setLocale(newBase, SharedPreferencesUtil.getLanguage(newBase));
        super.attachBaseContext(newBase);

    }

    @Override
    public void onClick(View view) {
            if(view==signUp){
                if(!validateDetails(name.getText().toString(),email.getText().toString(),username.getText().toString(),phone.getText().toString()))
                {

                    Toast.makeText(getApplicationContext(), getString(R.string.validate_details), Toast.LENGTH_LONG).show();
                }else{
                    if(!validateEmail(email.getText().toString())){
                        email.setError(getString(R.string.validate_email));
                        email.requestFocus();

                    }else{

                        if (!validatePassword(password.getText().toString())){
                            password.setError(getString(R.string.validate_password));
                            password.requestFocus();

                        }else{
                            CheckConnection();

                        }

                    }

                }

            } }


    }
