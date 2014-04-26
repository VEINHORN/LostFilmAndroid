package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    SerialsContainer serialsContainer = new SerialsContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textview);
        SerialsLoader serialsLoader = new SerialsLoader(textView, serialsContainer);
        serialsLoader.execute();
    }
}