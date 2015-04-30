package com.adam.iptv;

/**
 * Created by adam on 2/7/2015.
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adam.iptv.app.AppController;
import com.adam.iptv.model.Contendata;
import com.adam.iptv.model.Movie;
import com.adam.iptv.views.CustomListAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import io.vov.vitamio.R;

import static android.widget.Toast.makeText;

public class MainArtikel extends Fragment {
    private static final String url = "http://majelisfathimiyah.org/content/getDataContent";
    //private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<Contendata> contentData = new ArrayList<Contendata>();
    private ListView listView;
    private CustomListAdapter adapter;
    Context context;
    private static String id_selected;

//    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_row_artikel, container, false);
        return rootView;
    }

//    public static MainArtikel newInstance() {
//        MainArtikel fragment = new MainArtikel();
//        return fragment;
//    }
//
//    public MainArtikel() {
//    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View v = getView();

        listView = (ListView)v.findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), contentData);
        listView.setAdapter(adapter);

       // Bundle getId = Intent.get
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
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
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id_selected = ((TextView) view.findViewById(R.id.id_content)).getText().toString();
               Intent in = new Intent(getActivity(),DetailArtikel.class);
                in.putExtra("id_C",id_selected);
                startActivity(in);
            }
        });

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
