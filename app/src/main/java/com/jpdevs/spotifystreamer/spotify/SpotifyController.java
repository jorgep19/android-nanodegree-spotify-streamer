package com.jpdevs.spotifystreamer.spotify;

public class SpotifyController {

    public ArtistsSearchTask getArtistsSearchTask(ArtistsSearchTask.ArtistSearchListener listener) {
        return new ArtistsSearchTask(listener);
    }

    public ArtistTopSongsTask geTopTracksTask(ArtistTopSongsTask.TopSongsListener listener) {
        return new ArtistTopSongsTask(listener);
    }
}
