package com.jpdevs.spotifystreamer.activities.search;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jpdevs.spotifystreamer.spotify.ArtistsSearchTask;
import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

public class MainActivityFragment extends Fragment {

    private ImageView mNoResults;
    private RecyclerView mSearchResults;
    private ArtistsSearchAdapter mSearchAdapter;
    private SpotifyController mSpotifyController;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        SearchView searchBox = (SearchView) rootView.findViewById(R.id.search_box);
        mNoResults = (ImageView) rootView.findViewById(R.id.no_results);
        mSearchResults = (RecyclerView) rootView.findViewById(R.id.search_results);
        mSearchResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchAdapter = new ArtistsSearchAdapter();
        mSearchResults.setAdapter(mSearchAdapter);
        mSpotifyController = new SpotifyController();

        searchBox.setOnQueryTextListener(new ArtistQueryListener(new ArtistQueryListener.SearchQueryListener() {
            @Override
            public void performSearch(String query) {
                mSpotifyController.getArtistsSearchTask(new ArtistsSearchTask.ArtistSearchListener() {
                    @Override
                    public void reportSearchResults(List<Artist> artistsFound) {
                        if (artistsFound.size() > 0) {
                            mNoResults.setVisibility(View.GONE);
                            mSearchResults.setVisibility(View.VISIBLE);

                            mSearchAdapter.updateArtists(artistsFound);
                        } else {
                            mNoResults.setVisibility(View.VISIBLE);
                            mSearchResults.setVisibility(View.GONE);
                        }
                    }
                }).execute(query);
            }
        }));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mNoResults = null;
        mSearchResults = null;
        mSearchAdapter = null;
    }
}
