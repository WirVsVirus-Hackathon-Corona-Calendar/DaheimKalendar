package de.garritfra.daheimkalender.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayChallengeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayChallengeFragment extends Fragment {

    public TodayChallengeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TodayChallFragment.
     */
    public static TodayChallengeFragment newInstance() {
        TodayChallengeFragment fragment = new TodayChallengeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_chall, container, false);

        configureView(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        configureView(getView());
    }

    private void configureView(View view) {
        Button startButton = view.findViewById(R.id.button_start);

        Challenge challenge = ChallengeRepository.getInstance().getNextAvailableChallenge();
        final TextView textView = view.findViewById(R.id.txt_today_challenge_title);
        final TextView healineTextView = view.findViewById(R.id.txt_today_challenge_headline);
        final ImageView imageView = view.findViewById(R.id.img_today_chall_bg);

        if (challenge != null) {
            textView.setText(challenge.getTitle());
            ImageStorage.getInstance().getImage(challenge.getIconUrl(), new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    imageView.setImageBitmap(bitMap);
                }
            }, getContext());
        } else {
            healineTextView.setText(R.string.no_challenge_for_today);
            textView.setText(null);
            Drawable placeHolder = getResources().getDrawable(R.drawable.no_challenge_placeholder);
            imageView.setImageDrawable(placeHolder);
            startButton.setVisibility(View.GONE);
        }


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraphicNovel();
            }
        });
    }

    private void openGraphicNovel() {
        Intent intent = new Intent(getContext(), GraphicNovelActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}
