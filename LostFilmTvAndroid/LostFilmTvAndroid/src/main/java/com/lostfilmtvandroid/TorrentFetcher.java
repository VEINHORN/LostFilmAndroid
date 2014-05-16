package com.lostfilmtvandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.lostfilmtvandroid.torrentrss.EpisodesContainer;
import com.lostfilmtvandroid.torrentrss.TorrentRssFetcher;

/**
 * Created by veinhorn on 11.5.14.
 */
public class TorrentFetcher extends AsyncTask<String, String, EpisodesContainer> {
    private ProgressDialog progressDialog;
    private Context context;
    private String serialTitle;
    private String episodeTitle;

    public TorrentFetcher(Context context, String serialTitle, String episodeTitle) {
        this.context = context;
        this.serialTitle = serialTitle;
        this.episodeTitle = episodeTitle;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Searching", "Searching torrent");
    }

    @Override
    protected EpisodesContainer doInBackground(String... params) {
        return TorrentRssFetcher.getEpisodes(serialTitle);
    }

    @Override
    protected void onPostExecute(EpisodesContainer episodesContainer) {
        progressDialog.hide();
        if(episodesContainer.isSuchEpisode(episodeTitle)) {
            Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
        }
    }
}