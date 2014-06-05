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
            final String[] torrents = episodesContainer.searchTorrentLinks(episodeTitle);

            QustomDialogBuilder qustomDialogBuilder = new QustomDialogBuilder(activity)
                    .setTitle("Download torrent")
                    .setMessage("Select the torrent quality and start downloading")
                    .setCustomView(R.layout.torrent_buttons, activity);
            qustomDialogBuilder.show();
            TextView hqTorrentButton = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.hq_torrent_button);
            TextView torrentButton = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.torrent_button);

            torrentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, torrents[0], Toast.LENGTH_SHORT).show();
                }
            });

            // Checking for hq torrent
            if(!torrents[1].equals("")) {
                hqTorrentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, torrents[1], Toast.LENGTH_SHORT).show();
                        TorrentFileDownloader downloader = new TorrentFileDownloader(activity, torrents[1], episodeTitle);
                        downloader.execute();
                        //Log.d("My Path", Environment.getExternalStorageDirectory().getAbsolutePath());
                    }
                });
            } else {
                hqTorrentButton.setBackgroundResource(R.drawable.ps__button_github);
            }
        } else {
            Toast.makeText(activity, "No such episode", Toast.LENGTH_SHORT).show();
        }
    }
}