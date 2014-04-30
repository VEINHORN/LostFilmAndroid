package com.lostfilmtvandroid.serialslist;

import android.os.AsyncTask;
import android.widget.TextView;

import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
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
        return SerialsFetcher.loadSerialItems();
    }

    @Override
    protected void onPostExecute(SerialsContainer serialsContainer) {
        serialsAdapter.setSerialsContainer(serialsContainer);
        serialsAdapter.notifyDataSetChanged();
        testTextView.setText(Integer.toString(serialsAdapter.getSerialsContainer().size()));
    }
}
