package com.jpdevs.spotifystreamer.activities.search;


import android.support.v7.widget.SearchView;

public class ArtistQueryListener implements SearchView.OnQueryTextListener {
    public interface SearchQueryListener {
        void performSearch(String query);
    }

    private SearchQueryListener mListener;

    public ArtistQueryListener (SearchQueryListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        mListener.performSearch(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        // TODO check if appropriate based on time
        mListener.performSearch(s);
        return false;
    }
}
