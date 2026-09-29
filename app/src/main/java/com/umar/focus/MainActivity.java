package com.umar.focus;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Insets;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        );
        window.setStatusBarColor(Color.rgb(5, 6, 8));
        window.setNavigationBarColor(Color.rgb(5, 6, 8));

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(5, 6, 8));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(5, 6, 8));

        FrameLayout.LayoutParams webParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );

        root.addView(webView, webParams);

        root.setOnApplyWindowInsetsListener((view, insets) -> {

            Insets systemBars = insets.getInsets(
                    WindowInsets.Type.statusBars()
                            | WindowInsets.Type.navigationBars()
                            | WindowInsets.Type.displayCutout()
            );

            FrameLayout.LayoutParams params =
                    (FrameLayout.LayoutParams) webView.getLayoutParams();

            params.leftMargin = systemBars.left;
            params.topMargin = systemBars.top;
            params.rightMargin = systemBars.right;
            params.bottomMargin = systemBars.bottom;

            webView.setLayoutParams(params);

            return insets;
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient());

        setContentView(root);

        root.requestApplyInsets();

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
