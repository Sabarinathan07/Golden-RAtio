package com.sabari.bottomnav.ui.scoreboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sabari.bottomnav.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    TextView name,score;
    ArrayList list;
    LayoutInflater inflater;
    ImageView image;
    String sName;
    Context context;

    public Adapter(Context context,	ArrayList<ScorePage> scoreList) {
        this.list = scoreList;
        inflater=LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.score_items,viewGroup,false);
        name = v.findViewById(R.id.name);
        score = v.findViewById(R.id.score);

        ScorePage scorePage = (ScorePage) list.get(i);

        name.setText(scorePage.getUsername());
        score.setText(scorePage.getScore());
        image= v.findViewById(R.id.imageView4);

        sName = scorePage.getUsername();

        String sUrl = "https://ui-avatars.com/api/?name=";
        //SabariNathan&background=8bc24a&color=fff&rounded=true";
        sUrl= sUrl + sName+"&background=8bc24a&color=fff&rounded=true";





        Glide.with(context).load(sUrl).into(image);


        return v;
    }
}
