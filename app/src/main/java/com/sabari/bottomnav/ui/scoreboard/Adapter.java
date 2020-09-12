package com.sabari.bottomnav.ui.scoreboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sabari.bottomnav.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    TextView name,score;
    ArrayList list;
    LayoutInflater inflater;

    public Adapter(Context context,	ArrayList<ScorePage> scoreList) {
        this.list = scoreList;
        inflater=LayoutInflater.from(context);

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


        return v;
    }
}
