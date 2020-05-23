package com.egorrridze.ratingchgk.Models;

public class ParseItemTournament {
    private String tournament_name;
    private String tournament_date;
    private String tournament_type;

    public ParseItemTournament() {
    }

    public ParseItemTournament(String pt_position, String pt_name, String pt_team) {
        this.tournament_name = pt_position;
        this.tournament_date = pt_name;
        this.tournament_type = pt_team;
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getTournament_date() {
        return tournament_date;
    }

    public void setTournament_date(String tournament_date) {
        this.tournament_date = tournament_date;
    }

    public String getTournament_type() {
        return tournament_type;
    }

    public void setTournament_type(String tournament_type) {
        this.tournament_type = tournament_type;
    }

}
