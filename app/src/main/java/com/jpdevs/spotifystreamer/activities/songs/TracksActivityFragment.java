package com.jpdevs.spotifystreamer.activities.songs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.utils.SimpleDividerItemDecoration;

import kaaes.spotify.webapi.android.models.Track;

public class TracksActivityFragment extends Fragment {

    public TracksActivityFragment() {}

    private TopTracksAdapter mTracksAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        RecyclerView tracksList = (RecyclerView) rootView.findViewById(R.id.track_list);
        tracksList.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getResources().getDrawable(R.drawable.line_divider)));
        tracksList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTracksAdapter = new TopTracksAdapter();
        tracksList.setAdapter(mTracksAdapter);

        return rootView;
    }

    public void setTrackst(Track[] tracks) {
        mTracksAdapter.setTracks(tracks);
    }
}
