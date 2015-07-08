package com.jpdevs.spotifystreamer.spotify;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
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
        List<Artist> artists;
        SpotifyApi api = new SpotifyApi();

        if(params.length > 0 && !TextUtils.isEmpty(params[0])) {
            ArtistsPager results = api.getService().searchArtists(params[0]);
            artists = results.artists.items;

            for (Artist a : artists) {
                Log.i(TAG, "found " + a.name);
            }
        } else {

            artists = new ArrayList<>();
            Log.i(TAG, "No Matches ");
        }

        return artists;
    }

    protected void onPostExecute(List<Artist> artistsFound) {
        mListener.reportSearchResults(artistsFound);
    }
}