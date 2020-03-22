package de.garritfra.daheimkalender.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;

public class CompletedChallengeFragment extends Fragment {

    private Challenge mChallenge;

    public CompletedChallengeFragment() {
        super();
    }

    public CompletedChallengeFragment(Challenge challenge) {
        super();
        this.mChallenge = challenge;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_challenge_completed, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
