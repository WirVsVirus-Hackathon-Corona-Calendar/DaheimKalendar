package de.garritfra.daheimkalender.ui.loading;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.MainActivity;
import de.garritfra.daheimkalender.R;
import io.realm.Realm;

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
        Intent i = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
