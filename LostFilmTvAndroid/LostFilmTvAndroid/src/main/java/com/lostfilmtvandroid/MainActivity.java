package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(MainActivity.this, serialsLoader.getSerials().getSerial(index).getPageUrl(), Toast.LENGTH_SHORT).show();
            }
        });

        serialsLoader = new SerialsLoader(uiTableView);
        serialsLoader.execute();
    }
}