package com.example.h071211026_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.h071211026_finalmobile.adapter.MovieAdapter;
import com.example.h071211026_finalmobile.fragment.FavoriteFragment;
import com.example.h071211026_finalmobile.fragment.MovieFragment;
import com.example.h071211026_finalmobile.fragment.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        MovieFragment movieFragment = MovieFragment.getInstance();
        Fragment fragment =
                fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());
        if (!(fragment instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fl_container, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.item_movie) {
                switchFragment(MovieFragment.getInstance());
            }
            else if  (item.getItemId() == R.id.item_tv){
                switchFragment(TvFragment.getInstance());
            }
            else if (item.getItemId() == R.id.item_fav) {
                switchFragment(FavoriteFragment.getInstance());
            }
            return true;
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragment instanceof MovieFragment) {
            transaction
                    .replace(R.id.fl_container, fragment,
                            MovieFragment.class.getSimpleName())
                    .commit();
        } else {
            transaction
                    .replace(R.id.fl_container, fragment,
                            MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }
}