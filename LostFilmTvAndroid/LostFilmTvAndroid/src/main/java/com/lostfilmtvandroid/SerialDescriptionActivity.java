package com.lostfilmtvandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialDescriptionActivity extends ActionBarActivity {
    private SerialDescriptionLoader serialDescriptionLoader;
    private ImageView posterImageView;
    private TextView countryTextView;
    private TextView yearTextView;
    private TextView genreTextView;
    private TextView numberOfSeasonsTextView;
    private TextView statusTextView;
    private TextView serialDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/Roboto-Light.ttf");
        setContentView(R.layout.activity_serial_description);

        String serialUrl = getIntent().getStringExtra("serialUrl");
        posterImageView = (ImageView)findViewById(R.id.poster_image_view);
        countryTextView = (TextView)findViewById(R.id.country_text_view);
        yearTextView = (TextView)findViewById(R.id.year_text_view);
        genreTextView = (TextView)findViewById(R.id.genre_text_view);
        numberOfSeasonsTextView = (TextView)findViewById(R.id.number_of_seasons_text_view);
        statusTextView = (TextView)findViewById(R.id.status_text_view);
        serialDescriptionTextView = (TextView)findViewById(R.id.description_text_view);

        serialDescriptionLoader = new SerialDescriptionLoader(this, posterImageView, countryTextView, yearTextView, genreTextView, numberOfSeasonsTextView, statusTextView, serialDescriptionTextView, serialUrl);
        serialDescriptionLoader.execute();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(new CalligraphyContextWrapper(context, R.attr.fontPath));
    }
}
