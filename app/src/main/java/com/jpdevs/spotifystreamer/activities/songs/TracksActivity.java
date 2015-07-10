package com.jpdevs.spotifystreamer.activities.songs;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.spotify.ArtistTopSongsTask;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;
import com.jpdevs.spotifystreamer.utils.SimpleDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class TracksActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST_ID = "artist_id";
    public static final String EXTRA_ARTIS_NAME = "artist_name";
    public static final String EXTRA_ARTIST_IMG = "artist_img_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String artistId = intent.getStringExtra(EXTRA_ARTIST_ID);
        String artistName = intent.getStringExtra(EXTRA_ARTIS_NAME);
        String artistImgURL = intent.getStringExtra(EXTRA_ARTIST_IMG);


        final RecyclerView tracksList = (RecyclerView) findViewById(R.id.track_list);
        tracksList.addItemDecoration(new SimpleDividerItemDecoration(
                getResources().getDrawable(R.drawable.line_divider)));
        tracksList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new SpotifyController().geTopTracksTask(new ArtistTopSongsTask.TopSongsListener() {
            @Override
            public void reportTopSongs(List<Track> topTracks) {

                TopTracksAdapter tracksAdapter = new TopTracksAdapter();
                tracksAdapter.setTracks(topTracks.toArray(new Track[topTracks.size()]));
                tracksList.setAdapter(tracksAdapter);


//                View scroll = findViewById(R.id.scrollView);
//
//                ((TracksActivityFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.frag_top_tracks))
//                        .setTrackst(topTracks.toArray(new Track[topTracks.size()]));
            }
        }).execute(artistId);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(artistName);

        loadBackdrop(artistImgURL);
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
