package com.egorrridze.ratingchgk.Models;

public class ParseItemPlayer {
    private String player_position;
    private String player_name;
    private String player_team;
    private String player_rating;
    private String player_url;

    public ParseItemPlayer() {
    }

    public ParseItemPlayer(String player_position, String player_name, String player_team, String player_rating, String player_url) {
        this.player_position = player_position;
        this.player_name = player_name;
        this.player_team = player_team;
        this.player_rating = player_rating;
        this.player_url = player_url;
    }

    public String getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(String player_position) {
        this.player_position = player_position;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_team() {
        return player_team;
    }

    public void setPlayer_team(String player_team) {
        this.player_team = player_team;
    }

    public String getPlayer_rating() {
        return player_rating;
    }

    public void setPlayer_rating(String player_rating) {
        this.player_rating = player_rating;
    }

    public String getPlayer_url() {
        return player_url;
    }

    public void setPlayer_url(String player_url) {
        this.player_url = player_url;
    }
}
