package com.jpdevs.spotifystreamer;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class SpotifyController {
    private static final String TAG = SpotifyController.class.getName();

    public SearchSpotifyTask getArtistsSearchTask() {
        return new SearchSpotifyTask();
    }

    public static class SearchSpotifyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SpotifyApi api = new SpotifyApi();

            ArtistsPager results = api.getService().searchArtists("foo fighters");
            List<Artist> artists = results.artists.items;

            for (Artist a : artists) {
                Log.i(TAG, "found " + a.name);
            }

            return null;
        }
    }
}
