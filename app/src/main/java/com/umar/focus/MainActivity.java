package com.umar.focus;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setStatusBarColor(Color.rgb(5, 6, 8));
        window.setNavigationBarColor(Color.rgb(5, 6, 8));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(5, 6, 8));

        webView.setOnApplyWindowInsetsListener((view, insets) -> {

            android.graphics.Insets systemBars =
                    insets.getInsets(
                            WindowInsets.Type.statusBars()
                                    | WindowInsets.Type.navigationBars()
                                    | WindowInsets.Type.displayCutout()
                    );

            view.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient());

        setContentView(webView);

        webView.requestApplyInsets();

        if (savedInstanceState == null) {
            webView.loadUrl("file:///android_asset/index.html");
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
protected void onSaveInstanceState(Bundle outState) {
    webView.saveState(outState);
    super.onSaveInstanceState(outState);
}
}
   
