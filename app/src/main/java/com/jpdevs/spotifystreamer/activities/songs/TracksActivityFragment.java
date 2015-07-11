package com.jpdevs.spotifystreamer.activities.songs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.model.ParcelableTrack;
import com.jpdevs.spotifystreamer.utils.SimpleDividerItemDecoration;

public class TracksActivityFragment extends Fragment {
    private View mNoTracksContainer;
    private RecyclerView mTracksList;
    private TopTracksAdapter mTracksAdapter;

    public TracksActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        mTracksList = (RecyclerView) rootView.findViewById(R.id.track_list);
        mTracksAdapter = new TopTracksAdapter();
        mTracksList.addItemDecoration(new SimpleDividerItemDecoration(
                getResources().getDrawable(R.drawable.line_divider)));
        mTracksList.setAdapter(mTracksAdapter);
        mTracksList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNoTracksContainer = rootView.findViewById(R.id.no_tracks_container);

        return rootView;
    }

    public void setTracks(ParcelableTrack[] tracks) {
        mTracksAdapter.setTracks(tracks);

        if (tracks.length > 0) {
            mTracksList.setVisibility(View.VISIBLE);
            mNoTracksContainer.setVisibility(View.GONE);
        } else {
            mTracksList.setVisibility(View.GONE);
            mNoTracksContainer.setVisibility(View.VISIBLE);
        }
    }
}
