package com.mts.mtsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

/* Class for the home page of the app */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button calendarButton;
    private Button subscribeButton;
    private Button timingsButton;
    private Button directionsButton;
    private Button websiteButton;
    private Button dressCodeButon;
    private TextView title;
    private ViewFlipper viewFlipper;

    private DrawerLayout draw_layout;
    private ActionBarDrawerToggle action_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draw_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        action_toggle = new ActionBarDrawerToggle(this, draw_layout, R.string.Open, R.string.Close);
        action_toggle.setDrawerIndicatorEnabled(true);

        draw_layout.addDrawerListener(action_toggle);
        action_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = (NavigationView)findViewById(R.id.navigationView);

        /* Different actions based on which menu option is clicked */
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


        /* Initialize buttons on the home page */
        calendarButton = (Button)findViewById(R.id.calendarButton);
        subscribeButton = (Button)findViewById(R.id.subscribeButton);
        timingsButton = (Button)findViewById(R.id.timingsButton);
        directionsButton = (Button)findViewById(R.id.directionsButton);
        websiteButton = (Button)findViewById(R.id.websiteButton);
        dressCodeButon = (Button)findViewById(R.id.dressCodeButton);
        title = (TextView)findViewById(R.id.homePageTitle);
        viewFlipper = (ViewFlipper)findViewById(R.id.v_flip);

        calendarButton.setOnClickListener(this);
        subscribeButton.setOnClickListener(this);
        timingsButton.setOnClickListener(this);
        directionsButton.setOnClickListener(this);
        websiteButton.setOnClickListener(this);
        dressCodeButon.setOnClickListener(this);

        /* Slideshow of pictures on home page */
        int homeImages[] = {R.drawable.mts1, R.drawable.mts2, R.drawable.mts4, R.drawable.mts5};

        for (int i = 0; i < homeImages.length; i++){
            flipperSlides(homeImages[i]);
        }
    }

    /* What to do for all buttons on home page */
    public void onClick(View v) {
        if(v == calendarButton){
            calendarClick();
        }
        if(v == subscribeButton){
            subscribeClick();
        }
        if(v == timingsButton){
            timingsClick();
        }
        if(v == directionsButton){
            directionsClick();
        }
        if(v == websiteButton){
            websiteClick();
        }
        if(v == dressCodeButon){
            dressCodeClick();
        }
    }

    /* Calendar button */
    private void calendarClick(){
        Intent webActivity =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1lwJeaCvc10q2Q2FyhZA-Ad0zG2sI624S/view?usp=sharing"));
        startActivity(webActivity);
    }

    /* Subscribe button */
    private void subscribeClick(){
        Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
        webIntent.putExtra("url","https://visitor.r20.constantcontact.com/manage/optin?v=001HTk7N6nZumLUO68TJZop-2uyIpg_ZalrSLfSdmbM7JhcJ6FDcCjvzpLsTQJJ6LT1TALA0XgVOXnxWCLaDFj7TOHQqWGyG54oUHaFtal10D0%3D");
        startActivity(webIntent);
    }

    /* Temple timings button */
    private void timingsClick(){
        Intent nextIntent = new Intent(this, Timings.class);
        startActivity(nextIntent);
    }

    /* Temple directions button */
    private void directionsClick (){
        if (googleMapsInstalled()) {
            Uri mapsIntentUri = Uri.parse("geo:0,0?q=Sri+Meenakshi+Devasthanam,+Pearland+Texas");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        else{
            Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
            webIntent.putExtra("url","https://www.google.com/maps/place/Sri+Meenakshi+Devasthanam/@29.5227511,-95.2998147,17z/data=!3m1!4b1!4m5!3m4!1s0x86409192d9172f81:0x6c7c6b470f074b4f!8m2!3d29.5227511!4d-95.297626");
            startActivity(webIntent);
        }
    }

    private boolean googleMapsInstalled(){
        try{
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo("com.google.android.apps.maps",0);
            return true;
        } catch (PackageManager.NameNotFoundException exception){
            return false;
        }
    }

    /* Temple website button */
    private void websiteClick(){
        Intent webIntent = new Intent(getApplicationContext(), WebOpener.class);
        webIntent.putExtra("url","https://www.emeenakshi.org");
        startActivity(webIntent);
    }

    /* Dress code button */
    private void dressCodeClick(){
        Intent nextIntent = new Intent(this, DressCode.class);
        startActivity(nextIntent);
    }

    /* Method for slideshow of images */
    public void flipperSlides(int img){
        ImageView image = new ImageView(this);
        image.setBackgroundResource(img);

        viewFlipper.addView(image);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
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

}
