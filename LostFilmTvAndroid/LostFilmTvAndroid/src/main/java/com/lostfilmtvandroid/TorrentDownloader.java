package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 17.5.14.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qustom.dialog.QustomDialogBuilder;

import org.bitlet.wetorrent.Metafile;
import org.bitlet.wetorrent.Torrent;
import org.bitlet.wetorrent.disk.PlainFileSystemTorrentDisk;
import org.bitlet.wetorrent.disk.TorrentDisk;
import org.bitlet.wetorrent.peer.IncomingPeerListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by veinhorn on 3.5.14.
 */
public class TorrentDownloader extends AsyncTask<String, String, String> {
    private Activity activity;
    private File[] files;
    private QustomDialogBuilder qustomDialogBuilder;
    @InjectView(R.id.downloaded_text_view) TextView downloadedTextView;
    @InjectView(R.id.seeders_text_view) TextView seedersTextView;
    @InjectView(R.id.peers_text_view) TextView peersTextView;

    public TorrentDownloader(Activity activity, File[] files) {
        this.activity = activity;
        this.files = files;
    }

    @Override protected void onPreExecute() {
        qustomDialogBuilder = new QustomDialogBuilder(activity)
                .setTitle("Downloading")
                .setCustomView(R.layout.downloading_dialog, activity);
        View dialogView = qustomDialogBuilder.getDialogView();
        ButterKnife.inject(this, dialogView);
        qustomDialogBuilder.show();
    }

    @Override protected void onProgressUpdate(String... vars) {
        downloadedTextView.setText("Downloaded: " + vars[0] + "/" + vars[1]);
        peersTextView.setText("Peers: " + vars[2]);
        seedersTextView.setText("Seeds: " + vars[3]);
    }

    @Override protected String doInBackground(String... params) {
        try {
            //File torrentFile = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/gameofthrones.torrent");
            Metafile metafile = new Metafile(new BufferedInputStream(new FileInputStream(files[1])));
            TorrentDisk torrentDisk = new PlainFileSystemTorrentDisk(metafile, files[0]);
            torrentDisk.init();
            IncomingPeerListener peerListener = new IncomingPeerListener(3300);
            peerListener.start();

            Torrent torrent = new Torrent(metafile, torrentDisk, peerListener);
            torrent.startDownload();

            while (!torrent.isCompleted()) {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    Log.e(TorrentDownloader.class.getName(), e.getMessage(), e);
                    break;
                }
                torrent.tick();

                publishProgress(Long.toString(torrent.getPeersManager().getDownloaded()), Long.toString(metafile.getLength()),
                        Integer.toString(torrent.getPeersManager().getActivePeersNumber()), Integer.toString(torrent.getPeersManager().getSeedersNumber()));
                //downloadedString = Long.toString(torrent.getPeersManager().getDownloaded());
            }
        } catch(FileNotFoundException e) {
            Log.e(TorrentDownloader.class.getName(), e.getMessage(), e);
        } catch(NoSuchAlgorithmException e) {
            Log.e(TorrentDownloader.class.getName(), e.getMessage(), e);
        } catch(IOException e) {
            Log.e(TorrentDownloader.class.getName(), e.getMessage(), e);
        }
        return "";
    }


    @Override protected void onPostExecute(String str) {
        //qustomDialogBuilder.getDialo
    }
}