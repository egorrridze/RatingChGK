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

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
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
            /*try {
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
            }*/
            try {
                LocalDate date = LocalDate.now();

                URL url = new URL("http://api.rating.chgk.net/tournaments?page=1&dateStart%5Bafter%5D=" + date.minusMonths(1) + "&dateEnd%5Bbefore%5D=" + date.plusMonths(1));
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("accept", "application/json");

                //http.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                JSONArray jsonTournaments = new JSONArray(content.toString());
                for (int i = 0; i < jsonTournaments.length(); i++) {
                    String tournament_name = jsonTournaments.getJSONObject(i).get("name").toString();
                    String tournament_type = jsonTournaments.getJSONObject(i).getJSONObject("type").get("name").toString().substring(0, 1);

                    String d = jsonTournaments.getJSONObject(i).get("dateEnd").toString();
                    /*приведение к типу LocalDate (кажется, лишнее)
                    LocalDate date0 = LocalDate.of(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)), Integer.parseInt(d.substring(8, 10)));
                    String tournament_date = date0.getDayOfMonth() + "." + date0.getMonthValue() + "." + date0.getYear();*/
                    String tournament_date = d.substring(8, 10) + "." + d.substring(5, 7) + "." + d.substring(0, 4);
                    parseItems.add(new ParseItemTournament(tournament_name, tournament_date, tournament_type));
                    Log.d("items", "name: " + tournament_name + " date: " + tournament_date + " type: " + tournament_type);

                }
                http.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
