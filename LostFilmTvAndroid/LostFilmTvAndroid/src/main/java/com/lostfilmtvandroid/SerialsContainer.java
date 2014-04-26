package com.lostfilmtvandroid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsContainer implements Iterable<Serial> {
    private List<Serial> serialArrayList = new ArrayList<>();

    public void addSerial(Serial serial) {
        serialArrayList.add(serial);
    }

    public Serial getSerial(int position) {
        return serialArrayList.get(position);
    }

    public Iterator<Serial> iterator() {
        return serialArrayList.iterator();
    }
}
