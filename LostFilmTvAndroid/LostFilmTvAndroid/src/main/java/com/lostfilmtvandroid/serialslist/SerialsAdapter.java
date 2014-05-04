package com.lostfilmtvandroid.serialslist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lostfilmtvandroid.R;
import com.lostfilmtvandroid.SerialDescriptionActivity;
import com.lostfilmtvandroid.lostfilmtv.entities.Serial;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialItem;
import com.lostfilmtvandroid.lostfilmtv.entities.SerialsContainer;
import com.squareup.picasso.Picasso;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialsAdapter extends BaseAdapter implements Filterable {
    private static class ViewHolder {
        public TextView title;
        public TextView originalTitle;
        public ImageView poster;
    }

    private LayoutInflater layoutInflater;
    private SerialsContainer serialsContainer;
    private Context context;
    private GridView gridView;

    public SerialsContainer getSerialsContainer() {
        return serialsContainer;
    }

    public void setSerialsContainer(SerialsContainer serialsContainer) {
        this.serialsContainer = serialsContainer;
    }

    public SerialsAdapter(Context context, SerialsContainer serialsContainer, GridView gridView) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.serialsContainer = serialsContainer;
        this.gridView = gridView;
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

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.serial_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.serial_title);
            viewHolder.originalTitle = (TextView) convertView.findViewById(R.id.serial_original_title);
            viewHolder.poster = (ImageView) convertView.findViewById(R.id.serial_poster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(serialsContainer.getSerial(position).getTitle());
        viewHolder.originalTitle.setText(serialsContainer.getSerial(position).getOriginalTitle());
        viewHolder.poster.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
        Picasso.with(context).load(((SerialItem) serialsContainer.getSerial(position)).getPosterUrl()).resize(480, 270).into(viewHolder.poster);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults filterResults = new FilterResults();
                if (constraint != null && constraint.length() > 0) {
                    SerialsContainer filteredSerialsContainer = new SerialsContainer();
                    for (Serial serial : serialsContainer) {
                        if (serial.getTitle().length() >= constraint.length()) {
                            if (serial.getTitle().substring(0, constraint.length()).toLowerCase().contains(constraint)) {
                                filteredSerialsContainer.addSerial(serial);
                            }
                        }
                    }
                    // for english title
                    if (filteredSerialsContainer.size() == 0) {
                        for (Serial serial : serialsContainer) {
                            if (serial.getOriginalTitle().length() >= constraint.length()) {
                                if (serial.getOriginalTitle().substring(0, constraint.length()).toLowerCase().contains(constraint)) {
                                    filteredSerialsContainer.addSerial(serial);
                                }
                            }
                        }
                    }
                    filterResults.count = filteredSerialsContainer.size();
                    filterResults.values = filteredSerialsContainer;
                } else {
                    filterResults.count = serialsContainer.size();
                    filterResults.values = serialsContainer;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(final CharSequence constraint, FilterResults results) {
                final SerialsContainer newSerialsContainer = (SerialsContainer) results.values;
                gridView.setAdapter(new SerialsAdapter(context, newSerialsContainer, gridView));
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, SerialDescriptionActivity.class);
                        intent.putExtra("serialUrl", newSerialsContainer.getSerial(position).getPageUrl());
                        context.startActivity(intent);
                    }
                });
            }
        };
    }
}