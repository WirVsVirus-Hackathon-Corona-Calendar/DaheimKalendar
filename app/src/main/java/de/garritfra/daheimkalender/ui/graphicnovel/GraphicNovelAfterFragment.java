package de.garritfra.daheimkalender.ui.graphicnovel;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

public class GraphicNovelAfterFragment extends Fragment {

    private Challenge mChallenge;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        if (getActivity() instanceof GraphicNovelActivity) {
            mChallenge = ((GraphicNovelActivity) getActivity()).challenge;
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graphic_novel_challenge, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mChallenge != null) {
            ScrollView scrollView = view.findViewById(R.id.graphicNovelBody);
            scrollView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.animation_fadein));
            TextView body = view.findViewById(R.id.graphicNovelBodyText);
            body.setText(mChallenge.getStoryAfter());
            final ImageView imageView = view.findViewById(R.id.graphicNovelBackground);
            ImageStorage.getInstance().getImage(mChallenge.getStoryAfterImageUrl(), new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    imageView.setImageBitmap(bitMap);
                }
            }, getContext());
            //TODO: check for further details to add
        }

        ((Button) view.findViewById(R.id.button_first)).setText(R.string.continue_button);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChallengeRepository.getInstance().setCompleted(mChallenge, true);
                getActivity().finish();
            }
        });
    }
}
