package com.sabari.bottomnav.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabari.bottomnav.R;
import com.sabari.bottomnav.ui.home.HomePage;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList list;
    TextView heading,multilineText;
    ImageView imageView;

    public Adapter(Context context, ArrayList<ApplicationList> list){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
        View v = layoutInflater.inflate(R.layout.application_items,viewGroup,false);
        ApplicationList obj = (ApplicationList) list.get(i);

        heading = v.findViewById(R.id.heading);
        multilineText = v.findViewById(R.id.multilineTexts);
        imageView = v.findViewById(R.id.applicationImage);

        heading.setText(obj.getHeading());
        multilineText.setText(obj.getMultiline());

        return v;
    }
}
