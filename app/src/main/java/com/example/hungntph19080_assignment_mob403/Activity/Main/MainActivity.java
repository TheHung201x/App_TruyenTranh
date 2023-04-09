package com.example.hungntph19080_assignment_mob403.Activity.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Favorite.FavoriteFragment;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home.HomePageFragment;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Profile.ProfileFragment;
import com.example.hungntph19080_assignment_mob403.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
//    ViewPager2 pager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        pager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNaviagation);
        replaceFragment(new HomePageFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.trangchu: replaceFragment(new HomePageFragment());
                        break;
                    case R.id.yeuthich: replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.canhan: replaceFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}