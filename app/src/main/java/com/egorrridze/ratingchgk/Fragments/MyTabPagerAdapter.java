package com.egorrridze.ratingchgk.Fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyTabPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Игроки", "Команды"};

    MyTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavPlayersTab();
            case 1:
                return new FavTeamsTab();
            default:
                return null;
        }
    }
}