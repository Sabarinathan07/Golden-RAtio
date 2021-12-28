package com.sabari.bottomnav.ui.dashboard;

public class ApplicationList {

    private String heading,multiline;

    public ApplicationList(String heading,String multiline){
        this.heading= heading;
        this.multiline= multiline;
    }

    public String getHeading() {
        return heading;
    }

    public String getMultiline() {
        return multiline;
    }
}
