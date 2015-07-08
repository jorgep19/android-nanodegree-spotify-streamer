package com.jpdevs.spotifystreamer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArtistsSearchAdapter extends RecyclerView.Adapter<ArtistsSearchAdapter.ArtistViewHolder> {
    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_artist, parent, false);

        return  new ArtistViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder viewHolder, int i) {
        viewHolder.mTVArtistName.setText("Someone");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        public TextView mTVArtistName;

        public ArtistViewHolder(View rootView) {
            super(rootView);
            mTVArtistName = (TextView) rootView.findViewById(R.id.artist_name);
        }
    }

    public void updateArtists() {

    }
}