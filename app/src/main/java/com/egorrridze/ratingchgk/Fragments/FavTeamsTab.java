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
public class FavTeamsTab extends Fragment {


    public FavTeamsTab() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_teams_tab, container, false);
    }

}
