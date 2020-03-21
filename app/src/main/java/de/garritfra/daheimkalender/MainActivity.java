package de.garritfra.daheimkalender;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import de.garritfra.daheimkalender.ui.ChallengeTagebuchFragment;
import de.garritfra.daheimkalender.ui.TodayChallFragment;
import de.garritfra.daheimkalender.ui.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ChallengeTagebuchFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChallengeTagebuchFragment fragmentTagebuch = new ChallengeTagebuchFragment();
        TodayChallFragment fragmentToday = new TodayChallFragment();
        fragmentTransaction.add(R.id.frame, fragmentToday);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.Challenge item) {

    }
}
