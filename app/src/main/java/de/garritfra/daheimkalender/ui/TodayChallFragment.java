package de.garritfra.daheimkalender.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayChallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayChallFragment extends Fragment {

    public TodayChallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TodayChallFragment.
     */
    public static TodayChallFragment newInstance() {
        TodayChallFragment fragment = new TodayChallFragment();
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

        Button startButton = view.findViewById(R.id.button_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  openGraphicNovel();
                openChallangeInformation();
            }
        });

        return view;
    }

    private void openChallangeInformation(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, new ChallengeInformationsFragment(),"InformationsFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openGraphicNovel() {
        Intent intent = new Intent(getContext(), GraphicNovelActivity.class);
        Bundle bundle = new Bundle();
        //TODO add correct challenged Id here
        bundle.putString(GraphicNovelActivity.challengeId, "aff437cc-97f9-49b7-8efa-a8edf5feed96");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
