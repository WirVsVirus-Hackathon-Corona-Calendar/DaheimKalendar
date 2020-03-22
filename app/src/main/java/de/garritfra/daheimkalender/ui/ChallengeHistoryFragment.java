package de.garritfra.daheimkalender.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import java.util.LinkedList;
import java.util.List;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Sort;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ChallengeHistoryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private OnListFragmentInteractionListener mListener;
    boolean mOld = false;
    private ConstraintSet constraintSet1;
    private ConstraintSet constraintSet2;
    private ConstraintLayout constraintLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeHistoryFragment() {
        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Challenge item) {
                CompletedChallengeFragment fragment = new CompletedChallengeFragment(item);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        };
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChallengeHistoryFragment newInstance(int columnCount) {
        ChallengeHistoryFragment fragment = new ChallengeHistoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_history, container, false);

        constraintLayout = view.findViewById(R.id.constraint);
        //Animations Test Zeug
        final ConstraintLayout mConstraintLayout = view.findViewById(R.id.constraint);
         mOld = false;
        constraintSet1 = new ConstraintSet();
        constraintSet1.clone(getActivity(), R.layout.fragment_challenge_history);

        List challenges;
        challenges = ChallengeRepository.getInstance().readAll().sort("order", Sort.ASCENDING);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        mColumnCount = (int) (dpWidth / 92f);

        // Set the adapter
            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.list);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ChallengeRecyclerViewAdapter(challenges, mListener));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name

        void onListFragmentInteraction(Challenge item);
    }

    /*
    class MarginItemDecoration extends RecyclerView.ItemDecoration {

        private int scpaceHeight;

        MarginItemDecoration(int spaceHeight) {
            this.scpaceHeight = spaceHeight;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = scpaceHeight;
            }
            outRect.bottom = scpaceHeight;
            outRect.left = scpaceHeight;
            outRect.right = scpaceHeight;
        }

    }
    */
}
