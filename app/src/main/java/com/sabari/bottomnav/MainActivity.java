package com.sabari.bottomnav;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sabari.bottomnav.ui.SharedPreferencesUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        LocaleHelper.setLocale(this,SharedPreferencesUtil.getLanguage(getApplicationContext()));

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

/*    @Override
    protected void attachBaseContext(Context newBase) {
        LocaleHelper.setLocale(newBase, SharedPreferencesUtil.getLanguage(newBase));
        super.attachBaseContext(newBase);

    }
    */
@Override
protected void attachBaseContext(Context newBase) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Configuration config = newBase.getResources().getConfiguration();
        //Update your config with the Locale i. e. saved in SharedPreferences

        String language = SharedPreferencesUtil.getLanguage(newBase);
        config.setLocale(new Locale(language));
        newBase = newBase.createConfigurationContext(config);
    }
    super.attachBaseContext(newBase);
}
}