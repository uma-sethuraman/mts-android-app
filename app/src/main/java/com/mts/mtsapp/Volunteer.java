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

public class Volunteer extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout draw_layout;
    private ActionBarDrawerToggle action_toggle;
    private Button volunteerButton;
    private Button whatsAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        /* Initialize buttons on volunteer registration page */
        volunteerButton = (Button)findViewById(R.id.VolunteerButton);

        whatsAppButton = (Button)findViewById(R.id.whatsAppButton);

        volunteerButton.setOnClickListener(this);
        whatsAppButton.setOnClickListener(this);

        draw_layout = (DrawerLayout)findViewById(R.id.drawer_layout_volunteer);
        action_toggle = new ActionBarDrawerToggle(this, draw_layout, R.string.Open, R.string.Close);
        action_toggle.setDrawerIndicatorEnabled(true);

        draw_layout.addDrawerListener(action_toggle);
        action_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = (NavigationView)findViewById(R.id.navigationView_volunteer);

        /* Actions for different menu buttons */
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

    /* Actions for buttons on volunteer registration page */
    public void onClick(View v) {
        if (v == volunteerButton) {
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://docs.google.com/forms/d/1J1RIoSJgQXIKAxc_kqcml8g1xp4UqBN3ZbnoUnu4KdY/edit?usp=sharing");
            startActivity(webIntent);
        }
        if(v == whatsAppButton){
            Intent webActivity =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.whatsapp.com/FlLRimd4NwQE50QHRMmpcI"));
            startActivity(webActivity);
        }
    }
}
