package com.sabari.bottomnav.ui.scoreboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sabari.bottomnav.Constants;
import com.sabari.bottomnav.R;
import com.sabari.bottomnav.RequestHandler;
import com.sabari.bottomnav.ui.scoreboard.Adapter;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreboardFragment extends Fragment implements OnResultsRetrievedListener {

    ListView listView;
    Adapter adapter1;
    ArrayList<ScorePage> scoreList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        listView= v.findViewById(R.id.scoreboard_list);

        scoreList =  new ArrayList<ScorePage>();


        viewResult();


        return v;
    }

    private void viewResult() {

        final OnResultsRetrievedListener listener = this;

        StringRequest stringrequest = new StringRequest(Request.Method.POST, Constants.GET_RESULT,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for(int i =0; i < jsonarray.length(); i++){

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String username,score;

                                username= jsonobject.getString("username");
                                score = jsonobject.getString("score");


                                ScorePage scoreObj = new ScorePage(username,score);


                                scoreList.add(scoreObj);

                            }

                            listener.onSuccess2(scoreList);


                                    

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getLocalizedMessage()+"server error",Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        }){

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();



                return params;
            }

        };




        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringrequest);

    }

    @Override
    public void onSuccess2(ArrayList<ScorePage> scoreList) {
       Adapter adapter1 = new Adapter(getContext(),scoreList);
       listView.setAdapter(adapter1);
    }
}



