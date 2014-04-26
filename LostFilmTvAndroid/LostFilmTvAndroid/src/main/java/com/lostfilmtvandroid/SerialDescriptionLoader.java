package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String year = "";
        String genres = "";
        String numberOfSeasons = "";
        String description = "";

        String rawDescription = ""; // contains raw text from div
        String country = "";
        String status = "";

        String officialPage = "";

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("mid");
            Element midElement = elements.get(0);
            Element serialDescriptionElement = midElement.getElementsByTag("div").get(1);
            poster = SerialsLoader.LOSTFILM_URL + serialDescriptionElement.getElementsByTag("img").get(0).attr("src");
            Elements spanElements = serialDescriptionElement.getElementsByTag("span");
            year = spanElements.get(0).text();
            genres = spanElements.get(1).text();
            numberOfSeasons = spanElements.get(2).text();
            description = spanElements.get(3).text();

            rawDescription = serialDescriptionElement.ownText();
            Pattern pattern = Pattern.compile("Страна: [a-zA-Zа-яА-Я]+\\b");
            Matcher matcher = pattern.matcher(rawDescription);
            while(matcher.find()) {
                country = matcher.group().split(" ")[1];
            }
            pattern = Pattern.compile("Статус: [a-zA-Zа-яА-Я]+\\b");
            matcher = pattern.matcher(rawDescription);
            while(matcher.find()) {
                status = matcher.group().split(" ")[1];
            }

            officialPage = serialDescriptionElement.getElementsByTag("a").get(0).attr("href");

        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return officialPage;
    }

    @Override
    protected void onPostExecute(String text) {
        textView.setText(text);
    }
}