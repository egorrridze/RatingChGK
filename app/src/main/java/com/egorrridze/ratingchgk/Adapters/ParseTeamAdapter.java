package com.egorrridze.ratingchgk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Models.ParseItemTeam;
import com.egorrridze.ratingchgk.R;

import java.util.ArrayList;

public class ParseTeamAdapter extends RecyclerView.Adapter<ParseTeamAdapter.ViewHolder> {

    private ArrayList<ParseItemTeam> parseItems;
    private Context context;

    public ParseTeamAdapter(ArrayList<ParseItemTeam> parseItems, Context context) {   //конструктор
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseTeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseTeamAdapter.ViewHolder holder, int position) {
        ParseItemTeam parseItemTeam = parseItems.get(position);
        holder.team_position.setText(parseItemTeam.getTeam_position());
        holder.team_rating.setText(parseItemTeam.getTeam_rating());
        holder.team_name.setText(parseItemTeam.getTeam_name());
        holder.team_city.setText(parseItemTeam.getTeam_city());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView team_position;
        TextView team_rating;
        TextView team_name;
        TextView team_city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team_position = itemView.findViewById(R.id.team_position);
            team_rating = itemView.findViewById(R.id.team_rating);
            team_name = itemView.findViewById(R.id.team_name);
            team_city = itemView.findViewById(R.id.team_city);
        }
    }
}
