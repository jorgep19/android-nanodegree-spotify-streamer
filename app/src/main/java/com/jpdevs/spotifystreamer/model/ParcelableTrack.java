package com.jpdevs.spotifystreamer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Track;

public class ParcelableTrack implements Parcelable {
    public static final Creator<ParcelableTrack> CREATOR = new Creator<ParcelableTrack>() {
        @Override
        public ParcelableTrack createFromParcel(Parcel in) {
            return new ParcelableTrack(in);
        }

        @Override
        public ParcelableTrack[] newArray(int size) {
            return new ParcelableTrack[size];
        }
    };

    private String mName;
    private String mAlbumIconUrl;

    protected ParcelableTrack(Parcel in) {
        mName = in.readString();
        mAlbumIconUrl = in.readString();
    }

    public ParcelableTrack(Track track) {
        mName = track.name;
        mAlbumIconUrl = null;

        if(track.album.images.size() > 0) {
            int imgIndex = track.album.images.size() > 1 ?
                    track.album.images.size() - 2 :
                    track.album.images.size() - 1;
            mAlbumIconUrl = track.album.images.get(imgIndex).url;
        }
    }

    public String getName() {
        return mName;
    }

    public String getAlbumIconUrl() {
        return mAlbumIconUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mAlbumIconUrl);
    }
}
