package com.lostfilmtvandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialDescriptionActivity extends ActionBarActivity {
    private SerialDescriptionLoader serialDescriptionLoader;
    private ImageView posterImageView;
    private TextView countryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_description);
        String serialUrl = getIntent().getStringExtra("serialUrl");
        posterImageView = (ImageView)findViewById(R.id.poster_image_view);
        countryTextView = (TextView)findViewById(R.id.country_text_view);

        serialDescriptionLoader = new SerialDescriptionLoader(this, posterImageView, countryTextView, serialUrl);
        serialDescriptionLoader.execute();
    }
}
