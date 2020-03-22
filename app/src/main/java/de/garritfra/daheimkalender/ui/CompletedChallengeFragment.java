package de.garritfra.daheimkalender.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.makeramen.roundedimageview.RoundedImageView;

import de.garritfra.daheimkalender.ImageStorage;
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
        return inflater.inflate(R.layout.fragment_challenge_completed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleTextView = view.findViewById(R.id.completed_title_text_view);
        TextView challengeTitleTextView = view.findViewById(R.id.completed_challenge_title_text_view);
        TextView challengeDescriptionTextView = view.findViewById(R.id.completed_challenge_description_text_view);
        final RoundedImageView roundedImageView = view.findViewById(R.id.completed_challenge_rounded_image_view);

        challengeTitleTextView.setText(getString(R.string.challenge) + " "+ mChallenge.getOrder() + ": " + mChallenge.getTitle());
        if (mChallenge.getImagePath() != null) {
            ImageStorage.getInstance().getImage(mChallenge.getImagePath(), new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    roundedImageView.setImageBitmap(bitMap);
                }
            }, getContext());
        } else {
            Drawable placeHolder = getResources().getDrawable(R.drawable.under_sea_animals);
            roundedImageView.setImageDrawable(placeHolder);
        }
    }
}
