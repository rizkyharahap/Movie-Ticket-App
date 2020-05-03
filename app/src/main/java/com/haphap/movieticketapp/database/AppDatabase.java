package com.haphap.movieticketapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.haphap.movieticketapp.model.Ticket;
import com.haphap.movieticketapp.model.User;

@Database(entities = {User.class, Ticket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract TicketDao ticketDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ticket_movie_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
