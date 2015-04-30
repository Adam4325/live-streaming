package com.adam.iptv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;

import io.vov.vitamio.R;

/**
 * Created by adam on 2/7/2015.
 */


public class WebViewMajelis extends Activity {

    private WebView webView;
    private ProgressDialog dialog ;
    ImageButton imgBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_);
        webView = (WebView) findViewById(R.id.webView1);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        Bundle extras = getIntent().getExtras();
        String url=extras.getString("url");

        if (savedInstanceState != null){
            ((WebView) findViewById(R.id.webView1)).restoreState(savedInstanceState);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inBack = new Intent(WebViewMajelis.this,Navbarhome.class);
                startActivity(inBack);
                finish();
            }
        });

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..Please wait.");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        webView.loadUrl(url);



        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}
