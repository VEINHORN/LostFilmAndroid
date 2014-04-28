package com.lostfilmtvandroid.serialslist;

import android.os.AsyncTask;
import android.widget.TextView;

import com.lostfilmtvandroid.lostfilmtv.entities.Serial;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.lostfilmtvandroid.lostfilmtv.fetchers.PosterLoader;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialsFetcher;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsLoader extends AsyncTask<String, Integer, SerialsContainer> {
    private SerialsAdapter serialsAdapter;
    private TextView testTextView;

    public SerialsLoader(TextView testTextView, SerialsAdapter serialsAdapter) {
        this.testTextView = testTextView;
        this.serialsAdapter = serialsAdapter;
    }

    @Override
    protected SerialsContainer doInBackground(String... params) {
        SerialsContainer serials = SerialsFetcher.loadSerialItems();

        SerialsContainer newSerialsContainer = new SerialsContainer();

        for(Serial serial : serials) {
            SerialItem serialItem = new SerialItem();
            serialItem.setTitle(serial.getTitle());
            serialItem.setOriginalTitle(serial.getOriginalTitle());
            serialItem.setPageUrl(serial.getPageUrl());
            serialItem.setPosterUrl(PosterLoader.loadPosterUrl(serial));

            newSerialsContainer.addSerial(serialItem);
        }
        return newSerialsContainer;
    }

    @Override
    protected void onPostExecute(SerialsContainer serialsContainer) {
        serialsAdapter.setSerialsContainer(serialsContainer);
        serialsAdapter.notifyDataSetChanged();
        testTextView.setText(Integer.toString(serialsAdapter.getSerialsContainer().size()));
    }
}
