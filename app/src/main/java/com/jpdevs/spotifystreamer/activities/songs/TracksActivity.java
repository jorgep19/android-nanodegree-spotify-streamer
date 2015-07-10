package com.jpdevs.spotifystreamer.activities.songs;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.spotify.ArtistTopSongsTask;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class TracksActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST_ID = "artist_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        Intent intent = getIntent();
        String artistId = intent.getStringExtra(EXTRA_ARTIST_ID);
        new SpotifyController().geTopTracksTask(new ArtistTopSongsTask.TopSongsListener() {
            @Override
            public void reportTopSongs(List<Track> topTracks) {
                ((TracksActivityFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frag_top_tracks))
                        .setTrackst(topTracks.toArray(new Track[topTracks.size()]));
            }
        }).execute(artistId);
    }

    @Override
    public Intent getParentActivityIntent () {
        Intent intent = super.getParentActivityIntent();
        if(intent == null) {
            intent = new Intent();
        }
        
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return intent;
    }
}
