package com.adam.iptv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.Toast;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.R;
import io.vov.vitamio.widget.MediaController;

public class Navbarhome extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static io.vov.vitamio.widget.VideoView mVideoView;
    private static String path =  "rtmp://116.50.26.194/live/fathimiyah";

    //private static  String path  = "rtmp://edge.okeinfo.net/live/mncoke1a_128.stream";

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbarhome);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = new Fragment();
        fragment=null;

        switch (position){
            case 0:
                fragment = new MainArtikel();
                break;

            case 1:
                //fragment = new VideoRTMP();
                Intent inss = new Intent(Navbarhome.this,VideoRTMP.class);
                startActivity(inss);
                break;

            case 2:

                fragment = new MainArtikel();
                break;

            case 3:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, VideoViewRTMP.newInstance(position + 1))
//                        .commit();
                break;

            case 4:
                Intent in = new Intent(Navbarhome.this,WebViewMajelis.class);
                in.putExtra("url","http://majelisfathimiyah.org/forum");
                startActivity(in);
                break;

            case 5:
                Intent ins = new Intent(Navbarhome.this,WebViewMajelis.class);
                ins.putExtra("url","http://toko.majelisfathimiyah.org/");
                startActivity(ins);
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManagers = getSupportFragmentManager();
            fragmentManagers.beginTransaction()
                    .replace(R.id.container, fragment).commit();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.navbarhome, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_navbarhome, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Navbarhome) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    public static class VideoViewRTMP extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number2";

        public static VideoViewRTMP newInstance(int sectionNumber) {
            VideoViewRTMP fragment = new VideoViewRTMP();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public VideoViewRTMP() {
        }
        private Button playV,openV;
        private SurfaceHolder holder;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main, container, false);


            if (!LibsChecker.checkVitamioLibs(getActivity())) return rootView;

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setMessage("Streaming akan delay 10 detik atau lebih, jika belum tampil kembali tekan menu live Streaming");
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();
            mVideoView = (io.vov.vitamio.widget.VideoView) rootView.findViewById(R.id.surface_view);
            if (path == "") {
                Toast.makeText(getActivity(), "Please set the video path for your media file", Toast.LENGTH_LONG).show();
                return rootView;
            } else {


                mVideoView.setVideoPath(path);
                mVideoView.setMediaController(new MediaController(getActivity()));
                mVideoView.requestFocus();

                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        // optional need Vitamio 4.0
                        //mediaPlayer.setPlaybackSpeed(1.0f);
                    }
                });
            }
            return rootView;

        }

        public void startPlay(View view) {
            if (!TextUtils.isEmpty(path)) {
                mVideoView.setVideoPath(path);
            }
        }

        public void openVideo(View View) {
            mVideoView.setVideoPath(path);
        }
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((Navbarhome) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
    }

}
