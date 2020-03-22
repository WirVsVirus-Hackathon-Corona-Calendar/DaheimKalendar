package de.garritfra.daheimkalender.ui.graphicnovel;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

public class GraphicNovelBeforeFragment extends Fragment {

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

        if (getActivity() instanceof GraphicNovelActivity) {
            GraphicNovelActivity activity = (GraphicNovelActivity) getActivity();
            //TODO: Get Challenge from activity.challengeID
        }

        if (mChallenge != null) {
            TextView headline = view.findViewById(R.id.grapicNovelTitle);
            headline.setText(mChallenge.getTitle());
            TextView body = view.findViewById(R.id.graphicNovelBodyText);
            body.setText(mChallenge.getStoryBefore());
            final ImageView scene = view.findViewById(R.id.graphicNovelBackground);
            ImageStorage.getInstance().getImage(mChallenge.getStoryBeforeImageUrl(), new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    scene.setImageBitmap(bitMap);
                }
            }, getContext());
        }

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChallengeRepository.getInstance().setCompleted(mChallenge, true);
                NavHostFragment.findNavController(GraphicNovelBeforeFragment.this)
                        .navigate(R.id.action_story_before_to_challenge);
            }
        });
    }
}
