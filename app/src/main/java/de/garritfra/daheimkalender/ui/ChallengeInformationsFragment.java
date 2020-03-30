package de.garritfra.daheimkalender.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;

import static android.app.Activity.RESULT_OK;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ChallengeInformationsFragment extends Fragment {


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mChallengeId;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private Challenge challenge;

    public ChallengeInformationsFragment() {
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

        if (getActivity() instanceof GraphicNovelActivity) {
            challenge = ((GraphicNovelActivity) getActivity()).challenge;
        }

        final ImageView imageViewChallenge = view.findViewById(R.id.imageChallenge);

        ImageStorage.getInstance().getImage(challenge.getIconUrl(), new ImageStorage.ImageStorageListener() {
            @Override
            public void onImageLoaded(Bitmap bitMap) {
                imageViewChallenge.setImageBitmap(bitMap);
            }
        }, getContext());

        TextView txt_headlineMaterial =  view.findViewById(R.id.txt_headlineMaterial);
        txt_headlineMaterial.setText(R.string.titel_material);
        LinearLayout materialLayout = view.findViewById(R.id.materialLayout);

        for (String material : challenge.getMaterials()) {
            TextView materialTextView = new TextView(getContext());
            materialTextView.setText(material);
            materialTextView.setWidth(MATCH_PARENT);
            materialTextView.setHeight(MATCH_PARENT);
            materialTextView.setMinHeight(getResources().getDimensionPixelSize(R.dimen.challenge_field_min_height));
            materialTextView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.graphic_novel_body_background));
            materialTextView.setGravity(CENTER_VERTICAL);
            materialTextView.setPadding(getResources().getDimensionPixelSize(R.dimen.challenge_field_padding_left), 0, 0, 0);

            materialLayout.addView(materialTextView);
        }

        LinearLayout instructionsLayout = view.findViewById(R.id.instructionsLayout);

        for (String instruction : challenge.getTutorialSteps()) {
            TextView instructionTextView = new TextView(getContext());
            instructionTextView.setText(instruction);
            instructionTextView.setWidth(MATCH_PARENT);
            instructionTextView.setHeight(MATCH_PARENT);
            instructionTextView.setMinHeight(getResources().getDimensionPixelSize(R.dimen.challenge_field_min_height));
            instructionTextView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.graphic_novel_body_background));
            instructionTextView.setGravity(CENTER_VERTICAL);
            instructionTextView.setPadding(getResources().getDimensionPixelSize(R.dimen.challenge_field_padding_left), 0, 0, 0);

            instructionsLayout.addView(instructionTextView);
        }

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
                showTakePhotoDialog();
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
                String path = ImageStorage.getInstance().storeChallengeImage(imageBitmap, getContext());
                ChallengeRepository.getInstance().setImagePath(challenge, path);
            }

            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_challenge_to_story_after);
        }
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void showTakePhotoDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_photo, null);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        try {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        dialogView.findViewById(R.id.button_take_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                dispatchTakePictureIntent();
            }
        });

        alertDialog.show();
    }

}
