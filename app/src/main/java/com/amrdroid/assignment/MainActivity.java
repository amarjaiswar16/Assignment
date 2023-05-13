package com.amrdroid.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bnView);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        loadFragment(new NotesFragment());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
           /*     if(id == R.id.notes_item) {

                    loadFragment(new NotesFragment());
                }else {

                    loadFragment(new SettingsFragment());
                }*/
                if(id == R.id.settings_item) {
                    loadFragment(new SettingsFragment());
                } else {
                    loadFragment(new NotesFragment());
                }

                navigationView.getMenu().getItem(0).setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.settings_item) {
                    loadFragment(new SettingsFragment());
                } else {
                    loadFragment(new NotesFragment());
                }


                return true;
            }
        });


    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container,fragment);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notesrv_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_item:
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_item:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;


                
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}