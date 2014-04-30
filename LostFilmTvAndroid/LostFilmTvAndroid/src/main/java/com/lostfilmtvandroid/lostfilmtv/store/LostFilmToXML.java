package com.lostfilmtvandroid.lostfilmtv.store;

import com.lostfilmtvandroid.lostfilmtv.entities.Episode;
import com.lostfilmtvandroid.lostfilmtv.entities.Season;
import com.lostfilmtvandroid.lostfilmtv.entities.Seasons;
import com.lostfilmtvandroid.lostfilmtv.entities.Serial;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialDescription;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialDescriptionFetcher;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialsFetcher;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by veinhorn on 30.4.14.
 */
public class LostFilmToXML {
    public static void liteSaveToXML(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //root
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("tvshows");
            document.appendChild(rootElement);

            SerialsContainer serialsContainer = SerialsFetcher.loadSerialItems();

            for (Serial serial : serialsContainer) {
                Element serialElement = document.createElement("tvshow");

                Element titleElement = document.createElement("title");
                titleElement.setTextContent(serial.getTitle());
                serialElement.appendChild(titleElement);
                Element originalTitleElement = document.createElement("originaltitle");
                originalTitleElement.setTextContent(serial.getOriginalTitle());
                serialElement.appendChild(originalTitleElement);
                Element pageUrlElement = document.createElement("pageurl");
                pageUrlElement.setTextContent(serial.getPageUrl());
                serialElement.appendChild(pageUrlElement);

                SerialDescription serialDescription = SerialDescriptionFetcher.loadSerialDescription(serial.getPageUrl());
                Element posterUrlElement = document.createElement("posterUrl");
                posterUrlElement.setTextContent(serialDescription.getPosterUrl());
                serialElement.appendChild(posterUrlElement);

                // End
                rootElement.appendChild(serialElement);
            }
            // Write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch(ParserConfigurationException e) {

        } catch(TransformerConfigurationException e) {

        } catch(TransformerException e) {

        }
    }

    public static void saveToXML(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //root
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("tvshows");
            document.appendChild(rootElement);

            SerialsContainer serialsContainer = SerialsFetcher.loadSerialItems();

            for(Serial serial : serialsContainer) {
                Element serialElement = document.createElement("tvshow");

                Element titleElement = document.createElement("title");
                titleElement.setTextContent(serial.getTitle());
                serialElement.appendChild(titleElement);
                Element originalTitleElement = document.createElement("originaltitle");
                originalTitleElement.setTextContent(serial.getOriginalTitle());
                serialElement.appendChild(originalTitleElement);
                Element pageUrlElement = document.createElement("pageurl");
                pageUrlElement.setTextContent(serial.getPageUrl());
                serialElement.appendChild(pageUrlElement);

                // Serial description
                SerialDescription serialDescription = SerialDescriptionFetcher.loadSerialDescription(serial.getPageUrl());

                Element countryElement = document.createElement("country");
                countryElement.setTextContent(serialDescription.getCountry());
                serialElement.appendChild(countryElement);

                Element descriptionElement = document.createElement("description");
                descriptionElement.setTextContent(serialDescription.getDescription());
                serialElement.appendChild(descriptionElement);

                Element genresElement = document.createElement("genres");
                genresElement.setTextContent(serialDescription.getGenres());
                serialElement.appendChild(genresElement);

                Element numberOfSeasonsElement = document.createElement("numberofseasons");
                numberOfSeasonsElement.setTextContent(serialDescription.getNumberOfSeasons());
                serialElement.appendChild(numberOfSeasonsElement);

                Element officialPageElement = document.createElement("officialpage");
                officialPageElement.setTextContent(serialDescription.getOfficialPage());
                serialElement.appendChild(officialPageElement);

                Element posterUrlElement = document.createElement("posterUrl");
                posterUrlElement.setTextContent(serialDescription.getPosterUrl());
                serialElement.appendChild(posterUrlElement);

                Element statusElement = document.createElement("status");
                statusElement.setTextContent(serialDescription.getStatus());
                serialElement.appendChild(statusElement);

                Element yearElement = document.createElement("year");
                yearElement.setTextContent(serialDescription.getYear());
                serialElement.appendChild(yearElement);

                Element seasonsElement = document.createElement("seasons");
                Seasons seasons = serialDescription.getSeasons();
                for(Season season : seasons) {
                    Element seasonElement = document.createElement("season");

                    Element seasonNumberElement = document.createElement("seasonnumber");
                    seasonNumberElement.setTextContent(season.getSeasonNumber().toString());
                    seasonElement.appendChild(seasonNumberElement);

                    for(Episode episode : season) {
                        Element episodeElement = document.createElement("episode");

                        Element commentsNumberElement = document.createElement("commentsnumber");
                        commentsNumberElement.setTextContent(episode.getCommentsNumber());
                        episodeElement.appendChild(commentsNumberElement);

                        Element commentsUrlElement = document.createElement("commentsurl");
                        commentsUrlElement.setTextContent(episode.getCommentsUrl());
                        episodeElement.appendChild(commentsUrlElement);

                        Element episodeDescriptionUrlElement = document.createElement("episodedescriptionurl");
                        episodeDescriptionUrlElement.setTextContent(episode.getEpisodeDescriptionUrl());
                        episodeElement.appendChild(episodeDescriptionUrlElement);

                        Element ratingElement = document.createElement("rating");
                        ratingElement.setTextContent(episode.getRating());
                        episodeElement.appendChild(ratingElement);

                        Element seasonsNumber = document.createElement("seasonsnumber");
                        seasonsNumber.setTextContent(episode.getSeasonsNumber());
                        episodeElement.appendChild(seasonsNumber);

                        Element title = document.createElement("title");
                        title.setTextContent(episode.getTitle());
                        episodeElement.appendChild(title);

                        Element originalTitle = document.createElement("originaltitle");
                        originalTitle.setTextContent(episode.getOriginalTitle());
                        episodeElement.appendChild(originalTitle);

                        seasonElement.appendChild(episodeElement);
                    }
                    seasonsElement.appendChild(seasonElement);
                }
                serialElement.appendChild(seasonsElement);

                // End
                rootElement.appendChild(serialElement);
            }


            // Write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch(ParserConfigurationException e) {

        } catch(TransformerConfigurationException e) {

        } catch(TransformerException e) {

        }
    }
}