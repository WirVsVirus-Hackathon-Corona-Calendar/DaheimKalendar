package de.garritfra.daheimkalender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import de.garritfra.daheimkalender.model.Challenge;
import de.garritfra.daheimkalender.ui.ChallengeTagebuchFragment;

public class MainActivity extends AppCompatActivity implements ChallengeTagebuchFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChallengeTagebuchFragment fragment = new ChallengeTagebuchFragment();
        fragmentTransaction.add(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Challenge item) {

    }
}
