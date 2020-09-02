package com.mts.mtsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class VHS extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout draw_layout;
    private ActionBarDrawerToggle action_toggle;
    private Button vhsWebsiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vhs);

        /* Initialize buttons on this page */
        vhsWebsiteButton = (Button)findViewById(R.id.VHSButton);

        vhsWebsiteButton.setOnClickListener(this);

        draw_layout = (DrawerLayout)findViewById(R.id.drawer_layout_vhs);
        action_toggle = new ActionBarDrawerToggle(this, draw_layout, R.string.Open, R.string.Close);
        action_toggle.setDrawerIndicatorEnabled(true);

        draw_layout.addDrawerListener(action_toggle);
        action_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = (NavigationView)findViewById(R.id.navigationView_vhs);

        /* Actions for different menu items */
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemID = menuItem.getItemId();
                if(itemID == R.id.homeTab){
                    Intent nextIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(nextIntent);
                }
                if (itemID == R.id.pooja_forms){
                    Intent nextIntent = new Intent(getApplicationContext(), Pooja.class);
                    startActivity(nextIntent);
                }
                if(itemID == R.id.vhs){
                    Intent nextIntent = new Intent(getApplicationContext(), VHS.class);
                    startActivity(nextIntent);
                }
                if(itemID == R.id.volunteer){
                    Intent nextIntent = new Intent(getApplicationContext(), Volunteer.class);
                    startActivity(nextIntent);
                }
                if(itemID == R.id.rentals){
                    Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
                    webIntent.putExtra("url","https://facility.emeenakshi.org/swesti/home/index.jsp");
                    startActivity(webIntent);
                }
                return true;
            }
        });
    }

    /* Facebook icon */
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int itemID = item.getItemId();
        if(itemID == R.id.fbIcon){
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://www.facebook.com/SriMeenakshiTemple.Pearland");
            startActivity(webIntent);
        }
        return action_toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.actionbar_menu, m);
        return true;
    }

    /* Actions for buttons on VHS page */
    public void onClick(View v) {
        if (v == vhsWebsiteButton) {
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://mtsvhs.blogspot.com/");
            startActivity(webIntent);
        }
    }
}