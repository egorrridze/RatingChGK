package com.egorrridze.ratingchgk.Models;

public class ParseItemTeam {
    private String team_position;
    private String team_name;
    private String team_city;
    private String team_rating;

    public ParseItemTeam() {
    }

    public ParseItemTeam(String team_position, String team_name, String team_city, String team_rating) {
        this.team_position = team_position;
        this.team_name = team_name;
        this.team_city = team_city;
        this.team_rating = team_rating;
    }

    public String getTeam_position() {
        return team_position;
    }

    public void setTeam_position(String team_position) {
        this.team_position = team_position;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_city() {
        return team_city;
    }

    public void setTeam_city(String team_city) {
        this.team_city = team_city;
    }

    public String getTeam_rating() {
        return team_rating;
    }

    public void setTeam_rating(String team_rating) {
        this.team_rating = team_rating;
    }
}
