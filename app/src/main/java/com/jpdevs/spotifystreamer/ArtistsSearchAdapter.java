package com.jpdevs.spotifystreamer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        Artist artist = mArtists.get(i);
        viewHolder.mTVArtistName.setText(artist.name);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        public TextView mTVArtistName;

        public ArtistViewHolder(View rootView) {
            super(rootView);
            mTVArtistName = (TextView) rootView.findViewById(R.id.artist_name);
        }
    }

    public void updateArtists(List<Artist> artists) {
        mArtists = artists;
        mCount = mArtists.size();
        notifyDataSetChanged();
    }
}