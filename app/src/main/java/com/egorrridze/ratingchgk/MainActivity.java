package com.egorrridze.ratingchgk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.egorrridze.ratingchgk.Fragments.FavoritesFragment;
import com.egorrridze.ratingchgk.Fragments.PlayersFragment;
import com.egorrridze.ratingchgk.Fragments.TeamsFragment;
import com.egorrridze.ratingchgk.Fragments.TournamentsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navigationListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlayersFragment()).commit(); // установка дефолтного фрагмента
    }


    // функция обработки нажатий на элементы bottom_nav и вызов соответствующих фрагментов
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_players:
                            selectedFragment = new PlayersFragment();
                            break;
                        case R.id.nav_teams:
                            selectedFragment = new TeamsFragment();
                            break;
                        case R.id.nav_tournaments:
                            selectedFragment = new TournamentsFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_more:
                            PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.nav_more));
                            MenuInflater inflater = popup.getMenuInflater();
                            inflater.inflate(R.menu.more_popup, popup.getMenu());
                            popup.show();
                            break;
                    }
                    try {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    }
                    catch (NullPointerException e){}
                    return true;
                }
            };
}
