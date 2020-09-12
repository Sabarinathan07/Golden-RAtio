package com.sabari.bottomnav.ui.scoreboard;

public class ScorePage {
    private String username,score;

    public ScorePage(String username, String score) {
            this.score = score;
            this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public String getScore() {
        return score;
    }
}
