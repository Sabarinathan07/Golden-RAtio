package com.sabari.bottomnav.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sabari.bottomnav.LoginActivity;
import com.sabari.bottomnav.MainActivity;
import com.sabari.bottomnav.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private RadioGroup buttonGroup;
    RadioButton radioButton;
    private TextView textView;
    private Button save;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //loadLoacle();
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        buttonGroup = v.findViewById(R.id.radio);
        textView = v.findViewById(R.id.textView);
        save = v.findViewById(R.id.button);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = buttonGroup.getCheckedRadioButtonId();
                radioButton = buttonGroup.findViewById(radioId);

                switch(radioId){
                    case R.id.radio1:
                        setLocale("ta");
                        //recreate();
                        break;
                    case R.id.radio2:
                        setLocale("en");
                        //recreate();
                        break;
                    case R.id.radio3:
                        setLocale("hi");
                        //recreate();
                        break;

                }

            }

        });



        return v;
    }


    public void setLocale(String lang){
      /*  Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config , getActivity().getResources().getDisplayMetrics());
        */

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("lang",lang);
        editor.apply();
        requireActivity().finish();
        startActivity(new Intent(getContext(), MainActivity.class));

    }

 /*   public void loadLoacle(){
        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("lang","");
        setLocale(language);
    }
*/

 

}
