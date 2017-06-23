package com.example.enchanter19.projectbeauty;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 23/6/17.
 */

public class view_image implements Parcelable {
    String image;
    String names;

    protected view_image(Parcel in) {
        image = in.readString();
        names = in.readString();
    }

    public static final Creator<view_image> CREATOR = new Creator<view_image>() {
        @Override
        public view_image createFromParcel(Parcel in) {
            return new view_image(in);
        }

        @Override
        public view_image[] newArray(int size) {
            return new view_image[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(names);
    }
}

