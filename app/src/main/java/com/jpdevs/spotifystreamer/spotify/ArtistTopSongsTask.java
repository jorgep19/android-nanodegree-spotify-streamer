package com.jpdevs.spotifystreamer.spotify;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
            topTracks = api.getService().getArtistTopTrack(params[0]).tracks;

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
