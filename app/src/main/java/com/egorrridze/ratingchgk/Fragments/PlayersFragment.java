package com.egorrridze.ratingchgk.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.egorrridze.ratingchgk.Adapters.ParseAdapter;
import com.egorrridze.ratingchgk.Models.ParseItem;
import com.egorrridze.ratingchgk.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PlayersFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_players, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_players);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ParseAdapter(parseItems, getActivity());
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
                String url = "https://rating.chgk.info/players.php?page=1";
                Document table = Jsoup.connect(url).get();
                Elements rows = table.select("tr");
                int size = rows.size();
                for (int i = 2; i < size; i++) {
                    Element row = rows.get((i));  // по номеру индекса получаем строку
                    Elements cols = row.select("td"); // разбиваем полученную строку по тегу  на столбы
                    String player_position = cols.get(1).text();
                    String player_name = cols.get(7).text();
                    String player_team = cols.get(9).text();
                    String player_rating = cols.get(3).text();
                    parseItems.add(new ParseItem(player_position, player_name, player_team, player_rating));
                    Log.d("items", "pos: " + player_position + " . name: " + player_name + " . team: " + player_team + " . rating" + player_rating);
                }
            } catch (IOException e){
                e.printStackTrace();;
            }
            return null;
        }
    }
}
