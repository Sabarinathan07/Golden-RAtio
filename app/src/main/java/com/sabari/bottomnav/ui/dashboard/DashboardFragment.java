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

    private DashboardViewModel dashboardViewModel;

    ListView listView;
    TextView heading,subject;
    Adapter adapter;
    ImageView img1,img2,img3;
    ArrayList<ApplicationList> applicationList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listView = v.findViewById(R.id.application_listView);
        heading = v.findViewById(R.id.topicHeading);
        subject = v.findViewById(R.id.editTextTextMultiLine);
        img1= v.findViewById(R.id.imageView1);
          
        img2 = v.findViewById(R.id.imageView2);

        applicationList = new ArrayList<ApplicationList>();



        applicationList.add(new ApplicationList("Spiral Galaxies","The Fibonaccian spiral is also observed in case of a spiral galaxy." +
                " Our own galaxy―the Milky Way―is one such celestial entity." +
                " Certain other entities within the galaxy also exhibit the golden ratio. It is found in the ratio of the diameters of Saturn and its rings." +
                " It is also the ratio of the distances of Venus and the Earth from the Sun." +
                " Interestingly, the ratio of the revolutions of these two planets also yields the golden ratio."));
        applicationList.add(new ApplicationList("Hurricanes","As in the case of shells and spiral galaxies," +
                " the movement of air and wind in hurricanes also follows the Fibonaccian spiral, " +
                "revealing the golden ratio. The spiral nature of a hurricane is largely due to the simultaneous movement of the air and" +
                " atmospheric elements between a low pressure area (epicenter of the hurricane) and the surrounding high pressure area." ));
        applicationList.add(new ApplicationList("Fruits and Vegetables","The same pattern is observed in the case of fractal-like fruits and vegetables. The most common examples are pineapple, red cabbage, artichokes, and Romanian cauliflower (image)." +
                " In these fruits and vegetables, it is easy to visualize the spiral patterns along their surface."));
        applicationList.add(new ApplicationList("DNA","A DNA molecule is 34Å in length and 21Å in width." +
                " The ratio is approximately equal to the golden ratio. The same is true for the ratio of the two grooves of the helical DNA molecule," +
                " i.e., the major (21Å) and the minor (13Å) groove."));
        applicationList.add(new ApplicationList("Animal Bodies","Animals show a wide range of body structures. " +
                "Despite this vast range, they still exhibit the divine proportion in various parts of their bodies. Some examples include:\n" +
                "\n" +
                "► Dolphins: Dimensions (length:breadth) of eyes, fins, as well as tail section.\n" +
                "► Penguins: The ratio of the position of the body markings at the eyes, beak, and wings, in contrast with its total height.\n" +
                "► Tiger: Almost all the facial features and their positions show golden sections, including the ratio of the length and breadth of the face.\n" +
                "► Insects: The ratios of the body segments (head, thorax, and abdomen) to each other are golden sections."));
        applicationList.add(new ApplicationList("Human Bodies","Golden ratios that are observed in the human body are as follows:\n" +
                "\n" +
                "► Head to toe ● Head to navel\n" +
                "► Ratio of the length of each digit of a finger\n" +
                "► Shoulder to fingertip ● Shoulder to elbow\n" +
                "► Hip bone to heel ● Hip bone to knee\n" +
                "► Chest length ● Waist length"));


        adapter = new Adapter(getActivity(),applicationList);
        listView.setAdapter(adapter);
         return v;
    }
}