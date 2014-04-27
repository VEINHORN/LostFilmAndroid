package com.lostfilmtvandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private final static String NOBR_TAG = "nobr";
    private final static String T_ROW_CLASS = "t_row";
    private final static String B_TAG = "b";
    private final static String ALL_FIRST_SEASON = "Первый сезон полностью";

    private TextView textView;
    private String url;
    private String text = "";

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

            Elements episodeElements = midElement.getElementsByClass(T_ROW_CLASS);
            List<Episode> episodes = new ArrayList<>();
            for(Element episodeElement : episodeElements) {
                Episode episode = new Episode();
                String episodeTitle = episodeElement.getElementsByTag(NOBR_TAG).get(0).getElementsByTag(SPAN_TAG).get(0).ownText();
                String episodeOriginalTitle = episodeElement.getElementsByTag(NOBR_TAG).get(0).ownText().substring(1, episodeElement.getElementsByTag(NOBR_TAG).get(0).ownText().length() - 1);
                String episodeRating = episodeElement.getElementsByTag(B_TAG).get(0).ownText();
                String episodeCommentsNumber = episodeElement.getElementsByTag(B_TAG).get(1).ownText();
                String episodeCommentsUrl = SerialItemsLoader.LOSTFILM_URL + episodeElement.getElementsByTag(A_TAG).get(0).attr(HREF_ATTRIBUTE);
                String episodeDescriptionUrl = SerialItemsLoader.LOSTFILM_URL + episodeElement.getElementsByTag(A_TAG).get(1).attr(HREF_ATTRIBUTE);
                String episodeSeasonNumber = "";
                pattern = Pattern.compile("s=\\d");
                matcher = pattern.matcher(episodeCommentsUrl);
                while(matcher.find()) {
                    episodeSeasonNumber = matcher.group().replace("s=", "");
                }

                episode.setSeasonsNumber(episodeSeasonNumber);
                episode.setTitle(episodeTitle);
                episode.setOriginalTitle(episodeOriginalTitle);
                episode.setRating(episodeRating);
                episode.setCommentsNumber(episodeCommentsNumber);
                episode.setCommentsUrl(episodeCommentsUrl);
                episode.setEpisodeDescriptionUrl(episodeDescriptionUrl);

                if(!episodeTitle.equals(ALL_FIRST_SEASON)) {
                    episodes.add(episode);
                }
            }

            Seasons seasons = new Seasons();
            int numberOfSeasonsInt = Integer.parseInt(episodes.get(0).getSeasonsNumber());
            for(int season = 1; season <= numberOfSeasonsInt; season++) {
                Season seasonObj = new Season();
                seasonObj.setSeasonNumber(season);
                for(Episode episode : episodes) {
                    if(episode.getSeasonsNumber().equals(Integer.toString(season))) {
                        seasonObj.addEpisode(episode);
                    }
                }
                seasonObj.reverse();
                seasons.addSeason(seasonObj);
            }

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

            serialDescription.setSeasons(seasons);
        } catch(IOException e) {
            Log.e(StringConstants.TAG, StringConstants.EXCEPTION, e);
        }
        return serialDescription;
    }

    @Override
    protected void onPostExecute(SerialItemDescription serialDescription) {
        /*
        String str = "";
        for(Episode episode : episodes) {
            str += episode.toString() + "\n";
        }*/
        textView.setText(serialDescription.getSeasons().getSeason(4).getEpisode(3).toString());
        //textView.setText(serialDescription.toString());
    }
}