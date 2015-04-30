package com.adam.iptv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.adam.iptv.app.AppController;
import com.adam.iptv.model.Contendata;
import com.adam.iptv.views.CustomDetailAdapter;
import com.adam.iptv.views.CustomListAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.R;

/**
 * Created by adam on 2/9/2015.
 */
public class DetailArtikel extends Activity{

    private static final String url = "http://majelisfathimiyah.org/content/getDataContentDetail/";
    private ProgressDialog pDialog;
    private List<Contendata> contentData = new ArrayList<Contendata>();
    private ListView listView;
    private CustomDetailAdapter adapter;
    private String id_contents,contentShare;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_row_artikel);


            Bundle extras =getIntent().getExtras();
            id_contents=null;

            if (extras != null) {
                id_contents = extras.getString("id_C");
            }




            listView = (ListView)findViewById(R.id.list);
            //adapter = new CustomListAdapter(this, contentData);
            adapter = new CustomDetailAdapter(this, contentData);
            listView.setAdapter(adapter);



            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage("Loading...");
            pDialog.show();
            JsonArrayRequest movieReq = new JsonArrayRequest(url+id_contents,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            hidePDialog();


                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Contendata cd = new Contendata();
                                    JSONObject obj = response.getJSONObject(i);
                                    cd.setId_content(obj.getString("id_content"));
                                    cd.setCreate_date(obj.getString("create_date"));
                                    cd.setJudul(obj.getString("judul"));
                                    cd.setCoverUrl(obj.getString("cover"));
                                    cd.setContentIsi(obj.getString("content"));
                                    cd.setHit(Integer.parseInt(obj.getString("hit")));
                                    contentData.add(cd);

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                            }

                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();
                    //Toast.makeText(getActivity(),"Connection Off!",Toast.LENGTH_LONG).show();

                }
            });

            // Adding request to request queue
            Log.e("URL",url+id_contents);
            AppController.getInstance().addToRequestQueue(movieReq);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    contentShare = ((TextView) view.findViewById(R.id.contentDetail)).getText().toString();
                    Log.e("ID CONTENTS:", contentShare.toString());
                }
            });

//            btnShareAja.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //contentShare = ((TextView)findViewById(R.id.contentDetail)).getText().toString();
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_TEXT,"Your score and Some extra text");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "The title");
//                    startActivity(Intent.createChooser(shareIntent, "Share..."));
//                }
//            });
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
