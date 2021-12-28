package com.sabari.bottomnav.ui.home;

public class HomePage {

    private String heading,multiline;

    public HomePage(String heading,String multiline){
        this.heading = heading;
        this.multiline = multiline;
    }

    public String getHeading() {
        return heading;
    }

    public String getMultiline() {
        return multiline;
    }
}
