package com.egorrridze.ratingchgk.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Adapters.ParsePlayerPageAdapter;
import com.egorrridze.ratingchgk.Models.ParseItemPlayerPage;
import com.egorrridze.ratingchgk.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerPageFragment extends Fragment {

    private TextView player_name;
    private TextView player_team;
    private RecyclerView recyclerView;
    private ParsePlayerPageAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<ParseItemPlayerPage> parseItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_player_page, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        player_name = (TextView) view.findViewById(R.id.playerPage_name);
        player_team = (TextView) view.findViewById(R.id.playerPage_team);
        player_name.setText(getArguments().getString("player_name"));
        player_team.setText(getArguments().getString("player_team"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_playersTournaments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ParsePlayerPageAdapter(parseItems, getActivity());
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();

        return view;
    }

    private class Content extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            //progressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String baseUrl = "https://rating.chgk.info";
                String detailUrl = getArguments().getString("player_url");
                String url = baseUrl + detailUrl;
                Document table = Jsoup.connect(url).get();
                Elements rows = table.select("tr");
                boolean head_of_table = false;
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get((i));
                    Elements cols = row.select("td");
                    if (cols.size() == 1)
                    {
                        // номер сезона
                    }
                    else if (cols.size() == 12)
                    {
                        if (head_of_table) {
                            String player_tournament_name = cols.get(2).text();
                            String player_tournament_date = cols.get(4).text();
                            String player_tournament_result = cols.get(8).text();
                            parseItems.add(new ParseItemPlayerPage(player_tournament_name, player_tournament_date, player_tournament_result));
                            Log.d("items", "tourn name: " + player_tournament_name + ". tourn date: " + player_tournament_date + ". tourn result: " + player_tournament_result);
                        }
                        head_of_table = true;
                    }
                }
            } catch (IOException e){
                e.printStackTrace();;
            }
            return null;
        }
    }
}
