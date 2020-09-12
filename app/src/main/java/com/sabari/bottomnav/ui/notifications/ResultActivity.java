package com.sabari.bottomnav.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sabari.bottomnav.Constants;
import com.sabari.bottomnav.MainActivity;
import com.sabari.bottomnav.R;
import com.sabari.bottomnav.RequestHandler;
import com.sabari.bottomnav.SharePrefManager;
import com.sabari.bottomnav.StartupPage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    TextView grade,finalScore;
    Button retry,finish;
    ProgressDialog progressDialog;

    String name,sScore;

    int lastScore;
    int best1,best2,best3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        grade = findViewById(R.id.grade);
        finalScore = findViewById(R.id.outOf);
        retry = findViewById(R.id.retry);
        finish = findViewById(R.id.finish);

        progressDialog = new ProgressDialog(this);

        String finalScr , bes1,bes2,bes3,lastScr;
        bes1 = getString(R.string.best1);
        bes2 = getString(R.string.best2);
        bes3 = getString(R.string.best3);
        lastScr = getString(R.string.lastScore);

        name = SharePrefManager.getInstance(this).getName();









        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        sScore = Integer.toString(score);

        updateResult(name,sScore);


        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastScore",score);
        editor.apply();


        lastScore = preferences.getInt("lastScore",0);
        best1 = preferences.getInt("best1",0);
        best2 = preferences.getInt("best2",0);
        best3 = preferences.getInt("best3",0);



        if(lastScore> best3){
            best3 = lastScore;

            editor.putInt("best3",best3);
            editor.apply();
        }

        if(lastScore> best2){
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            //SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2",best2);
            editor.putInt("best3",best3);
            editor.apply();
        }

        if(lastScore> best1){
            int temp = best1;
            //int temp2 = best2;
            best1 = lastScore;
            best2 = temp;
            //best3 = temp2;
            //SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best1",best1);
            editor.putInt("best2",best2);
            editor.putInt("best3",best3);
            editor.apply();
        }


        finalScr = lastScr + lastScore + "\n"+ bes1 + best1 +"\n"+ bes1 +best2 +"\n"+ bes1 + best3 ;

        finalScore.setText(finalScr);

        if(score==10){
            grade.setText(getString(R.string.result1));
        }else if(score==9){
            grade.setText(getString(R.string.result2));
        }else if(score == 8){
            grade.setText(getString(R.string.result3));
        }else if(score<=7 && score>=5){
            grade.setText(getString(R.string.result4));
        }else{
            grade.setText(getString(R.string.result5));
        }

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), StartupPage.class);
                startActivity(i);
            }
        });


    }


    private void updateResult(String name,String score ) {


        final String sName = name;
        final String sScore = score;





        progressDialog.setMessage("Updating Result...");
        progressDialog.show();

        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_SCORE,
                new Response.Listener<String>(){




                    @Override
                    public void onResponse(String Response) {
                        progressDialog.dismiss();
                        try {

                            JSONObject jsonobject = new JSONObject(Response);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();


                    }


                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", sName);
                params.put("score",sScore );

                return params;
            }




        };

        RequestHandler.getInstance(this).addToRequestQueue(stringrequest);


    }
}