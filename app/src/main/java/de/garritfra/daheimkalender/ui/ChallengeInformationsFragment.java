package de.garritfra.daheimkalender.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

public class ChallengeInformationsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private Challenge challenge;

    public ChallengeInformationsFragment(Challenge challenge) {
        this.challenge = challenge;
    }

    /*
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChallengeInformationsFragment newInstance(int columnCount) {
        ChallengeInformationsFragment fragment = new ChallengeInformationsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageViewChallenge = view.findViewById(R.id.imageChallenge);

        TextView txt_headlineMaterial =  view.findViewById(R.id.txt_headlineMaterial);
        txt_headlineMaterial.setText(R.string.titel_material);

        TextView txt_material = view.findViewById(R.id.txt_material);
        if (!challenge.getResources().isEmpty()) {
                txt_material.setText(challenge.getResources().first());
        }

        TextView txt_headlineInstruction = view.findViewById(R.id.txt_headlineInstruction);
        txt_headlineInstruction.setText(R.string.titel_instruction);

        TextView txt_instruction =  view.findViewById(R.id.txt_instruction);
        if (!challenge.getResources().isEmpty()) {
            txt_instruction.setText(challenge.getTutorialSteps().first());
        }

        ImageView imageViewInstruction = view.findViewById(R.id.imageInstruction);

    }
}
