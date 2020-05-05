package com.egorrridze.ratingchgk.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egorrridze.ratingchgk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavPlayersTab extends Fragment {


    public FavPlayersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_players_tab, container, false);
    }

}
