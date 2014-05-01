package com.lostfilmtvandroid;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.lostfilmtvandroid.lostfilmtv.entities.SerialItem;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by veinhorn on 1.5.14.
 */
public class LostFilmFromXML {
    private SerialsContainer serialsContainer;
    private SerialItem serialItem;
    private String text;

    public LostFilmFromXML() {
        serialsContainer = new SerialsContainer();
    }

    public  SerialsContainer loadFromXML(Context context) {
        XmlResourceParser xmlResourceParser = context.getResources().getXml(R.xml.lostfilm_db_lite);
        serialItem = new SerialItem();
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xmlResourceParser.getName();
                switch(eventType) {
                    case XmlPullParser.START_TAG: {
                        if(tagName.equalsIgnoreCase("tvshow")) {
                            serialItem = new SerialItem();
                        }
                        break;
                    }
                    case XmlPullParser.TEXT: {
                        text = xmlResourceParser.getText();
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if(tagName.equalsIgnoreCase("tvshow")) {
                            serialsContainer.addSerial(serialItem);
                        } else if(tagName.equalsIgnoreCase("title")) {
                            serialItem.setTitle(text);
                        } else if(tagName.equalsIgnoreCase("originaltitle")) {
                            serialItem.setOriginalTitle(text);
                        } else if(tagName.equalsIgnoreCase("pageurl")) {
                            serialItem.setPageUrl(text);
                        } else if(tagName.equalsIgnoreCase("posterUrl")) {
                            serialItem.setPosterUrl(text);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlResourceParser.next();
            }
        } catch(XmlPullParserException e) {

        } catch(IOException e) {

        }
        return serialsContainer;
    }
}
