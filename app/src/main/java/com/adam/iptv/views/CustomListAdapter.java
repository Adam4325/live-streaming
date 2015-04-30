package com.adam.iptv.views;

/**
 * Created by adam on 2/7/2015.
 */
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.adam.iptv.app.AppController;
import com.adam.iptv.model.Contendata;
import com.adam.iptv.model.Movie;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import io.vov.vitamio.R;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Contendata> contentItems;


    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Contendata> contentItems) {
        this.activity = activity;
        this.contentItems = contentItems;
    }

    @Override
    public int getCount() {
        return contentItems.size();
    }

    @Override
    public Object getItem(int location) {
        return contentItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_artikels, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView id_content = (TextView) convertView.findViewById(R.id.id_content);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView tanggal = (TextView) convertView.findViewById(R.id.tanggal);
        TextView hit = (TextView) convertView.findViewById(R.id.hit);

        // getting movie data for the row
        Contendata m = contentItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getCoverUrl(), imageLoader);
        title.setText(m.getJudul());
        hit.setText("Hit: " + String.valueOf(m.getHit()));
        content.setText(m.getContentIsi());
        id_content.setText(m.getId_content());
        tanggal.setText(String.valueOf(m.getCreate_date()));
        return convertView;
    }

}