package com.egorrridze.ratingchgk.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.egorrridze.ratingchgk.R;
import com.google.android.material.tabs.TabLayout;

public class FavoritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyTabPagerAdapter tabPager = new MyTabPagerAdapter(getChildFragmentManager());

        ViewPager viewPager = getView().findViewById(R.id.viewpager);
        viewPager.setAdapter(tabPager);

        // Display a tab for each Fragment displayed in ViewPager.
        TabLayout tabLayout = getView().findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}