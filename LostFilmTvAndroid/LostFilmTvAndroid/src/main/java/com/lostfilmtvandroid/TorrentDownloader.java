package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 17.5.14.
 */

import android.app.Activity;
import android.os.AsyncTask;
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


/**
 * Created by veinhorn on 3.5.14.
 */
public class TorrentDownloader extends AsyncTask<String, String, String> {
    private Activity activity;
    private File[] files;
    private QustomDialogBuilder qustomDialogBuilder;
    private TextView downloadedTextView;
    private TextView seedersTextView;
    private TextView peersTextView;

    public TorrentDownloader(Activity activity, File[] files) {
        this.activity = activity;
        this.files = files;
    }

    @Override
    protected void onPreExecute() {
        qustomDialogBuilder = new QustomDialogBuilder(activity)
                .setTitle("Downloading")
                .setCustomView(R.layout.downloading_dialog, activity);
        downloadedTextView = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.downloaded_text_view);
        seedersTextView = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.seeders_text_view);
        peersTextView = (TextView)qustomDialogBuilder.getDialogView().findViewById(R.id.peers_text_view);
        qustomDialogBuilder.show();
    }

    @Override
    protected void onProgressUpdate(String... vars) {
        downloadedTextView.setText("Downloaded: " + vars[0] + "/" + vars[1]);
        peersTextView.setText("Peers: " + vars[2]);
        seedersTextView.setText("Seeds: " + vars[3]);
    }

    @Override
    protected String doInBackground(String... params) {
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
                } catch(InterruptedException ie) {
                    break;
                }
                torrent.tick();

                publishProgress(Long.toString(torrent.getPeersManager().getDownloaded()), Long.toString(metafile.getLength()),
                        Integer.toString(torrent.getPeersManager().getActivePeersNumber()), Integer.toString(torrent.getPeersManager().getSeedersNumber()));
                //downloadedString = Long.toString(torrent.getPeersManager().getDownloaded());
            }
        } catch(FileNotFoundException e) {

        } catch(NoSuchAlgorithmException e) {

        } catch(IOException e) {

        }
        return "";
    }

    @Override
    protected void onPostExecute(String str) {
        //qustomDialogBuilder.getDialo
    }
}