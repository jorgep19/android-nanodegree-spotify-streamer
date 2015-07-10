package com.jpdevs.spotifystreamer.activities.search;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.activities.songs.TracksActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

public class ArtistsSearchAdapter extends RecyclerView.Adapter<ArtistsSearchAdapter.ArtistViewHolder> {
    private List<Artist> mArtists;
    private int mCount;

    public ArtistsSearchAdapter() {
        mArtists = new ArrayList<>();
        mCount = 0;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_artist, parent, false);

        return  new ArtistViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder viewHolder, int i) {
        final Artist artist = mArtists.get(i);
        viewHolder.mTVArtistName.setText(artist.name);
        if(artist.images.size() > 0) {
            int imgIndex = artist.images.size() > 1 ? artist.images.size() - 2 : artist.images.size() - 1;
            Picasso.with(viewHolder.mRootView.getContext())
                    .load(artist.images.get(imgIndex).url)
                    .into(viewHolder.mArtistImg);
        }
        viewHolder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TracksActivity.class);
                intent.putExtra(TracksActivity.EXTRA_ARTIST_ID, artist.id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCount; 
    }

    public void updateArtists(List<Artist> artists) {
        mArtists = artists;
        mCount = mArtists.size();
        notifyDataSetChanged();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        public View mRootView;
        public ImageView mArtistImg;
        public TextView mTVArtistName;

        public ArtistViewHolder(View rootView) {
            super(rootView);
            mRootView = rootView;
            mArtistImg = (ImageView) rootView.findViewById(R.id.artist_img);
            mTVArtistName = (TextView) rootView.findViewById(R.id.artist_name);
        }
    }
}