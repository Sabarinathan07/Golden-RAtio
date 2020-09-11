package com.sabari.bottomnav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sabari.bottomnav.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView listView;
    Adapter adapter;
    ArrayList<HomePage> homeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                          @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        listView = v.findViewById(R.id.homeList);
        homeList =  new ArrayList<HomePage>();

        homeList.add(new HomePage(getString(R.string.home_heading1), getString(R.string.home_multiline1)));
        homeList.add(new HomePage(getString(R.string.home_heading2), getString(R.string.home_multiline2)));
        homeList.add(new HomePage(getString(R.string.home_heading3), getString(R.string.home_multiline3)));
        homeList.add(new HomePage(getString(R.string.home_heading4), getString(R.string.home_multiline4)));
        homeList.add(new HomePage(getString(R.string.home_heading5), getString(R.string.home_multiline5)));




        adapter = new Adapter(getActivity(),homeList);
        listView.setAdapter(adapter);

        return v;
    }

    public static int[] images = new int[]{
            R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5
    };
}