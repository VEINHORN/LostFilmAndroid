package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.dina.ui.widget.UITableView;


public class MainActivity extends ActionBarActivity {
    private SerialsContainer serialsContainer = new SerialsContainer();
    private UITableView uiTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiTableView = (UITableView)findViewById(R.id.uitableview);
        SerialsLoader serialsLoader = new SerialsLoader(serialsContainer, uiTableView);
        serialsLoader.execute();
    }
}