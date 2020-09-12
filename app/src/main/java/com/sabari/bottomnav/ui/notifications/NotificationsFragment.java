package com.sabari.bottomnav.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.sabari.bottomnav.R;

public class NotificationsFragment extends Fragment {
    private RadioGroup buttonGroup;
    RadioButton radioButton;
    private TextView choice1,choice2,choice3;
    private TextView questionId;
    private ImageView imageView;
    private Button next,exit;
    private QuestionLibrary mQuestionLibrary;

    String[] array;
    String[] choices1;
    String[] choices2;
    String[] choices3;
    String[] answers;
    String answer;
    int score,qnNumber=0;

    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notifications, container, false);


      buttonGroup = v.findViewById(R.id.radioGroup);
        choice1=v.findViewById(R.id.radio_one);
        choice2=v.findViewById(R.id.radio_two);
        choice3=v.findViewById(R.id.radio_three);
        questionId=v.findViewById(R.id.question);
        imageView=v.findViewById(R.id.qnImage);
        next=v.findViewById(R.id.next);
        exit = v.findViewById(R.id.exit);
        mQuestionLibrary = new QuestionLibrary();

        array = mQuestionLibrary.getQuestions(getActivity());
        choices1 = mQuestionLibrary.getChoice1(getActivity());
        choices2 = mQuestionLibrary.getChoice2(getActivity());
        choices3 = mQuestionLibrary.getChoice3(getActivity());
        answers = mQuestionLibrary.getAnswers(getActivity());



        updateQuestion();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = buttonGroup.getCheckedRadioButtonId();
                    radioButton = buttonGroup.findViewById(radioId);
                    String strSelected=radioButton.getText().toString();
                    if(strSelected.equals(answer)){


                        //Toast.makeText(getActivity(),getString(R.string.correct),Toast.LENGTH_SHORT).show();
                        score=score+1;


                        if(qnNumber==array.length){

                            Intent i = new Intent(getActivity(),ResultActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("finalScore",score);
                            i.putExtras(bundle);
                            startActivity(i);
                        }else{
                            updateQuestion();
                        }
                    }else{
                        //Toast.makeText(getActivity(),getString(R.string.worng),Toast.LENGTH_SHORT).show();
                        if(qnNumber==array.length){
                            Intent i = new Intent(getActivity(),ResultActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("finalScore",score);
                            i.putExtras(bundle);
                            startActivity(i);
                        }else{
                            updateQuestion();
                        }
                    }


            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("finalScore",score);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        return v;
    }



    private void updateQuestion() {
        questionId.setText(qnNumber+1 +". "+array[qnNumber]);
        choice1.setText(choices1[qnNumber]);
        choice2.setText(choices2[qnNumber]);
        choice3.setText(choices3[qnNumber]);
//        choice2.setText(mQuestionLibrary.getChoice2(qnNumber));
//        choice3.setText(mQuestionLibrary.getChoice3(qnNumber));

        answer = answers[qnNumber];

        imageView.setImageResource(QuestionLibrary.qnImages[qnNumber]);

        qnNumber++;
    }




}