package com.jpdevs.spotifystreamer.activities.songs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.spotify.ArtistTopSongsTask;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class SongsActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST_ID = "artist_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Intent intent = getIntent();
        String artistId = intent.getStringExtra(EXTRA_ARTIST_ID);
        new SpotifyController().geTopTracksTask(new ArtistTopSongsTask.TopSongsListener() {
            @Override
            public void reportTopSongs(List<Track> artistsFound) {

            }
        }).execute(artistId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_songs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
