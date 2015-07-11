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
    private TopTracksAdapter mTracksAdapter;

    public TracksActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        RecyclerView tracksList = (RecyclerView) rootView.findViewById(R.id.track_list);
        mTracksAdapter = new TopTracksAdapter();
        tracksList.addItemDecoration(new SimpleDividerItemDecoration(
                getResources().getDrawable(R.drawable.line_divider)));
        tracksList.setAdapter(mTracksAdapter);
        tracksList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    public void setTracks(ParcelableTrack[] tracks) {
        mTracksAdapter.setTracks(tracks);
    }
}
