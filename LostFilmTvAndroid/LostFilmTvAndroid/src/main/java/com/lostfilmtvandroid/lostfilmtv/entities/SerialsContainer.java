package com.lostfilmtvandroid.lostfilmtv.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsContainer implements Iterable<Serial> {
    private List<Serial> serialItemArrayList;

    public SerialsContainer() {
        serialItemArrayList  = new ArrayList<>();
    }

    public SerialsContainer(SerialsContainer serialsContainer) {
        serialItemArrayList  = new ArrayList<>();
        for(Serial serial : serialsContainer) {
            Serial newSerial = new Serial();
            newSerial.setTitle(serial.getTitle());
            newSerial.setOriginalTitle(serial.getOriginalTitle());
            newSerial.setPageUrl(serial.getPageUrl());
            addSerial(newSerial);
        }
    }

    public int size() {
        return serialItemArrayList.size();
    }

    public void addSerial(Serial serialItem) {
        serialItemArrayList.add(serialItem);
    }

    public Serial getSerial(int position) {
        return serialItemArrayList.get(position);
    }

    public Iterator<Serial> iterator() {
        return serialItemArrayList.iterator();
    }
}