package com.egorrridze.ratingchgk.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
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

    static class MyTabPagerAdapter extends FragmentPagerAdapter {
        MyTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new FavPlayersTab();
                case 1:
                    return new FavTeamsTab();
                default:
                    return null;
            }
        }
    }
}