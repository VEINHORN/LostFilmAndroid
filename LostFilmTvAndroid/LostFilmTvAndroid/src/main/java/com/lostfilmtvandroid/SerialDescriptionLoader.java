package com.lostfilmtvandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lostfilmtvandroid.lostfilmtv.entities.Episode;
import com.lostfilmtvandroid.lostfilmtv.entities.Season;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialDescription;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialDescriptionFetcher;
import com.squareup.picasso.Picasso;

import br.com.dina.ui.widget.UITableView;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialDescriptionLoader extends AsyncTask<String, Integer, SerialDescription> {
    private SerialDescription serialDescription;
    private String url;

    private Activity activity;
    private ImageView posterImageView;
    private TextView countryTextView;
    private TextView yearTextView;
    private TextView genreTextView;
    private TextView numberOfSeasonsTextView;
    private TextView statusTextView;
    private TextView serialDescriptionTextView;

    public SerialDescriptionLoader(Activity activity, ImageView posterImageView, TextView countryTextView,
                                   TextView yearTextView, TextView genreTextView, TextView numberOfSeasonsTextView,
                                   TextView statusTextView, TextView serialDescriptionTextView, String url) {
        this.activity = activity;
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

        Picasso.with(activity).load(serialDescription.getPosterUrl()).into(posterImageView);
        countryTextView.setText(activity.getString(R.string.serial_description_country) + serialDescription.getCountry());
        yearTextView.setText(activity.getString(R.string.serial_description_year) + serialDescription.getYear());
        genreTextView.setText(activity.getString(R.string.serial_description_genre) + serialDescription.getGenres());
        numberOfSeasonsTextView.setText(activity.getString(R.string.serial_description_seasons) + serialDescription.getNumberOfSeasons());
        statusTextView.setText(activity.getString(R.string.serial_description_status) + serialDescription.getStatus());
        serialDescriptionTextView.setText(serialDescription.getDescription());

        for(Season season : serialDescription.getSeasons()) {
            LinearLayout serialDescriptionLayout = (LinearLayout)activity.findViewById(R.id.description_layout);
            View seasonView = LayoutInflater.from(activity).inflate(R.layout.season_view, null);
            TextView seasonTitleTextView = (TextView)seasonView.findViewById(R.id.season_title);
            seasonTitleTextView.setText(season.getSeasonNumber().toString() + " season");
            final UITableView episodesTable = (UITableView)seasonView.findViewById(R.id.episodes_table);
            for(Episode episode : season) {
                episodesTable.addBasicItem(episode.getTitle(), episode.getOriginalTitle());
            }
            episodesTable.commit();
            episodesTable.setClickListener(new UITableView.ClickListener() {
                @Override
                public void onClick(int index) {
                    Toast.makeText(activity, episodesTable.getBasicItem(index).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            serialDescriptionLayout.addView(seasonView);
        }
    }
}