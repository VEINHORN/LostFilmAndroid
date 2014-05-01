package com.lostfilmtvandroid.serialslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lostfilmtvandroid.R;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialItem;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.squareup.picasso.Picasso;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialsAdapter extends BaseAdapter {
    private static class ViewHolder {
        public TextView title;
        public TextView originalTitle;
        public ImageView poster;
    }

    private LayoutInflater layoutInflater;
    private SerialsContainer serialsContainer;
    private Context context;

    public SerialsContainer getSerialsContainer() {
        return serialsContainer;
    }

    public void setSerialsContainer(SerialsContainer serialsContainer) {
        this.serialsContainer = serialsContainer;
    }

    public SerialsAdapter(Context context, SerialsContainer serialsContainer) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.serialsContainer = serialsContainer;
    }

    @Override
    public int getCount() {
        return serialsContainer.size();
    }

    @Override
    public Object getItem(int position) {
        return serialsContainer.getSerial(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.serial_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView)convertView.findViewById(R.id.serial_title);
            viewHolder.originalTitle = (TextView)convertView.findViewById(R.id.serial_original_title);
            viewHolder.poster = (ImageView)convertView.findViewById(R.id.serial_poster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(serialsContainer.getSerial(position).getTitle());
        viewHolder.originalTitle.setText(serialsContainer.getSerial(position).getOriginalTitle());
        Picasso.with(context).load(((SerialItem)serialsContainer.getSerial(position)).getPosterUrl()).into(viewHolder.poster);
        return convertView;
    }
}