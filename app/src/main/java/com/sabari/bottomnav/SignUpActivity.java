package com.sabari.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabari.bottomnav.ui.SharedPreferencesUtil;

public class SignUpActivity extends AppCompatActivity {
    EditText name,email,username,password,phone;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(getApplicationContext(), SharedPreferencesUtil.getLanguage(getApplicationContext()));
        setContentView(R.layout.activity_sign_up);
        name = (EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        phone=(EditText)findViewById(R.id.phone);
        signUp=(Button)findViewById(R.id.signup);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }

                    }

                }

            }
        });





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

}