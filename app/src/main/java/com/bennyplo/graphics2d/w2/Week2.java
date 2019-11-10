package com.bennyplo.graphics2d.w2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Week2 extends AppCompatActivity {
    private MyView mMyView = null;//a custom view for drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//hide the title bar
        mMyView = new MyView(this);
        setContentView(mMyView);
    }
}
