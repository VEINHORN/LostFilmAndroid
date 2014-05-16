package com.lostfilmtvandroid.torrentrss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by veinhorn on 11.5.14.
 */
public class SerialsContainer implements Iterable<Serial> {
    List<Serial> serials = new ArrayList<>();

    public void addSerial(Serial serial) {
        serials.add(serial);
    }

    public Serial getSerial(int position) {
        return serials.get(position);
    }

    public int size() {
        return serials.size();
    }

    public Iterator<Serial> iterator() {
        return serials.iterator();
    }

    public String searchUrl(String serialTitle) {
        String url = "";
        for(Serial serial : serials) {
            if(serial.getTitle().toLowerCase().contains(serialTitle.toLowerCase())) {
                url = serial.getLink();
                break;
            }
        }
        return url;
    }
}
