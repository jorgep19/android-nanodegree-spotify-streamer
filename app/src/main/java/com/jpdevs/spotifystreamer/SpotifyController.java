package com.jpdevs.spotifystreamer;

public class SpotifyController {
    private static final String TAG = SpotifyController.class.getName();

    public ArtistsSearchTask getArtistsSearchTask(ArtistsSearchTask.ArtistSearchListener listener) {
        return new ArtistsSearchTask(listener);
    }
}
