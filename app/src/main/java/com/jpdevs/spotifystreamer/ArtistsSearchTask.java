package com.jpdevs.spotifystreamer;


import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class ArtistsSearchTask extends AsyncTask<String, Void, List<Artist>> {
    private static final String TAG = ArtistsSearchTask.class.getName();
    public interface ArtistSearchListener {
        void reportSearchResults(List<Artist> artistsFound);
    }

    private ArtistSearchListener mListener;

    public ArtistsSearchTask(ArtistSearchListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Artist> doInBackground(String... params) {
        SpotifyApi api = new SpotifyApi();

        ArtistsPager results = api.getService().searchArtists("foo fighters");
        List<Artist> artists = results.artists.items;

        for (Artist a : artists) {
            Log.i(TAG, "found " + a.name);
        }

        return artists;
    }

    protected void onPostExecute(List<Artist> artistsFound) {
        mListener.reportSearchResults(artistsFound);
    }
}