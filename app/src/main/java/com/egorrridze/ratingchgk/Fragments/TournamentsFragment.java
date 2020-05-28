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

import com.egorrridze.ratingchgk.Adapters.ParseTournamentAdapter;
import com.egorrridze.ratingchgk.Models.ParseItemTournament;
import com.egorrridze.ratingchgk.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TournamentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParseTournamentAdapter adapter;
    private ArrayList<ParseItemTournament> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tournaments, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tournaments);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ParseTournamentAdapter(parseItems, getActivity());
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
            //saveData();
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String html = "https://rating.chgk.info/tournaments";
                Document table = Jsoup.connect(html).get();
                Elements rows = table.select("tr");
                boolean head_of_table = false;
                for (int i = 0; i < rows.size(); i++) {
                    Element row = rows.get((i)); //по номеру индекса получает строку
                    Elements cols = row.select("td");// разбиваем полученную строку по тегу  на столбцы
                    if (cols.size() == 1)
                    {

                    }
                    else if (cols.size() == 8)
                    {
                        if (head_of_table) {
                            String tournament_name = cols.get(1).text();
                            String tournament_date = cols.get(2).text();
                            String tournament_type = cols.get(3).text();
                            parseItems.add(new ParseItemTournament(tournament_name, tournament_date, tournament_type));
                            Log.d("items", "name: " + tournament_name + " date: " + tournament_date + " type: " + tournament_type);
                        }
                        head_of_table = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
