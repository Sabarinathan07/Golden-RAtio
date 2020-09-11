package com.sabari.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabari.bottomnav.ui.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocaleHelper.setLocale(getApplicationContext(),SharedPreferencesUtil.getLanguage(getApplicationContext()));
        setContentView(R.layout.activity_login);

        Toast.makeText(this, "lang"+SharedPreferencesUtil.getLanguage(this), Toast.LENGTH_SHORT).show();

        username=(EditText) findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login= (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });



    }
}