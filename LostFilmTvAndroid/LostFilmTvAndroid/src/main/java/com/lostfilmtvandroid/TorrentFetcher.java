package com.lostfilmtvandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lostfilmtvandroid.torrentrss.EpisodesContainer;
import com.lostfilmtvandroid.torrentrss.TorrentRssFetcher;
import com.qustom.dialog.QustomDialogBuilder;

/**
 * Created by veinhorn on 11.5.14.
 */
public class TorrentFetcher extends AsyncTask<String, String, EpisodesContainer> {
    private ProgressDialog progressDialog;
    private Activity activity;
    private String serialTitle;
    private String episodeTitle;

    public TorrentFetcher(Activity activity, String serialTitle, String episodeTitle) {
        this.activity = activity;
        this.serialTitle = serialTitle;
        this.episodeTitle = episodeTitle;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "Searching", "Searching torrent");
    }

    @Override
    protected EpisodesContainer doInBackground(String... params) {
        return TorrentRssFetcher.getEpisodes(serialTitle);
    }

    @Override
    protected void onPostExecute(EpisodesContainer episodesContainer) {
        progressDialog.hide();
        if(episodesContainer.isSuchEpisode(episodeTitle)) {
            QustomDialogBuilder qustomDialogBuilder = new QustomDialogBuilder(activity)
                    .setTitle("Download torrent")
                    .setMessage("Select the torrent quality and start downloading")
                    .setCustomView(R.layout.torrent_buttons, activity)
                    .setTitleColor("#FF00FF")
                    .setDividerColor("#FF00FF");
            qustomDialogBuilder.show();
            TextView hqTorrentButton = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.hq_torrent_button);
            hqTorrentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "Text", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "false", Toast.LENGTH_SHORT).show();
        }
    }
}