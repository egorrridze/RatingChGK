package com.egorrridze.ratingchgk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Models.ParseItem;
import com.egorrridze.ratingchgk.R;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context) {   //конструктор
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_players, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.player_position.setText(parseItem.getPlayer_position());
        holder.player_rating.setText(parseItem.getPlayer_rating());
        holder.player_name.setText(parseItem.getPlayer_name());
        holder.player_team.setText(parseItem.getPlayer_team());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView player_position;
        TextView player_rating;
        TextView player_name;
        TextView player_team;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            player_position = itemView.findViewById(R.id.player_position);
            player_rating = itemView.findViewById(R.id.player_rating);
            player_name = itemView.findViewById(R.id.player_name);
            player_team = itemView.findViewById(R.id.player_team);
        }
    }
}
