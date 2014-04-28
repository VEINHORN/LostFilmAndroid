package com.lostfilmtvandroid;

import android.os.AsyncTask;

import com.lostfilmtvandroid.lostfilmtv.entities.Serial;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.lostfilmtvandroid.lostfilmtv.fetchers.SerialsFetcher;

import br.com.dina.ui.widget.UITableView;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsLoader extends AsyncTask<String, Integer, SerialsContainer> {
    private SerialsContainer serialsContainer;
    private UITableView uiTableView;

    public SerialsLoader(UITableView uiTableView) {
        this.uiTableView = uiTableView;
    }

    @Override
    protected SerialsContainer doInBackground(String... params) {
        return SerialsFetcher.loadSerialItems();
    }

    @Override
    protected void onPostExecute(SerialsContainer serialsContainer) {
        this.serialsContainer = serialsContainer;
        for(Serial serialItem : serialsContainer) {
            uiTableView.addBasicItem(serialItem.getTitle(), serialItem.getOriginalTitle());
        }
        uiTableView.commit();
    }

    public SerialsContainer getSerials() {
        return serialsContainer;
    }
}
