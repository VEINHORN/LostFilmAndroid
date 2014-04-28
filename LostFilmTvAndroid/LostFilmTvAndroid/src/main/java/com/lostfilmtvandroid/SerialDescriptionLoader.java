package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.widget.TextView;

import com.lostfilmtvandroid.lostfilmtv.entities.SerialDescription;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialDescriptionFetcher;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialDescriptionLoader extends AsyncTask<String, Integer, SerialDescription> {

    private TextView textView;
    private String url;

    public SerialDescriptionLoader(TextView textView, String url) {
        this.textView = textView;
        this.url = url;
    }

    @Override
    protected SerialDescription doInBackground(String... params) {
        SerialDescription serialItemDescription = SerialDescriptionFetcher.loadSerialDescription(url);
        return serialItemDescription;
    }

    @Override
    protected void onPostExecute(SerialDescription serialDescription) {
        textView.setText(serialDescription.getSeasons().getSeason(4).getEpisode(3).toString());
    }
}