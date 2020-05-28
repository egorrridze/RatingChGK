package com.egorrridze.ratingchgk.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.egorrridze.ratingchgk.Adapters.ParsePlayerAdapter;
import com.egorrridze.ratingchgk.Models.ParseItemPlayer;
import com.egorrridze.ratingchgk.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayersFragment extends Fragment {

    //private int page_num = 1;
    private RecyclerView recyclerView;
    private ParsePlayerAdapter adapter;
    private ArrayList<ParseItemPlayer> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_players, container, false);

        if(!hasConnection(getActivity())) loadData();

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_players);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ParsePlayerAdapter(parseItems, getActivity());
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();

        return view;
    }

    private void saveData(){
        try {
            SharedPreferences prefs = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(parseItems);
            editor.putString("playersList", json);
            editor.apply();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void loadData(){
        SharedPreferences prefs = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("playersList", null);
        Type type = new TypeToken<ArrayList<ParseItemPlayer>>() {}.getType();
        parseItems = gson.fromJson(json, type);
        if (parseItems == null){
            parseItems = new ArrayList<>();
        }
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
            saveData();
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (hasConnection(getActivity())) {
                try {
                    String url = "https://rating.chgk.info/players.php?page=1";
                    Document table = Jsoup.connect(url).get();
                    Elements rows = table.select("tr");
                    int size = rows.size();
                    for (int i = 2; i < rows.size(); i++) {
                        Element row = rows.get((i));  // по номеру индекса получаем строку
                        Elements cols = row.select("td"); // разбиваем полученную строку по тегу  на столбы
                        String player_position = cols.get(1).text();
                        String player_name = cols.get(7).text();
                        String player_team = cols.get(9).text();
                        String player_rating = cols.get(3).text();
                        String player_url = cols.get(7).select("a[href]").attr("href");
                        parseItems.add(new ParseItemPlayer(player_position, player_name, player_team, player_rating, player_url));
                        Log.d("items", "pos: " + player_position + " . name: " + player_name + " . team: " + player_team + " . rating" + player_rating);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}
