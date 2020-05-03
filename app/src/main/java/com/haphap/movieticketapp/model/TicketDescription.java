package com.haphap.movieticketapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketDescription implements Parcelable {
    private String title
            , studio
            , date
            , time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.studio);
        dest.writeString(this.date);
        dest.writeString(this.time);
    }

    public TicketDescription() {
    }

    protected TicketDescription(Parcel in) {
        this.title = in.readString();
        this.studio = in.readString();
        this.date = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<TicketDescription> CREATOR = new Parcelable.Creator<TicketDescription>() {
        @Override
        public TicketDescription createFromParcel(Parcel source) {
            return new TicketDescription(source);
        }

        @Override
        public TicketDescription[] newArray(int size) {
            return new TicketDescription[size];
        }
    };
}
