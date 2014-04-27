package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    //private SerialsContainer serialsContainer = new SerialsContainer();
    //private UITableView uiTableView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = (TextView)findViewById(R.id.textview);
        SerialDescriptionLoader serialDescriptionLoader = new SerialDescriptionLoader(textView, "http://www.lostfilm.tv/browse.php?cat=145");
        serialDescriptionLoader.execute();
        /*
        uiTableView = (UITableView)findViewById(R.id.uitableview);
        uiTableView.setClickListener(new UITableView.ClickListener() {
            @Override
            public void onClick(int index) {
                Toast.makeText(MainActivity.this, serialsContainer.getSerial(index).getPageUrl(), Toast.LENGTH_SHORT).show();
            }
        });
        SerialItemsLoader serialsLoader = new SerialItemsLoader(serialsContainer, uiTableView);
        serialsLoader.execute();
        */
    }
}