package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialDescriptionLoader extends AsyncTask<String, Integer, String> {
    private TextView textView;
    private String url;

    public SerialDescriptionLoader(TextView textView, String url) {
        this.textView = textView;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... params) {
        String poster = "";
        String text = "";
        //String country = ""; // don't forget
        String year = "";
        String genres = "";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("mid");
            Element midElement = elements.get(0);
            Element serialDescriptionElement = midElement.getElementsByTag("div").get(1);
            poster = SerialsLoader.LOSTFILM_URL + serialDescriptionElement.getElementsByTag("img").get(0).attr("src");
            Elements spanElements = serialDescriptionElement.getElementsByTag("span");
            year = spanElements.get(0).text();
            genres = spanElements.get(1).text();
            //country = serialDescriptionElement.getElementsByTag("span").toString();
        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return genres;
    }

    @Override
    protected void onPostExecute(String text) {
        textView.setText(text);
    }
}