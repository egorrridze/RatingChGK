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

import com.egorrridze.ratingchgk.Adapters.ParseTeamAdapter;
import com.egorrridze.ratingchgk.Models.ParseItemTeam;
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

public class TeamsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParseTeamAdapter adapter;
    private ArrayList<ParseItemTeam> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_teams, container, false);

        if (!hasConnection(getActivity())) loadData();

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_teams);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ParseTeamAdapter(parseItems, getActivity());
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
            editor.putString("teamsList", json);
            editor.apply();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void loadData(){
        SharedPreferences prefs = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("teamsList", null);
        Type type = new TypeToken<ArrayList<ParseItemTeam>>() {}.getType();
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
            try {
                String url = "https://rating.chgk.info/teams.php?page=1";
                Document table = Jsoup.connect(url).get();
                Elements rows = table.select("tr");
                int size = rows.size();
                for (int i = 2; i < size; i++) {
                    Element row = rows.get((i));  // по номеру индекса получаем строку
                    Elements cols = row.select("td"); // разбиваем полученную строку по тегу  на столбы
                    String team_position = cols.get(1).text();
                    String team_name = cols.get(7).text();
                    String team_city = cols.get(8).text();
                    String team_rating = cols.get(3).text();
                    parseItems.add(new ParseItemTeam(team_position, team_name, team_city, team_rating));
                    Log.d("items", "pos: " + team_position + " . name: " + team_name + " . team: " + team_city + " . rating" + team_rating);
                }
            } catch (IOException e){
                e.printStackTrace();;
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
