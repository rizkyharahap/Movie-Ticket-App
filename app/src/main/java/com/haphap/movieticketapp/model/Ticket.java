package com.haphap.movieticketapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ticket_table")
public class Ticket implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "idUser")
    private Long idUser;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "studio")
    private String studio;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "seat")
    private String seat;

    public Ticket(Long idUser, String title, String studio, String date, String time, String seat) {
        this.idUser = idUser;
        this.title = title;
        this.studio = studio;
        this.date = date;
        this.time = time;
        this.seat = seat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getTitle() {
        return title;
    }

    public String getStudio() {
        return studio;
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

    public String getSeat() {
        return seat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.idUser);
        dest.writeString(this.title);
        dest.writeString(this.studio);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.seat);
    }

    protected Ticket(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.idUser = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.studio = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.seat = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel source) {
            return new Ticket(source);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };
}