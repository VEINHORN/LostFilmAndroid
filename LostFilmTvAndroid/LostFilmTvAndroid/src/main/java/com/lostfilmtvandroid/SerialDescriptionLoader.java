package com.lostfilmtvandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.lostfilmtvandroid.lostfilmtv.entities.SerialDescription;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialDescriptionFetcher;
import com.squareup.picasso.Picasso;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialDescriptionLoader extends AsyncTask<String, Integer, SerialDescription> {
    private SerialDescription serialDescription;
    private String url;

    private Context context;
    private ImageView posterImageView;
    private TextView countryTextView;

    public SerialDescriptionLoader(Context context, ImageView posterImageView, TextView countryTextView, String url) {
        this.context = context;
        this.posterImageView = posterImageView;
        this.countryTextView = countryTextView;
        this.url = url;
    }

    @Override
    protected SerialDescription doInBackground(String... params) {
        return SerialDescriptionFetcher.loadSerialDescription(url);
    }

    @Override
    protected void onPostExecute(SerialDescription serialDescription) {
        this.serialDescription = serialDescription;

        Picasso.with(context).setDebugging(true);
        Picasso.with(context).load(serialDescription.getPosterUrl()).into(posterImageView);
        countryTextView.setText(serialDescription.getCountry());
        //textView.setText(serialDescription.getSeasons().getSeason(4).getEpisode(3).toString());
    }
}