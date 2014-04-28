package com.lostfilmtvandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import br.com.dina.ui.widget.UITableView;


public class MainActivity extends ActionBarActivity {
    private SerialsLoader serialsLoader;
    private UITableView uiTableView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiTableView = (UITableView)findViewById(R.id.uitableview);
        uiTableView.setClickListener(new UITableView.ClickListener() {
            @Override
            public void onClick(int index) {
                Intent intent = new Intent(MainActivity.this, SerialDescriptionActivity.class);
                intent.putExtra("serialUrl", serialsLoader.getSerials().getSerial(index).getPageUrl());
                startActivity(intent);
            }
        });

        serialsLoader = new SerialsLoader(uiTableView);
        serialsLoader.execute();
    }
}