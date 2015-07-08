package com.jpdevs.spotifystreamer.spotify;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Track;

public class ArtistTopSongsTask extends AsyncTask<String, Void, List<Track>> {
    private static final String TAG = ArtistsSearchTask.class.getName();
    public interface TopSongsListener {
        void reportTopSongs(List<Track> artistsFound);
    }

    private TopSongsListener mListener;

    public ArtistTopSongsTask(TopSongsListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Track> doInBackground(String... params) {
        List<Track> topTracks;
        SpotifyApi api = new SpotifyApi();

        if(params.length > 0 && !TextUtils.isEmpty(params[0])) {
            Log.i(TAG, "Gonna tracks for: " + params[0]);
            Map<String, Object> options = new HashMap<>();
            options.put("country", "US");

            topTracks = api.getService().getArtistTopTrack(params[0], options).tracks;

            for (Track track : topTracks) {
                Log.i(TAG, "found " + track.name);
            }
        } else {

            topTracks = new ArrayList<>();
            Log.i(TAG, "No Matches ");
        }

        return topTracks;
    }

    protected void onPostExecute(List<Track> topTracks) {
        mListener.reportTopSongs(topTracks);
    }
}
