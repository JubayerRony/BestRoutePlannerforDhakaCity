package com.test.bestrouteplannerfordhakacity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "GOTHIC.TTF");
        TextView textView;
        typeFace = Typeface.createFromAsset(getAssets(), "COMIC.ttf");
        textView = (TextView) findViewById(R.id.helpText);
        textView.setTypeface(typeFace);
        textView.setText("Welcome to Best Route Planner for Dhaka City. This Section will guide you on how to use this app.\n\n"+
        "1. First set your source location. It means the place you would like to start from.\n\n"+
        "2. Then select your destined location. It is the place you want to go. \n\n"+
        "3. Our interactive drop-down menu will help you to select your desired bus stop available across Dhaka city.\n\n"+
        "4. Our sorting option will let you to sort the result depending on 3 different factors. \n"+
            "a. Cost \n"+
            "b. Distance \n"+
            "c. Time \n\n"+

        "You will be able to find the best route to reach your destination by this app and save your money and time. Thanks for downloading. Have a good day.");


    }
}
