package com.jpdevs.spotifystreamer.spotify;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.jpdevs.spotifystreamer.model.ParcelableTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Track;

public class ArtistTopSongsTask extends AsyncTask<String, Void, List<ParcelableTrack>> {
    public interface TopSongsListener {
        void reportTopSongs(ParcelableTrack[] artistsFound);
    }

    private TopSongsListener mListener;

    public ArtistTopSongsTask(TopSongsListener listener) {
        mListener = listener;
    }

    @Override
    protected List<ParcelableTrack> doInBackground(String... params) {
        List<ParcelableTrack> topTracks = new ArrayList<>();
        SpotifyApi api = new SpotifyApi();

        if(params.length > 0 && !TextUtils.isEmpty(params[0])) {
            Map<String, Object> options = new HashMap<>();
            options.put("country", "US");

            List<Track> tracks = api.getService().getArtistTopTrack(params[0], options).tracks;
            for (Track t : tracks) {
                topTracks.add(new ParcelableTrack(t));
            }
        }

        return topTracks;
    }

    protected void onPostExecute(List<ParcelableTrack> topTracks) {
        mListener.reportTopSongs(topTracks.toArray(new ParcelableTrack[topTracks.size()]));
    }
}
