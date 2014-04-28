package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import br.com.dina.ui.widget.UITableView;


public class MainActivity extends ActionBarActivity {
    /*
    private SerialsLoader serialsLoader;
    private ListView serialsListView;
    private SerialsAdapter serialsAdapter;
    private TextView testTextView;
    */
    private UITableView uiTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        uiTableView = (UITableView)findViewById(R.id.tableView);

        uiTableView.addBasicItem("1", "Des 1");
        uiTableView.addBasicItem("2", "Des 2");
        uiTableView.addBasicItem("3", "Des 3");
        uiTableView.commit();
        uiTableView.removeBasicItem(2);
        uiTableView.removeBasicItem(0);
        uiTableView.setClickListener(new UITableView.ClickListener() {
            @Override
            public void onClick(int index) {
                if(index == 0) {
                    Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*
        testTextView = (TextView)findViewById(R.id.testtextview);

        serialsListView = (ListView)findViewById(R.id.serials_list_view);
        serialsAdapter = new SerialsAdapter(this, new SerialsContainer());
        serialsListView.setAdapter(serialsAdapter);

        serialsLoader = new SerialsLoader(testTextView, serialsAdapter);
        serialsLoader.execute();
        */
    }
}