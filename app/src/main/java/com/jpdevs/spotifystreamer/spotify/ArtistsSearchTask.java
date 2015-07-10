package com.jpdevs.spotifystreamer.spotify;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.jpdevs.spotifystreamer.model.ParcelableArtist;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class ArtistsSearchTask extends AsyncTask<String, Void, List<ParcelableArtist>> {
    public interface ArtistSearchListener {
        void reportSearchResults(ParcelableArtist[] artistsFound);
    }

    private ArtistSearchListener mListener;

    public ArtistsSearchTask(ArtistSearchListener listener) {
        mListener = listener;
    }

    @Override
    protected List<ParcelableArtist> doInBackground(String... params) {
        List<ParcelableArtist> artists = new ArrayList<>();
        SpotifyApi api = new SpotifyApi();

        if(params.length > 0 && !TextUtils.isEmpty(params[0])) {
            ArtistsPager results = api.getService().searchArtists(params[0]);

            for(Artist artist : results.artists.items) {
                artists.add(new ParcelableArtist(artist));
            }
        }

        return artists;
    }

    protected void onPostExecute(List<ParcelableArtist> artistsFound) {
        mListener.reportSearchResults(artistsFound.toArray(new ParcelableArtist[artistsFound.size()]));
    }
}