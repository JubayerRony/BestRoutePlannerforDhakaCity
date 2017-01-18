package com.test.bestrouteplannerfordhakacity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClickFindWay(View view){
        Intent i = new Intent(Home.this, Input.class);
        startActivity(i);
    }

    public void onClickHelp(View view){
        Intent i = new Intent(Home.this, Help.class);
        startActivity(i);
    }

    public void onClickAbout(View view){
        Intent i = new Intent(Home.this, About.class);
        startActivity(i);
    }

}
