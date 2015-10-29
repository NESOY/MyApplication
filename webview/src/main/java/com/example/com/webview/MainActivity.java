package com.example.com.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText URL;
    Button btnGo,btnBack;
    WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL = (EditText) findViewById(R.id.URL);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnBack = (Button) findViewById(R.id.btnBack);
        webView1 = (WebView) findViewById(R.id.webView1);

        webView1.setWebViewClient(new CookWebViewClient());
        WebSettings webset = webView1.getSettings();
        webset.setBuiltInZoomControls(true);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!URL.getText().toString().contains("http://"))
                    URL.setText(URL.getText().insert(0, "http://"));

                webView1.loadUrl(URL.getText().toString());

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView1.goBack();
            }
        });

    }
   class CookWebViewClient extends WebViewClient{
       @Override
       public boolean shouldOverrideUrlLoading(WebView view, String url) {

           return super.shouldOverrideUrlLoading(view,url);
       }

       @Override
       public void onPageFinished(WebView view, String url) {
           super.onPageFinished(view, url);
           URL.setText(view.getUrl());
       }
   }
}
