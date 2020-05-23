package com.egorrridze.ratingchgk.Models;

public class ParseItemPlayerPage {
    private String player_tournament_name;
    private String player_tournament_date;
    private String player_tournament_result;

    public ParseItemPlayerPage() {
    }

    public ParseItemPlayerPage(String player_tournament_name, String player_tournament_date, String player_tournament_result) {
        this.player_tournament_name = player_tournament_name;
        this.player_tournament_date = player_tournament_date;
        this.player_tournament_result = player_tournament_result;
    }

    public String getPlayer_tournament_name() {
        return player_tournament_name;
    }

    public void setPlayer_tournament_name(String player_tournament_name) {
        this.player_tournament_name = player_tournament_name;
    }

    public String getPlayer_tournament_date() {
        return player_tournament_date;
    }

    public void setPlayer_tournament_date(String player_tournament_date) {
        this.player_tournament_date = player_tournament_date;
    }

    public String getPlayer_tournament_result() {
        return player_tournament_result;
    }

    public void setPlayer_tournament_result(String player_tournament_result) {
        this.player_tournament_result = player_tournament_result;
    }
}
