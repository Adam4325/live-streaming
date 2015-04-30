package com.adam.iptv.views;

/**
 * Created by adam on 2/7/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.adam.iptv.app.AppController;
import com.adam.iptv.model.Contendata;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import io.vov.vitamio.R;

public class CustomDetailAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Contendata> contentItems;


    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomDetailAdapter(Activity activity, List<Contendata> contentItems) {
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
            convertView = inflater.inflate(R.layout.detailartikel, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnailDetail);
        TextView id_contentD = (TextView) convertView.findViewById(R.id.id_content_detail);
        final TextView titleD = (TextView) convertView.findViewById(R.id.titleDetail);
        final TextView contentD = (TextView) convertView.findViewById(R.id.contentDetail);
        TextView tanggalD = (TextView) convertView.findViewById(R.id.tanggalDetail);
        TextView hitD = (TextView) convertView.findViewById(R.id.hitDetail);
        Button btnShareAja = (Button)convertView.findViewById(R.id.btnShare);

        // getting movie data for the row
        Contendata m = contentItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getCoverUrl(), imageLoader);
        titleD.setText(m.getJudul());
        hitD.setText("Hit: " + String.valueOf(m.getHit()));
        contentD.setText(m.getContentIsi());
        id_contentD.setText(m.getId_content());
        tanggalD.setText(String.valueOf(m.getCreate_date()));

        btnShareAja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlToShare = "http://majelisfathimiyah.org/";
                //contentShare = ((TextView)findViewById(R.id.contentDetail)).getText().toString();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Share From Android : \n " + urlToShare + "\n" + titleD.getText().toString() + " \n" + contentD.getText().toString());
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, titleD.getText().toString());

                boolean facebookAppFound = false;
                List<ResolveInfo> matches =v.getContext().getPackageManager().queryIntentActivities(shareIntent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                        shareIntent.setPackage(info.activityInfo.packageName);
                        facebookAppFound = true;
                        break;
                    }
                }

                if (!facebookAppFound) {
                    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare+"\n" + titleD.getText().toString() + " \n" + contentD.getText().toString();
                    shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                }
                //(Intent.createChooser(shareIntent, "Share..."));
                v.getContext().startActivity(Intent.createChooser(shareIntent, "Share via android ..."));
            }
        });
        return convertView;
    }

}