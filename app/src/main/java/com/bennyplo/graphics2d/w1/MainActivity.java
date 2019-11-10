package com.bennyplo.graphics2d.w1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private View mMyView = null;//a custom view for drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //replace the view with my custom designed view
        FrameLayout frameLayout = new FrameLayout(this);

        /*mMyView = new Quiz1(this);
        frameLayout.addView(mMyView);*/

        mMyView = new QuizzPloygon(this);
        frameLayout.addView(mMyView);

        mMyView = new QuizzAffine(this);
        frameLayout.addView(mMyView);

        setContentView(frameLayout);

    }

}
