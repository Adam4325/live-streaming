package com.adam.iptv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import io.vov.vitamio.R;
public class MainActivity extends Activity {

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.bg};
    public static String [] prgmNameList={"Majelis yang didirikan oleh Habib Munzir bin Fuad bin Abdurrahman Al-Musawa (Sulthonul Qulub). Materi yang disampaikan adalah akhlak dan kelembutan nabi yang terangkum dalam hadits Shahih Bukhari, agar para jamaah hidup dalam keharmonisan antar sesama, dan semakin mencintai Allah dan Rasulnya.\\n\n" +
            "        jadwal live Streaming hanya pada saat acara berlangsung!"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_home);
        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));
    }
}
