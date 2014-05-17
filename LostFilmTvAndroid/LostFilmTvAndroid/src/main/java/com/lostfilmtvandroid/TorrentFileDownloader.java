package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by veinhorn on 17.5.14.
 */
public class TorrentFileDownloader extends AsyncTask<String, Integer, File[]> {
    private String urlString;
    private String episodeTitle;

    public TorrentFileDownloader(String urlString, String episodeTitle) {
        this.urlString = urlString;
        this.episodeTitle = episodeTitle;
    }

    @Override
    protected File[] doInBackground(String... params) {
        File file = new File(Environment.getExternalStorageDirectory(), "lostfilmtv");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");
            }
        }

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            Log.d("test", file.getAbsolutePath());

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream(file.getAbsolutePath() + "/" + episodeTitle + ".torrent");

            byte data[] = new byte[1024];

            long total = 0;
            int count;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                //publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch(MalformedURLException e) {

        } catch(IOException e) {

        }
        File files[] = {file, new File(file, episodeTitle + ".torrent")}; // to, from
        return files;
    }

    @Override
    protected void onPostExecute(File[] files) {
        TorrentDownloader torrentDownloader = new TorrentDownloader(files);
        torrentDownloader.execute();
    }
}
