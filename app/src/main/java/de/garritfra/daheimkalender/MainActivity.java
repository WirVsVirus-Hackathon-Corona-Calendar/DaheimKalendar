package de.garritfra.daheimkalender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import android.view.View;
import android.widget.Button;

import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;

import de.garritfra.daheimkalender.ui.onboarding.OnboardingActivity;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);

        Button openGraphicNovelBtn = findViewById(R.id.btn_open_graphic_novel);
        openGraphicNovelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGraphicNovel();
            }
        });

        Button openAgeCheckBtn = findViewById(R.id.btn_open_age_check);
        openAgeCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgeCheck();
            }
        });
    }

    private void openGraphicNovel() {
        Intent intent = new Intent(this, GraphicNovelActivity.class);
        Bundle bundle = new Bundle();
        //TODO add correct challenged Id here
        bundle.putInt(GraphicNovelActivity.challengeId, -1);
        intent.putExtras(bundle);
        startActivity(intent);



    }

    private void openAgeCheck() {
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

}
