package com.jpdevs.spotifystreamer.activities.songs;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.model.ParcelableTrack;
import com.squareup.picasso.Picasso;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TrackViewHolder> {
    private ParcelableTrack[] mTracks;

    public TopTracksAdapter() {
        mTracks = new ParcelableTrack[0];
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_track, parent, false);

        return  new TrackViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder viewHolder, int i) {
        ParcelableTrack track = mTracks[i];
        viewHolder.mTVTrackName.setText(track.getName());
        if(!TextUtils.isEmpty(track.getAlbumIconUrl())) {
            Picasso.with(viewHolder.mAlbumImg.getContext())
                    .load(track.getAlbumIconUrl())
                    .into(viewHolder.mAlbumImg);
        } else {
            viewHolder.mAlbumImg.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public int getItemCount() {
        return mTracks.length;
    }

    public void setTracks(ParcelableTrack[] tracks) {
        mTracks = tracks;
        notifyDataSetChanged();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAlbumImg;
        public TextView mTVTrackName;

        public TrackViewHolder(View rootView) {
            super(rootView);
            mAlbumImg = (ImageView) rootView.findViewById(R.id.album_img);
            mTVTrackName = (TextView) rootView.findViewById(R.id.track_name);
        }
    }
}