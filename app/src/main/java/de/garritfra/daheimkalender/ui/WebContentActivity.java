package de.garritfra.daheimkalender.ui;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import de.garritfra.daheimkalender.R;

public class WebContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_content);

        String filename = getIntent().getStringExtra("filename");

        WebView webView = findViewById(R.id.view_web_content);


        webView.loadUrl("file:///android_asset/" + filename);
    }
}
