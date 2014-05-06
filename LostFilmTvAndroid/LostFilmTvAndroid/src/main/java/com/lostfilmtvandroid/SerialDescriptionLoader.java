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
    private TextView yearTextView;
    private TextView genreTextView;
    private TextView numberOfSeasonsTextView;
    private TextView statusTextView;
    private TextView serialDescriptionTextView;

    public SerialDescriptionLoader(Context context, ImageView posterImageView, TextView countryTextView,
                                   TextView yearTextView, TextView genreTextView, TextView numberOfSeasonsTextView,
                                   TextView statusTextView, TextView serialDescriptionTextView, String url) {
        this.context = context;
        this.posterImageView = posterImageView;
        this.countryTextView = countryTextView;
        this.yearTextView = yearTextView;
        this.genreTextView = genreTextView;
        this.numberOfSeasonsTextView = numberOfSeasonsTextView;
        this.statusTextView = statusTextView;
        this.serialDescriptionTextView = serialDescriptionTextView;

        this.url = url;
    }

    @Override
    protected SerialDescription doInBackground(String... params) {
        return SerialDescriptionFetcher.loadSerialDescription(url);
    }

    @Override
    protected void onPostExecute(SerialDescription description) {
        this.serialDescription = description;

        Picasso.with(context).load(serialDescription.getPosterUrl()).into(posterImageView);
        countryTextView.setText(context.getString(R.string.serial_description_country) + serialDescription.getCountry());
        yearTextView.setText(context.getString(R.string.serial_description_year) + serialDescription.getYear());
        genreTextView.setText(context.getString(R.string.serial_description_genre) + serialDescription.getGenres());
        numberOfSeasonsTextView.setText(context.getString(R.string.serial_description_seasons) + serialDescription.getNumberOfSeasons());
        statusTextView.setText(context.getString(R.string.serial_description_status) + serialDescription.getStatus());
        serialDescriptionTextView.setText(serialDescription.getDescription());
    }
}