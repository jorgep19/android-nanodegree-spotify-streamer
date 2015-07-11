package com.jpdevs.spotifystreamer.activities.search;


import android.support.v7.widget.SearchView;

import java.util.Timer;
import java.util.TimerTask;

public class ArtistQueryListener implements SearchView.OnQueryTextListener {
    public interface SearchQueryListener {
        void performSearch(String query);
    }

    private SearchQueryListener mListener;
    private boolean mShouldSearch;
    private String mCurrentString;
    private String mStringFromLastQuery;

    public ArtistQueryListener (SearchQueryListener listener) {
        mShouldSearch = true;
        mListener = listener;
        mCurrentString = "";

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        mListener.performSearch(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mCurrentString  = s;

        // mShouldSearch is a flag to limit the search request to one per second
        if (mShouldSearch) {
            mShouldSearch = false;
            mListener.performSearch(s);
            mStringFromLastQuery = s;

            Timer T = new Timer();
            T.schedule(new TimerTask() {
                @Override
                public void run() {
                    mShouldSearch = true;
                    if(!mCurrentString.equals(mStringFromLastQuery)) {
                        mListener.performSearch(mCurrentString);
                    }
                }
            }, 1000);
        }

        return true;
    }
}
