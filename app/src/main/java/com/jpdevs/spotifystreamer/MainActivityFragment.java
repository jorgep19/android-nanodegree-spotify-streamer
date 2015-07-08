package com.jpdevs.spotifystreamer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivityFragment extends Fragment {

    private ImageView mNoResults;
    private RecyclerView mSearchResults;


    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        EditText searchBox = (EditText) rootView.findViewById(R.id.search_box);
        mNoResults = (ImageView) rootView.findViewById(R.id.no_results);
        mSearchResults = (RecyclerView) rootView.findViewById(R.id.search_results);
        mSearchResults.setAdapter(new ArtistsSearchAdapter());

        searchBox.addTextChangedListener(new ArtistSeachTextWatcher());

        return rootView;
    }
}
