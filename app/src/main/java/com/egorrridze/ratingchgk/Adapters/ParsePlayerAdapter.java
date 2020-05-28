package com.egorrridze.ratingchgk.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Fragments.PlayerPageFragment;
import com.egorrridze.ratingchgk.Models.ParseItemPlayer;
import com.egorrridze.ratingchgk.R;

import java.util.ArrayList;

public class ParsePlayerAdapter extends RecyclerView.Adapter<ParsePlayerAdapter.ViewHolder> {

    private ArrayList<ParseItemPlayer> parseItems;
    private Context context;

    public ParsePlayerAdapter(ArrayList<ParseItemPlayer> parseItems, Context context) {   //конструктор
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParsePlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_players, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParsePlayerAdapter.ViewHolder holder, int position) {
        ParseItemPlayer parseItemPlayer = parseItems.get(position);
        holder.player_position.setText(parseItemPlayer.getPlayer_position());
        holder.player_rating.setText(parseItemPlayer.getPlayer_rating());
        holder.player_name.setText(parseItemPlayer.getPlayer_name());
        holder.player_team.setText(parseItemPlayer.getPlayer_team());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemPlayer parseItemPlayer = parseItems.get(position);

            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            Fragment myFragment = new PlayerPageFragment();
            Bundle bundle = new Bundle();
            bundle.putString("player_name", parseItemPlayer.getPlayer_name());
            bundle.putString("player_team", parseItemPlayer.getPlayer_team());
            bundle.putString("player_url", parseItemPlayer.getPlayer_url());
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
        }
    }
}