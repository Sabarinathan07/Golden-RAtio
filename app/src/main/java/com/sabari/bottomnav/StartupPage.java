package com.sabari.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sabari.bottomnav.ui.SharedPreferencesUtil;

public class StartupPage extends AppCompatActivity {

    Button btLogin,btRegister;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(getApplicationContext(), SharedPreferencesUtil.getLanguage(getApplicationContext()));
        setContentView(R.layout.activity_startup_page);

        btLogin = (Button)findViewById(R.id.btLogin);
        btRegister = (Button)findViewById(R.id.btRegister);
        image = (ImageView) findViewById(R.id.imageView1234);




        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        LocaleHelper.setLocale(newBase, SharedPreferencesUtil.getLanguage(newBase));
        super.attachBaseContext(newBase);

    }
}