package com.haphap.movieticketapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.haphap.movieticketapp.model.Ticket;

import java.util.List;

@Dao
public interface TicketDao {

    @Insert
    Long insertTicket(Ticket ticket);

    @Query("SELECT * FROM ticket_table WHERE idUser = :userId ORDER BY date ASC" )
    LiveData<List<Ticket>> getTicket(Long userId);
}
