package com.argentinatecno.checkmanager.entities;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Share implements Parcelable {
    Uri file;
    String text;

//    public Share(byte[] file, String text) {
//        this.file = file;
//        this.text = text;
//    }

    public Uri getFile() {
        return file;
    }

    public void setFile(Uri file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


//    public Share(Parcel in) {
//        //this();
//        readFromParcel(in);
//
//    }

    public final Parcelable.Creator<Share> CREATOR = new Creator<Share>() {
        public Share createFromParcel(Parcel in) {
            Uri.Builder builder = new Uri.Builder();
            Share share = new Share();
            share.file = builder.path(in.readString()).build();
           // in.readByteArray(file);
            share.text = in.readString();
            return share;
            }

        public Share[] newArray(int size) {
            return new Share[size];
        }
    };

//    private void readFromParcel(Parcel in) {
//        this.file = new byte[in.readInt()];
//        in.readByteArray(file);
//        this.text = in.readString();
//    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    //    dest.writeInt(file.length);
      //  dest.writeByteArray(file);
        dest.writeString(file.toString());
        dest.writeString(text);
    }
}
