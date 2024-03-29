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

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.gridview) GridView gridView;
    @InjectView(R.id.search_edit_text) EditText searchEditText;
    private SerialsAdapter serialsAdapter;
    private SerialsLoader serialsLoader;
    private LostFilmFromXML lostFilmFromXML = new LostFilmFromXML();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/Roboto-Light.ttf");
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.main_activity_action_bar_view, null);
        actionBar.setCustomView(view);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                serialsAdapter.getFilter().filter(s);
            }

            @Override public void afterTextChanged(Editable s) { }
        });

        serialsAdapter = new SerialsAdapter(this, new SerialsContainer(), gridView);
        gridView.setAdapter(serialsAdapter);
        serialsLoader = new SerialsLoader(this, serialsAdapter);
        serialsLoader.execute();
    }

    @OnItemClick(R.id.gridview) public void onClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, SerialDescriptionActivity.class);
        intent.putExtra("serialUrl", serialsAdapter.getSerialsContainer().getSerial(position).getPageUrl());
        if(Locale.getDefault().getLanguage().equals("ru")) {
            intent.putExtra("serialTitle", serialsAdapter.getSerialsContainer().getSerial(position).getTitle());
        } else {
            intent.putExtra("serialTitle", serialsAdapter.getSerialsContainer().getSerial(position).getOriginalTitle());
        }
        startActivity(intent);
    }

    @Override protected void attachBaseContext(Context context) {
        super.attachBaseContext(new CalligraphyContextWrapper(context, R.attr.fontPath));
    }
}