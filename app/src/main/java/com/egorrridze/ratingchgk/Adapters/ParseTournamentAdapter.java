package com.egorrridze.ratingchgk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Models.ParseItemTournament;
import com.egorrridze.ratingchgk.R;

import java.util.ArrayList;

public class ParseTournamentAdapter extends RecyclerView.Adapter<ParseTournamentAdapter.ViewHolder> {

    private ArrayList<ParseItemTournament> parseItems;
    private Context context;

    public ParseTournamentAdapter(ArrayList<ParseItemTournament> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseTournamentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournaments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseTournamentAdapter.ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(position % 2 == 0 ? "#F4F0CB" : "#DED29E"));
        ParseItemTournament parseItemTournament = parseItems.get(position);
        holder.tournament_name.setText(parseItemTournament.getTournament_name());
        holder.tournament_date.setText(parseItemTournament.getTournament_date());
        holder.tournament_type.setText(parseItemTournament.getTournament_type());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tournament_name;
        TextView tournament_date;
        TextView tournament_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tournament_name = itemView.findViewById(R.id.tournament_name);
            tournament_date = itemView.findViewById(R.id.tournament_date);
            tournament_type = itemView.findViewById(R.id.tournament_type);
        }
    }
}
