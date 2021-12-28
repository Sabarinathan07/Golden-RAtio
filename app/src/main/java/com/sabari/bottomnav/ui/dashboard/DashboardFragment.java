package com.sabari.bottomnav.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sabari.bottomnav.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {



    ListView listView;

    Adapter adapter;

    ArrayList<ApplicationList> applicationList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listView = v.findViewById(R.id.application_listView);

        applicationList = new ArrayList<ApplicationList>();


        applicationList.add(new ApplicationList(getString(R.string.application_heading1),getString(R.string.application_multiline1)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading2),getString(R.string.application_multiline2)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading3),getString(R.string.application_multiline3)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading8),getString(R.string.application_multiline8)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading4),getString(R.string.application_multiline4)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading5),getString(R.string.application_multiline5)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading6),getString(R.string.application_multiline6)));
        applicationList.add(new ApplicationList(getString(R.string.application_heading7),getString(R.string.application_multiline7)));



        adapter = new Adapter(getActivity(),applicationList);
        listView.setAdapter(adapter);
         return v;
    }

    public static int[] images = new int[]{
            R.drawable.a1,R.drawable.a3,R.drawable.a2,R.drawable.a8,R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7
};

}