package com.mts.mtsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.navigation.NavigationView;

/* Controls all pages that want to be opened in a webView */
public class WebOpener extends AppCompatActivity {

    /* Initialize webView */
    private WebView webView;
    private DrawerLayout draw_layout;
    private ActionBarDrawerToggle action_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_opener);

        /* Open url passed into webView */
        String urlLink = getIntent().getStringExtra("url");
        webView = (WebView)findViewById(R.id.my_webview);
        webView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(webIntent);
                    return true;
                } else {
                    return false;
                }
            }

            @RequiresApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                String url = request.getUrl().toString();
                if (url.endsWith(".pdf")) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(webIntent);
                    return true;
                } else {
                    return false;
                }
            }
        });
        WebSettings myWebSettings = webView.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setLoadWithOverviewMode(true);
        myWebSettings.setUseWideViewPort(true);
        myWebSettings.setBuiltInZoomControls(true);
        webView.loadUrl(urlLink);

        draw_layout = (DrawerLayout)findViewById(R.id.drawer_layout_webopener);
        action_toggle = new ActionBarDrawerToggle(this, draw_layout, R.string.Open, R.string.Close);
        action_toggle.setDrawerIndicatorEnabled(true);

        draw_layout.addDrawerListener(action_toggle);
        action_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = (NavigationView)findViewById(R.id.navigationView_webopener);

        /* Each option in side menu */
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

    /* Facebook icon in menu bar */
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
