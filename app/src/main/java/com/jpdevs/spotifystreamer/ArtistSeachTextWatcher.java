package com.jpdevs.spotifystreamer;


import android.text.Editable;
import android.text.TextWatcher;

public class ArtistSeachTextWatcher implements TextWatcher {
    public interface SearchQueryListener {
        void performSearch(String query);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {}
}
