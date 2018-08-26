package com.example.dave.frameconfusionolderversion;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    public static WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        webView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewInterface webViewInterface = new WebViewInterface(this, webView);
        webView.addJavascriptInterface(webViewInterface, "Android");
        webView.loadUrl("file:///android_asset/index.html");
    }
}