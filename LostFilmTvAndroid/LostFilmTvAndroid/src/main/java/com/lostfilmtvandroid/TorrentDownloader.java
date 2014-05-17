package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 17.5.14.
 */

import android.os.AsyncTask;

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
    private File[] files;

    public TorrentDownloader(File[] files) {
        this.files = files;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(String... vars) {

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
                //publishProgress(Long.toString(torrent.getPeersManager().getDownloaded()), Long.toString(metafile.getLength()));
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
        //downloadedTextView.setText("Completed");
    }
}