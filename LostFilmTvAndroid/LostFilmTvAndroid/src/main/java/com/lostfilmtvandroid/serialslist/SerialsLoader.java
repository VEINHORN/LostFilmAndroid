package com.lostfilmtvandroid.serialslist;

import android.content.Context;
import android.os.AsyncTask;

import com.lostfilmtvandroid.LostFilmFromXML;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialsLoader extends AsyncTask<String, Integer, SerialsContainer> {
    private SerialsAdapter serialsAdapter;
    private Context context;

    public SerialsLoader(Context context, SerialsAdapter serialsAdapter) {
        this.context = context;
        this.serialsAdapter = serialsAdapter;
    }

    @Override
    protected SerialsContainer doInBackground(String... params) {
        return new LostFilmFromXML().loadFromXML(context);
    }

    @Override
    protected void onPostExecute(SerialsContainer serialsContainer) {
        serialsAdapter.setSerialsContainer(serialsContainer);
        serialsAdapter.notifyDataSetChanged();
    }
}
