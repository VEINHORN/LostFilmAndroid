package com.lostfilmtvandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.lostfilmtvandroid.serialslist.SerialsAdapter;
import com.lostfilmtvandroid.serialslist.SerialsLoader;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends ActionBarActivity {
    private SerialsLoader serialsLoader;
    private GridView gridView;
    private SerialsAdapter serialsAdapter;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/Roboto-Light.ttf");
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View view = inflator.inflate(R.layout.titleview, null);
        actionBar.setCustomView(view);

        LostFilmFromXML lostFilmFromXML = new LostFilmFromXML();

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                serialsAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gridView = (GridView)findViewById(R.id.gridview);
        serialsAdapter = new SerialsAdapter(this, new SerialsContainer(), gridView);
        gridView.setAdapter(serialsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SerialDescriptionActivity.class);
                intent.putExtra("serialUrl", serialsAdapter.getSerialsContainer().getSerial(position).getPageUrl());
                startActivity(intent);
            }
        });
        serialsLoader = new SerialsLoader(this, serialsAdapter);
        serialsLoader.execute();

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(new CalligraphyContextWrapper(context, R.attr.fontPath));
    }
}