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

public class Pooja extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout draw_layout;
    private ActionBarDrawerToggle action_toggle;
    private Button poojaForms;
    private Button poojaMaterials;
    private Button poojaEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja);

        /* Initialize buttons on home page */
        poojaForms = (Button)findViewById(R.id.poojaFormButton);
        poojaMaterials = (Button)findViewById(R.id.poojaMaterialsButton);
        poojaEvents = (Button)findViewById(R.id.poojaEventsButton);

        poojaForms.setOnClickListener(this);
        poojaMaterials.setOnClickListener(this);
        poojaEvents.setOnClickListener(this);

        draw_layout = (DrawerLayout)findViewById(R.id.drawer_layout_pooja);
        action_toggle = new ActionBarDrawerToggle(this, draw_layout, R.string.Open, R.string.Close);
        action_toggle.setDrawerIndicatorEnabled(true);

        draw_layout.addDrawerListener(action_toggle);
        action_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = (NavigationView)findViewById(R.id.navigationView_pooja);

        /* Actions for each menu option */
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

    /* Each button on pooja page */
    public void onClick(View v){
        if (v == poojaEvents){
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://www.emeenakshi.org");
            startActivity(webIntent);
        }
        if (v == poojaForms) {
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://docs.google.com/forms/d/e/1FAIpQLScUfOkihxK0VoLzu6DaOlk_GZZo7vCKYXcbyiwoRqsqooV5og/viewform");
            startActivity(webIntent);
        }
        if (v == poojaMaterials) {
            Intent webActivity =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emeenakshi.org/Database/Forms/Pooja%20Items%20v2.pdf"));
            startActivity(webActivity);
        }
    }
}
