package com.sabari.bottomnav.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.sabari.bottomnav.R;

import java.util.ResourceBundle;

public class QuestionLibrary {
    LayoutInflater layoutInflater;



     public String[] getQuestions(Context context){
         String[] mQuestions  = context.getResources().getStringArray(R.array.questions);
         return mQuestions;

     }

    public String[] getChoice1(Context context){
         String[] mChoice1 = context.getResources().getStringArray(R.array.Choices1);
         return mChoice1;
    }


    public String[] getChoice2(Context context){
        String[] mChoice2 = context.getResources().getStringArray(R.array.Choices2);
        return mChoice2;
    }


    public String[] getChoice3(Context context){
        String[] mChoice3 = context.getResources().getStringArray(R.array.Choices3);
        return mChoice3;
    }

    public String[] getAnswers(Context context){
        String[] mAnswers  = context.getResources().getStringArray(R.array.Answers);
        return mAnswers;

    }





    private String mChoices[][] = {
            {"Phi","Pi","Psi"},
            {"Square Ratio","Rectangle Ratio","Regular Ratio"},
            {"(a+b)/a","(a+b)/b","(a-b)/a"},
            {"Calculus","Trigonometry","Geometry"},
            {"1.618","0.618","2.624"},
            {"0.764","1.618","0.382"},
            {"Greeks","Romans","Persians"},
            {"Dance, Music and Writing","English and Political Justice","Architecture, Music and Design"},
            {"Irrational number","Rational number","Perfect square number"},
            {"Golden mean or Golden section","Silver Ratio","King of ratios"}
    };

    private String mCorrectAnswers[] = {
            "Phi","Rectangle Ratio","(a+b)/a","Geometry","1.618",
            "0.764","Greeks","Architecture, Music and Design","Irrational number","Golden mean or Golden section"
    };

    public static int[] qnImages= new int[]{
        R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6,
            R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10
    };



    public String getChoice1(int a){
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a){
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a){
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
