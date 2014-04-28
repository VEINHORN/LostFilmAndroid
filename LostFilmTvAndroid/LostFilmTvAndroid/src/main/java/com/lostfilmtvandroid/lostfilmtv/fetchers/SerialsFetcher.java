package com.lostfilmtvandroid.lostfilmtv.fetchers;

import com.lostfilmtvandroid.lostfilmtv.entities.Serial;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialsFetcher extends LostFilmTvFetcher {
    private final static String HREF_ATTRIBUTE = "href";
    private final static String CLASS = "bb_a";
    private final static String REG_EXP = "\\((.*)\\)";

    public static SerialsContainer loadSerialItems() {
        SerialsContainer serialsContainer = new SerialsContainer();
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
                serialsContainer.addSerial(new Serial(title, originalTitle, LOSTFILM_URL + pageUrl));
            }
        } catch(IOException e) {

        }
        return serialsContainer;
    }
}
