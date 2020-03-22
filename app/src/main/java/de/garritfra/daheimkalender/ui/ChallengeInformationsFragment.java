package de.garritfra.daheimkalender.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

import static android.app.Activity.RESULT_OK;

public class ChallengeInformationsFragment extends Fragment {


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mChallengeId;
    TextView txt_material1, txt_material2, txt_material3, txt_material4, txt_material5, txt_instruction1, txt_instruction2, txt_instruction3, txt_instruction4, txt_instruction5;

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

        final ImageView imageViewChallenge = view.findViewById(R.id.imageChallenge);

        ImageStorage.getInstance().getImage(challenge.getIconUrl(), new ImageStorage.ImageStorageListener() {
            @Override
            public void onImageLoaded(Bitmap bitMap) {
                imageViewChallenge.setImageBitmap(bitMap);
            }
        }, getContext());

        txt_material1 = view.findViewById(R.id.txt_material1);
        txt_material2 = view.findViewById(R.id.txt_material2);
        txt_material3 = view.findViewById(R.id.txt_material3);
        txt_material4 = view.findViewById(R.id.txt_material4);
        txt_material5 = view.findViewById(R.id.txt_material5);

        txt_instruction1 = view.findViewById(R.id.txt_instruction1);
        txt_instruction2 = view.findViewById(R.id.txt_instruction2);
        txt_instruction3 = view.findViewById(R.id.txt_instruction3);
        txt_instruction4 = view.findViewById(R.id.txt_instruction4);
        txt_instruction5 = view.findViewById(R.id.txt_instruction5);

        TextView txt_headlineMaterial =  view.findViewById(R.id.txt_headlineMaterial);
        txt_headlineMaterial.setText(R.string.titel_material);

        setupResourceFields(challenge.getMaterials());
        setupInstructionFields(challenge.getMaterials());

        TextView txt_headlineInstruction = view.findViewById(R.id.txt_headlineInstruction);
        txt_headlineInstruction.setText(R.string.titel_instruction);

        final ImageView imageViewInstruction = view.findViewById(R.id.imageInstruction);

        String resourceUrl = challenge.getResources().first();

        if (resourceUrl != null) {
            imageViewInstruction.setVisibility(View.VISIBLE);
            ImageStorage.getInstance().getImage(resourceUrl, new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    imageViewInstruction.setImageBitmap(bitMap);
                }
            }, getContext());
        } else {
            imageViewInstruction.setVisibility(View.GONE);
        }

        Button btnComplete = view.findViewById(R.id.btn_challenge_complete);

        btnComplete.setText(R.string.btn_complete_title);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ChallengeRepository.getInstance().setCompleted(challenge, true);
                ChallengeRepository.getInstance().setCompleted(challenge, true);
                mChallengeId = challenge.getId();
                dispatchTakePictureIntent();
                //TODO: move to done fragment
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (mChallengeId != null) {
                Challenge challenge = ChallengeRepository.getInstance().readOneById(mChallengeId);
                String path = ImageStorage.getInstance().storeChallengeImage(challenge.getId(), imageBitmap, getContext());
                ChallengeRepository.getInstance().setImagePath(challenge, path);
            }
        }
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void setupResourceFields(List<String> materials) {
        switch (materials.size()) {
            case 0:
                txt_material1.setVisibility(View.GONE);
                txt_material2.setVisibility(View.GONE);
                txt_material3.setVisibility(View.GONE);
                txt_material4.setVisibility(View.GONE);
                txt_material5.setVisibility(View.GONE);
                break;
            case 1:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0).toString());
                txt_material2.setVisibility(View.GONE);
                txt_material3.setVisibility(View.GONE);
                txt_material4.setVisibility(View.GONE);
                txt_material5.setVisibility(View.GONE);
                break;
            case 2:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0).toString());
                txt_material2.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(1).toString());
                txt_material3.setVisibility(View.GONE);
                txt_material4.setVisibility(View.GONE);
                txt_material5.setVisibility(View.GONE);
                break;
            case 3:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0).toString());
                txt_material2.setVisibility(View.VISIBLE);
                txt_material2.setText(materials.get(1).toString());
                txt_material3.setVisibility(View.VISIBLE);
                txt_material3.setText(materials.get(2).toString());
                txt_material4.setVisibility(View.GONE);
                txt_material5.setVisibility(View.GONE);
                break;
            case 4:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.VISIBLE);
                txt_material2.setText(materials.get(1));
                txt_material3.setVisibility(View.VISIBLE);
                txt_material3.setText(materials.get(2));
                txt_material4.setVisibility(View.VISIBLE);
                txt_material4.setText(materials.get(3));
                txt_material5.setVisibility(View.GONE);
                break;
            case 5:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.VISIBLE);
                txt_material2.setText(materials.get(1));
                txt_material3.setVisibility(View.VISIBLE);
                txt_material3.setText(materials.get(2));
                txt_material4.setVisibility(View.VISIBLE);
                txt_material4.setText(materials.get(3));
                txt_material5.setVisibility(View.VISIBLE);
                txt_material5.setText(materials.get(4));
                break;
        }
    }

    private void setupInstructionFields(List<String> instructions) {
        switch (instructions.size()) {
            case 0:
                txt_instruction1.setVisibility(View.GONE);
                txt_instruction2.setVisibility(View.GONE);
                txt_instruction3.setVisibility(View.GONE);
                txt_instruction4.setVisibility(View.GONE);
                txt_instruction5.setVisibility(View.GONE);
                break;
            case 1:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.GONE);
                txt_instruction3.setVisibility(View.GONE);
                txt_instruction4.setVisibility(View.GONE);
                txt_instruction5.setVisibility(View.GONE);
                break;
            case 2:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.GONE);
                txt_instruction4.setVisibility(View.GONE);
                txt_instruction5.setVisibility(View.GONE);
                break;
            case 3:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.GONE);
                txt_instruction5.setVisibility(View.GONE);
                break;
            case 4:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.VISIBLE);
                txt_instruction4.setText(instructions.get(3));
                txt_instruction5.setVisibility(View.GONE);
                break;
            case 5:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.VISIBLE);
                txt_instruction4.setText(instructions.get(3));
                txt_instruction5.setVisibility(View.VISIBLE);
                txt_instruction5.setText(instructions.get(4));
                break;
        }
    }
}
