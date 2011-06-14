package com.xinchejian.android.intro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ShowWebPage extends Activity {
    private WebView webView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        webView = (WebView) findViewById(R.id.webkitWebView);
        //webView.loadUrl("file:/android_asset/helloworld.html");
        webView.loadUrl("http://www.google.com");
    }
}