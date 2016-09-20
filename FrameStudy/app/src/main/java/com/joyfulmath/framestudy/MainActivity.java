package com.joyfulmath.framestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joyfulmath.framestudy.network.NetworkRequestSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        func();
    }

    private void func() {
        NetworkRequestSample.startSample();
    }
}
