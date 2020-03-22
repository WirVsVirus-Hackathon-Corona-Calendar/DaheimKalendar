package de.garritfra.daheimkalender.ui.loading;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

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
    }

    @Override
    public void onUpdateFinished() {
        //challenges loaded, loading graphics
        RealmResults<Challenge> result = ChallengeRepository.getInstance().readAll();
        Iterator<Challenge> it = result.iterator();
        List<String> urls = new LinkedList<>();
        while (it.hasNext()) {
            Challenge challenge = it.next();
            urls.add(challenge.getIconUrl());
        }
        String[] urlArray = new String[urls.size()];
        urlArray = urls.toArray(urlArray);
        ImageStorage.getInstance().getImages(urlArray, new ImageStorage.ImageStorageListener() {
            @Override
            public void onImageLoaded(Bitmap bitMap) {
                startAfterLoading();
            }
        }, getApplicationContext());
    }

    private void startAfterLoading() {
        Intent i = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
