package com.example.jamesdeng.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // beware of XSS vulnerabilities
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.addJavascriptInterface(this, "nativeMethod");
        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

    }


    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //in js, call window.AndroidWebView.showInfoFromJs(name), then run this method
        @JavascriptInterface
        public void showInfoFromJs(String nameVal) {
            Toast.makeText(mContext,"Hi "+nameVal+", thanks for using our app!", Toast.LENGTH_SHORT).show();
            userName = nameVal;
        }
    }




    public void onClick_GoToActivity1(View view){
        Intent myIntent = new Intent(this, Activity1.class);
        startActivity(myIntent);
    }

    @JavascriptInterface
    public void toActivity(String activityName) {
        if (userName != null) {
            if (TextUtils.equals(activityName, "zero")) {

                Intent intent = new Intent(MainActivity.this, Activity1.class);
//                Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();
                intent.putExtra("KEY_StringName", userName);
                startActivity(intent);

            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        } else {
            Toast.makeText(this, "You forget to signup", Toast.LENGTH_SHORT).show();
        }
    }
}
