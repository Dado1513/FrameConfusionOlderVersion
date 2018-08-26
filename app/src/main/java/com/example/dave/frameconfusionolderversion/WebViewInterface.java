package com.example.dave.frameconfusionolderversion;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.Console;

public class WebViewInterface  {
    Context mContext;
    WebView webView;

    /** Instantiate the interface and set the context */
    WebViewInterface(Context c, WebView web) {
        mContext = c;
        webView = web;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(final String test) {
        Toast.makeText(mContext, test, Toast.LENGTH_LONG).show();
        webView.post(new Runnable() {
            @Override
            public void run() {

                // frame confusion --> il codice viene eseguto nel frame principale nonostante a chiamarlo sia stato l'iframe
                // infatti document.domain --> è quello dell'iframe principale
                // problema se viene eseguito evaluateJavaScript con altro codice perchè potrebbe chiamare
                // plugin/ecc dando accesso a problemi di sicurezza
                //webView.loadData("", "text/html", null);
                webView.loadUrl("javascript: (function(){document.getElementById('testUpper').innerHTML = document.domain; })()", null);
                //webView.loadUrl("file:///android_asset/index.html");
                Log.e(WebViewInterface.class.getName(),"call ShowToast from "+test);
            }
        });
    }
}
