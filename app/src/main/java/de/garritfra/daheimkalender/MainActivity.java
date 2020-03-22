package de.garritfra.daheimkalender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.garritfra.daheimkalender.ui.ChallengeHistoryFragment;
import de.garritfra.daheimkalender.ui.TodayChallFragment;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;
import de.garritfra.daheimkalender.ui.onboarding.OnboardingActivity;
import de.garritfra.daheimkalender.ui.settings.SettingsFragment;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);


        requestPermissions();

        final Fragment[] fragments = { new TodayChallFragment(), new ChallengeHistoryFragment(), new SettingsFragment() };

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragments[0]).commit();

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_today:
                        selectedFragment = fragments[0];
                        break;
                    case R.id.nav_history:
                        selectedFragment = fragments[1];
                        break;
                    case R.id.nav_settings:
                        selectedFragment = fragments[2];

                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_handwash, null);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        dialogView.findViewById(R.id.button_na_klar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();

    }

    private void openGraphicNovel() {
        Intent intent = new Intent(this, GraphicNovelActivity.class);
        Bundle bundle = new Bundle();
        //TODO add correct challenged Id here
        bundle.putString(GraphicNovelActivity.challengeId, "aff437cc-97f9-49b7-8efa-a8edf5feed96");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openAgeCheck() {
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    private void requestPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }
}
