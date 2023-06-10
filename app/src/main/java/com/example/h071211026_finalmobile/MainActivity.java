package com.example.h071211026_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.h071211026_finalmobile.adapter.MovieAdapter;
import com.example.h071211026_finalmobile.fragment.FavoriteFragment;
import com.example.h071211026_finalmobile.fragment.MovieFragment;
import com.example.h071211026_finalmobile.fragment.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    private LinearLayout filmCard;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setListener();

        fragmentManager = getSupportFragmentManager();
        MovieFragment movieFragment = MovieFragment.getInstance();
        TvFragment tvFragment = TvFragment.getInstance();
        FavoriteFragment favoriteFragment = FavoriteFragment.getInstance();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fl_container, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .commit();
        }
        else if (!(fragment instanceof TvFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fl_container, tvFragment,
                            TvFragment.class.getSimpleName())
                    .commit();
        }
        else if (!(fragment instanceof FavoriteFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fl_container, favoriteFragment,
                            FavoriteFragment.class.getSimpleName())
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
        }
        else if (fragment instanceof TvFragment) {
            transaction
                    .replace(R.id.fl_container, fragment,
                            TvFragment.class.getSimpleName())
                    .commit();
        }
        else if (fragment instanceof FavoriteFragment) {
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

//    public void setListener() {
//        filmCard.findViewById(R.id.filmCard)
//                .setOnClickListener(v -> {
//                    Intent intent = new Intent(this, DetailActivity.class);
//                    startActivity(intent);
//                });
//    }
}