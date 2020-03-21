package de.garritfra.daheimkalender;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.garritfra.daheimkalender.ui.ChallengeHistoryFragment;
import de.garritfra.daheimkalender.ui.TodayChallFragment;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;
import de.garritfra.daheimkalender.ui.onboarding.OnboardingActivity;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);

        final Fragment[] fragments = { new TodayChallFragment(), new ChallengeHistoryFragment() };

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
                        //TODO: navigate to settings
                        // selectedFragment = new SettingsFragment()

                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                } else {
                    return false;
                }
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
