package com.jpdevs.spotifystreamer.spotify;

public class SpotifyController {
    private static final String TAG = SpotifyController.class.getName();

    public ArtistsSearchTask getArtistsSearchTask(ArtistsSearchTask.ArtistSearchListener listener) {
        return new ArtistsSearchTask(listener);
    }

    public ArtistTopSongsTask getArtistsSearchTask(ArtistTopSongsTask.TopSongsListener listener) {
        return new ArtistTopSongsTask(listener);
    }
}
