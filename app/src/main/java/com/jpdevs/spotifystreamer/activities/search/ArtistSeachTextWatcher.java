package com.jpdevs.spotifystreamer.activities.search;


import android.text.Editable;
import android.text.TextWatcher;

public class ArtistSeachTextWatcher implements TextWatcher {
    public interface SearchQueryListener {
        void performSearch(String query);
    }

    private SearchQueryListener mListener;

    public ArtistSeachTextWatcher (SearchQueryListener listener) {
        mListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO check if appropriate based on time
        mListener.performSearch(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
