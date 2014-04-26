package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsLoader extends AsyncTask<String, Integer, SerialsContainer> {
    private final static String LOSTFILM_URL = "http://www.lostfilm.tv";
    private final static String ALL_SERIALS_URL = "http://www.lostfilm.tv/serials.php";
    private final static String HREF_ATTRIBUTE = "href";
    private final static String CLASS = "bb_a";
    private final static String REG_EXP = "\\((.*)\\)";

    private TextView textView;
    private SerialsContainer serialsContainer;

    public SerialsLoader(TextView textView, SerialsContainer serialsContainer) {
        this.textView = textView;
        this.serialsContainer = serialsContainer;
    }

    @Override
    protected SerialsContainer doInBackground(String... params) {
        try {
            Elements elements = Jsoup.connect(ALL_SERIALS_URL).get().getElementsByClass(CLASS);
            for(Element element : elements) {
                String title = element.ownText();
                String pageUrl = element.attr(HREF_ATTRIBUTE);
                String originalTitle = "";
                Pattern pattern = Pattern.compile(REG_EXP);
                Matcher matcher = pattern.matcher(element.text());
                while(matcher.find()) {
                    String tempTitle = matcher.group();
                    originalTitle = tempTitle.substring(1, tempTitle.length() - 1); //delete brackets
                }
                serialsContainer.addSerial(new Serial(title, originalTitle, LOSTFILM_URL + pageUrl));
            }
        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return serialsContainer;
    }

    protected void onPostExecute(SerialsContainer serialsContainer) {

    }
}
