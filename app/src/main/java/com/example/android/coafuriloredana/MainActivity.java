package com.example.android.coafuriloredana;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/CoafuriLoredana";
    public static String FACEBOOK_PAGE_ID = "CoafuriLoredana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method to get the right URL to use in the facebook intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    //method used to visit facebook page of the business
    public void lunchFacebook(View v) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    //method used to find place on GoogleMaps
    public void navigatemaps(View v) {
        Uri mapAddress = Uri.parse("geo:46.835612, 27.458324");
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, mapAddress);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}