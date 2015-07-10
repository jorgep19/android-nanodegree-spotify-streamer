package com.jpdevs.spotifystreamer.activities.songs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpdevs.spotifystreamer.R;

import kaaes.spotify.webapi.android.models.Track;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TrackViewHolder> {
    private Track[] mTracks;

    public TopTracksAdapter() {
        mTracks = new Track[0];
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_track, parent, false);

        return  new TrackViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder viewHolder, int i) {
        Track track = mTracks[i];
        viewHolder.mTVTrackName.setText(track.name);
    }

    @Override
    public int getItemCount() {
        return mTracks.length;
    }

    public void setTracks(Track[] tracks) {
        mTracks = tracks;
        notifyDataSetChanged();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        public TextView mTVTrackName;

        public TrackViewHolder(View rootView) {
            super(rootView);
            mTVTrackName = (TextView) rootView.findViewById(R.id.track_name);
        }
    }
}