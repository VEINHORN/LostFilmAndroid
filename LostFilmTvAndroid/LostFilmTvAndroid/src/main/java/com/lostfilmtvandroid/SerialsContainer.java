package com.lostfilmtvandroid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsContainer implements Iterable<SerialItem> {
    private List<SerialItem> serialItemArrayList = new ArrayList<>();

    public void addSerial(SerialItem serialItem) {
        serialItemArrayList.add(serialItem);
    }

    public SerialItem getSerial(int position) {
        return serialItemArrayList.get(position);
    }

    public Iterator<SerialItem> iterator() {
        return serialItemArrayList.iterator();
    }
}
