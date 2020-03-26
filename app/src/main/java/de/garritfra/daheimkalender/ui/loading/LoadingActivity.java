package de.garritfra.daheimkalender.ui.loading;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.MainActivity;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoadingActivity extends AppCompatActivity implements ChallengeRepository.OnUpdateFinishedListener {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        ChallengeRepository.getInstance().update(LoadingActivity.this);

        setContentView(R.layout.activity_loading);

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //ProgressBar progressBar = findViewById(R.id.progress_spinner);
        //progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onUpdateFinished() {
        //challenges loaded, loading graphics
        RealmResults<Challenge> result = ChallengeRepository.getInstance().readAll();
        List<String> urls = new LinkedList<>();
        for (Challenge challenge : result) {
            urls.add(challenge.getIconUrl());
            urls.add(challenge.getStoryBeforeImageUrl());
            urls.add(challenge.getStoryAfterImageUrl());
            urls.addAll(challenge.getResources());
        }
        final ProgressBar progressBar = findViewById(R.id.loading_progressBar);
        String[] urlArray = new String[urls.size()];
        urlArray = urls.toArray(urlArray);
        progressBar.setMax(urlArray.length);
        final int imagesTotal = urlArray.length;
        ImageStorage.getInstance().getImages(urlArray, new ImageStorage.ImageLoadingListener() {
            @Override
            public void onElementLoaded(final int loadedImages, Boolean isFinal) {
                Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Loading", "Loaded images: " + loadedImages + "/" + imagesTotal);
                        progressBar.setProgress(loadedImages, true);
                    }
                };
                mainHandler.post(task);
                if (isFinal) {
                    startAfterLoading();
                }
            }
        }, getApplicationContext());
    }

    private void startAfterLoading() {
        Intent i = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
