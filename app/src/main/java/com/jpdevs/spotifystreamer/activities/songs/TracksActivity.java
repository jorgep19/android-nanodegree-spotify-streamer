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


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        ParcelableArtist artist = intent.getParcelableExtra(EXTRA_ARTIST);


        final RecyclerView tracksList = (RecyclerView) findViewById(R.id.track_list);
        tracksList.addItemDecoration(new SimpleDividerItemDecoration(
                getResources().getDrawable(R.drawable.line_divider)));
        tracksList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new SpotifyController().geTopTracksTask(new ArtistTopSongsTask.TopSongsListener() {
            @Override
            public void reportTopSongs(ParcelableTrack[] topTracks) {

                TopTracksAdapter tracksAdapter = new TopTracksAdapter();
                tracksAdapter.setTracks(topTracks);
                tracksList.setAdapter(tracksAdapter);


//                View scroll = findViewById(R.id.scrollView);
//
//                ((TracksActivityFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.frag_top_tracks))
//                        .setTrackst(topTracks.toArray(new Track[topTracks.size()]));
            }
        }).execute(artist.getId());

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(artist.getName());

        loadBackdrop(artist.getProfileImgUrl());
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
