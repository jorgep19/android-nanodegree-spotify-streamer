package com.jpdevs.spotifystreamer.activities.songs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Track;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TrackViewHolder> {
    private Track[] mTracks;

    public TopTracksAdapter() {
        mTracks = new Track[0];
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_track, parent, false);

        return  new TrackViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder viewHolder, int i) {
        Track track = mTracks[i];
        viewHolder.mTVTrackName.setText(track.name);
        if(track.album.images.size() > 0) {
            int imgIndex = track.album.images.size() > 1 ?
                                track.album.images.size() - 2 :
                                track.album.images.size() - 1;
            Picasso.with(viewHolder.mAlbumImg.getContext())
                    .load(track.album.images.get(imgIndex).url)
                    .transform(new CircleTransform())
                    .into(viewHolder.mAlbumImg);
        } else {
            viewHolder.mAlbumImg.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public int getItemCount() {
        return mTracks.length;
    }

    public void setTracks(Track[] tracks) {
        mTracks = tracks;
        notifyDataSetChanged();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAlbumImg;
        public TextView mTVTrackName;

        public TrackViewHolder(View rootView) {
            super(rootView);
            mAlbumImg = (ImageView) rootView.findViewById(R.id.album_img);
            mTVTrackName = (TextView) rootView.findViewById(R.id.track_name);
        }
    }
}