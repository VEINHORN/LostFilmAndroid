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
public class SerialDescriptionLoader extends AsyncTask<String, Integer, SerialItemDescription> {
    private final static String BRACKETS_REG_EXP = "\\((.*)\\)";
    private final static String DELETE_BRACKETS_CONTENT_REG_EXP = " +\\((.*)\\)";
    private final static String A_TAG = "a";
    private final static String HREF_ATTRIBUTE = "href";
    private final static String MID_CLASS = "mid";
    private final static String DIV_TAG = "div";
    private final static String H1_TAG = "h1";
    private final static String IMG_TAG = "img";
    private final static String SPAN_TAG = "span";
    private final static String SRC_ATTRIBUTE = "src";

    private TextView textView;
    private String url;

    public SerialDescriptionLoader(TextView textView, String url) {
        this.textView = textView;
        this.url = url;
    }

    @Override
    protected SerialItemDescription doInBackground(String... params) {
        SerialItemDescription serialDescription = new SerialItemDescription();

        String title = "";
        String originalTitle = "";
        String posterUrl = "";
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
            Elements elements = document.getElementsByClass(MID_CLASS);
            Element midElement = elements.get(0);
            Element serialDescriptionElement = midElement.getElementsByTag(DIV_TAG).get(1);

            String tempTitle = serialDescriptionElement.getElementsByTag(H1_TAG).get(0).text();
            title = tempTitle.replaceAll(DELETE_BRACKETS_CONTENT_REG_EXP, "");
            Pattern pattern = Pattern.compile(BRACKETS_REG_EXP);
            Matcher matcher = pattern.matcher(tempTitle);
            while(matcher.find()) {
                originalTitle = matcher.group().substring(1, matcher.group().length() - 1);
            }

            posterUrl = SerialItemsLoader.LOSTFILM_URL + serialDescriptionElement.getElementsByTag(IMG_TAG).get(0).attr(SRC_ATTRIBUTE);

            Elements spanElements = serialDescriptionElement.getElementsByTag(SPAN_TAG);

            year = spanElements.get(0).text();
            genres = spanElements.get(1).text();
            numberOfSeasons = spanElements.get(2).text();
            description = spanElements.get(3).text();

            rawDescription = serialDescriptionElement.ownText();
            pattern = Pattern.compile("Страна: [a-zA-Zа-яА-Я]+\\b");
            matcher = pattern.matcher(rawDescription);
            while(matcher.find()) {
                country = matcher.group().split(" ")[1];
            }
            pattern = Pattern.compile("Статус: [a-zA-Zа-яА-Я]+\\b");
            matcher = pattern.matcher(rawDescription);
            while(matcher.find()) {
                status = matcher.group().split(" ")[1];
            }

            officialPage = serialDescriptionElement.getElementsByTag(A_TAG).get(0).attr(HREF_ATTRIBUTE);

            serialDescription.setTitle(title);
            serialDescription.setOriginalTitle(originalTitle);
            serialDescription.setPosterUrl(posterUrl);
            serialDescription.setYear(year);
            serialDescription.setGenres(genres);
            serialDescription.setNumberOfSeasons(numberOfSeasons);
            serialDescription.setDescription(description);
            serialDescription.setCountry(country);
            serialDescription.setStatus(status);
            serialDescription.setOfficialPage(officialPage);
        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return serialDescription;
    }

    @Override
    protected void onPostExecute(SerialItemDescription serialDescription) {
        textView.setText(serialDescription.toString());
    }
}