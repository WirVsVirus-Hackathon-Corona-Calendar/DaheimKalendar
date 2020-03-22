package de.garritfra.daheimkalender.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.garritfra.daheimkalender.ImageStorage;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;
import de.garritfra.daheimkalender.ui.ChallengeHistoryFragment.OnListFragmentInteractionListener;
import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;

import static java.security.AccessController.getContext;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Challenge} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ChallengeRecyclerViewAdapter extends RecyclerView.Adapter<ChallengeRecyclerViewAdapter.ViewHolder> {

    private final List<Challenge> mValues;
    private final OnListFragmentInteractionListener mListener;

    private DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());

    public ChallengeRecyclerViewAdapter(List<Challenge> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_challenge_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mItem = mValues.get(position);
        Challenge challenge = mValues.get(position);
        if (challenge.getCompleted()) {
            holder.mView.findViewById(R.id.img_challenge_tile_background).setVisibility(View.VISIBLE);
        } else {
            holder.mView.findViewById(R.id.img_challenge_tile_background).setVisibility(View.GONE);
        }

        String imagePath = challenge.getImagePath();
        if (imagePath != null) {
            final ImageView imageView = holder.mView.findViewById(R.id.img_challenge_tile_background);
            ImageStorage.getInstance().getImage(imagePath, new ImageStorage.ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    imageView.setImageBitmap(bitMap);
                }
            }, holder.mView.getContext());
        }
        holder.mTitleView.setText(challenge.getTitle() != null ? challenge.getTitle(): "" + position);
        holder.mItem = challenge;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public Challenge mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.item_title);
        }
    }
}
