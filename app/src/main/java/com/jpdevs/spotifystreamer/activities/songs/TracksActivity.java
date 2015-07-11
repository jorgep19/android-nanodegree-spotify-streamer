package com.jpdevs.spotifystreamer.activities.songs;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.model.ParcelableArtist;
import com.jpdevs.spotifystreamer.model.ParcelableTrack;
import com.jpdevs.spotifystreamer.spotify.ArtistTopSongsTask;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;
import com.squareup.picasso.Picasso;

public class TracksActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST = "artist_data";
    public static final String DATA_TRACKS = "top_tracks";

    private ParcelableTrack[] mTopTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        // retrieve data from the intent
        Intent intent = getIntent();
        ParcelableArtist artist = intent.getParcelableExtra(EXTRA_ARTIST);

        // tool bar setup
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(artist.getName());
        loadBackdrop(artist.getProfileImgUrl());

        // Load top tracks
        ArtistTopSongsTask.TopSongsListener listener = new ArtistTopSongsTask.TopSongsListener() {
            @Override
            public void reportTopSongs(ParcelableTrack[] topTracks) {
                mTopTracks = topTracks;
                ((TracksActivityFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.tracks_fragment))
                        .setTracks(mTopTracks);
            }
        };
        if(savedInstanceState == null) {
            new SpotifyController().geTopTracksTask(listener).execute(artist.getId());
        } else {
            Parcelable[] parcels = savedInstanceState.getParcelableArray(DATA_TRACKS);

            if(parcels != null) {
                mTopTracks = new ParcelableTrack[parcels.length];
                for (int i = 0; i < parcels.length; ++i){
                    mTopTracks[i] = (ParcelableTrack) parcels[i];
                }
            }
            listener.reportTopSongs(mTopTracks);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray(DATA_TRACKS, mTopTracks);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // specify that we want to go back to the same instance of the parent activity
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadBackdrop(String imgUrl) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        if (!TextUtils.isEmpty(imgUrl)) {
            Picasso.with(this).load(imgUrl).into(imageView);
        } else {
            imageView.setImageResource(android.R.color.transparent);
        }

    }
}
