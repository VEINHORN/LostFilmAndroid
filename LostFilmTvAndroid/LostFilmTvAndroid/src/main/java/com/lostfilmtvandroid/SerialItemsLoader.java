package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.dina.ui.widget.UITableView;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialItemsLoader extends AsyncTask<String, Integer, SerialsContainer> {
    public final static String LOSTFILM_URL = "http://www.lostfilm.tv";
    private final static String ALL_SERIALS_URL = "http://www.lostfilm.tv/serials.php";
    private final static String HREF_ATTRIBUTE = "href";
    private final static String CLASS = "bb_a";
    private final static String REG_EXP = "\\((.*)\\)";

    private SerialsContainer serialsContainer;
    private UITableView uiTableView;

    public SerialItemsLoader(SerialsContainer serialsContainer, UITableView uiTableView) {
        this.serialsContainer = serialsContainer;
        this.uiTableView = uiTableView;
    }

    @Override
    protected SerialsContainer doInBackground(String... params) {
        try {
            Elements elements = Jsoup.connect(ALL_SERIALS_URL).get().getElementsByClass(CLASS);
            for(Element element : elements) {
                String title = element.ownText();
                String originalTitle = "";
                String pageUrl = element.attr(HREF_ATTRIBUTE);
                Pattern pattern = Pattern.compile(REG_EXP);
                Matcher matcher = pattern.matcher(element.text());
                while(matcher.find()) {
                    String tempTitle = matcher.group();
                    originalTitle = tempTitle.substring(1, tempTitle.length() - 1); //delete brackets
                }
                serialsContainer.addSerial(new SerialItem(title, originalTitle, LOSTFILM_URL + pageUrl));
            }
        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return serialsContainer;
    }

    protected void onPostExecute(SerialsContainer serialsContainer) {
        for(SerialItem serialItem : serialsContainer) {
            uiTableView.addBasicItem(serialItem.getTitle(), serialItem.getOriginalTitle());
        }
        uiTableView.commit();
    }
}
