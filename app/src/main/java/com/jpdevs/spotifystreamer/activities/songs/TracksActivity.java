package com.jpdevs.spotifystreamer.activities.songs;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.model.ParcelableArtist;
import com.jpdevs.spotifystreamer.model.ParcelableTrack;
import com.jpdevs.spotifystreamer.spotify.ArtistTopSongsTask;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;
import com.jpdevs.spotifystreamer.utils.SimpleDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class TracksActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST = "artist_data";

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(artist.getName());

        loadBackdrop(artist.getProfileImgUrl());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container, TracksActivityFragment.newInstance(artist.getId()))
                .commit();

//        // setup list from bundle
//        ((TracksActivityFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.tracks_fragment))
//                .setTracks(topTracks);
    }

    @Override
    public Intent getParentActivityIntent () {
        Intent intent = super.getParentActivityIntent();
        if(intent == null) {
            intent = new Intent();
        }
        
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return intent;
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
