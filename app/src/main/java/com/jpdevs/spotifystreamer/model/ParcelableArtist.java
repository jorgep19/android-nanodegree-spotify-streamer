package com.jpdevs.spotifystreamer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.jpdevs.spotifystreamer.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Artist;

public class ParcelableArtist implements Parcelable {
    public static final Creator<ParcelableArtist> CREATOR = new Creator<ParcelableArtist>() {
        @Override
        public ParcelableArtist createFromParcel(Parcel in) {
            return new ParcelableArtist(in);
        }

        @Override
        public ParcelableArtist[] newArray(int size) {
            return new ParcelableArtist[size];
        }
    };

    private String mId;
    private String mName;
    private String mIconUrl;
    private String mProfileImgUrl;

    protected ParcelableArtist(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mIconUrl = in.readString();
        mProfileImgUrl = in.readString();
    }

    public ParcelableArtist(Artist artist) {
        mId = artist.id;
        mName = artist.name;
        mIconUrl = null;
        mProfileImgUrl = null;

        if (artist.images.size() != 0) {
            int iconIndex = artist.images.size() > 1 ? artist.images.size() - 2 : artist.images.size() - 1;
            int profileIndex = artist.images.size() > 1 ? 1 : 0;
            mIconUrl = artist.images.get(iconIndex).url;
            mProfileImgUrl = artist.images.get(profileIndex).url;
        }
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public String getProfileImgUrl() {
        return mProfileImgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mIconUrl);
        dest.writeString(mProfileImgUrl);
    }
}
