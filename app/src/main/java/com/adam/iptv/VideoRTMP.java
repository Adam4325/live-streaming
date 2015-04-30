package com.adam.iptv;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.adam.iptv.app.AppController;

import com.adam.iptv.model.Contendata;
import com.adam.iptv.model.LiveData;
import com.adam.iptv.views.CustomListAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.R;

/**
 * Created by adam on 2/8/2015.
 */
public class VideoRTMP extends Activity {
    private static final String url2 = "http://majelisfathimiyah.org/content/getDataLive";
    private ProgressDialog pDialog;
    Context context;
    private static io.vov.vitamio.widget.VideoView mVideoView;
    private String path ;
    private LiveData lData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lData = new LiveData();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        context = this;
        //path=null;
        if (!LibsChecker.checkVitamioLibs(VideoRTMP.this)) return ;

        pDialog = new ProgressDialog(context);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonArrayRequest pathLive = new JsonArrayRequest(url2,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        hidePDialog();
                        int i=0;
                        //for (int i = 0; i < response.length(); i++) {
                        if(i<response.length()){
                           // i = i + 1;
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                lData.setPath(obj.getString("path").toString());
                                path=lData.getPath();
                                Log.e("ALAMAT",""+path);

                                mVideoView = (io.vov.vitamio.widget.VideoView)findViewById(R.id.surface_view);
                                //mVideoView = (VideoView)rootView.findViewById(R.id.vVideo);
                                //mVideoView.setVideoPath("rtmp://live.majelisfathimiyah.org/live/tarunabhakti");
                                mVideoView.setVideoPath(path);
                                mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
                                mVideoView.setBufferSize(2048);
                                mVideoView.requestFocus();
                                mVideoView.start();
                                mVideoView.setMediaController(new io.vov.vitamio.widget.MediaController(context));
                                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mediaPlayer) {
                                        //prodlg.dismiss();
                                        //prodlg.dismiss();
                                        //mediaPlayer.

                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
                Toast.makeText(context,"Connection Off!",Toast.LENGTH_LONG).show();

            }
        });

        AppController.getInstance().addToRequestQueue(pathLive);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //finish();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    public void startPlay(View view) {
        if (!TextUtils.isEmpty(path)) {
            mVideoView.setVideoPath(path);
        }
    }

    public void openVideo(View View) {
        mVideoView.setVideoPath(path);
    }
}
