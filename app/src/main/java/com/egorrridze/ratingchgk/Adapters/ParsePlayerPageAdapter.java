package com.egorrridze.ratingchgk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Models.ParseItemPlayerPage;
import com.egorrridze.ratingchgk.R;

import java.util.ArrayList;

public class ParsePlayerPageAdapter extends RecyclerView.Adapter<ParsePlayerPageAdapter.ViewHolder> {

    private ArrayList<ParseItemPlayerPage> parseItems;
    private Context context;

    public ParsePlayerPageAdapter(ArrayList<ParseItemPlayerPage> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParsePlayerPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParsePlayerPageAdapter.ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(position % 2 == 0 ? "#F4F0CB" : "#DED29E"));
        ParseItemPlayerPage parseItem = parseItems.get(position);
        holder.player_tournament_name.setText(parseItem.getPlayer_tournament_name());
        holder.player_tournament_date.setText(parseItem.getPlayer_tournament_date());
        holder.player_tournament_result.setText(parseItem.getPlayer_tournament_result());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView player_tournament_name;
        TextView player_tournament_date;
        TextView player_tournament_result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            player_tournament_name = itemView.findViewById(R.id.player_tournament_name);
            player_tournament_date = itemView.findViewById(R.id.player_tournament_date);
            player_tournament_result = itemView.findViewById(R.id.player_tournament_result);
        }
    }
}