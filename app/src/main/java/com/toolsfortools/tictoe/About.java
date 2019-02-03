package com.toolsfortools.tictoe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.toolsfortools.tictoe.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void sendMail(View view) {




        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","toolsfortools1907@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void playStoreDirect(View view) {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Toolsfortools "));
        startActivity(i);
    }

    public void facebookopen(View view) {
        String YourPageURL = "https://www.facebook.com/profile.php?id=100008778120059";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

        startActivity(browserIntent);
    }
}
