package com.haphap.movieticketapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.haphap.movieticketapp.database.AppDatabase;
import com.haphap.movieticketapp.database.TicketDao;
import com.haphap.movieticketapp.model.Ticket;

import java.util.List;

public class TicketViewModel extends AndroidViewModel {

    private TicketDao ticketDao;

    public TicketViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ticketDao = appDatabase.ticketDao();
    }

    public Long insertTicket(Ticket ticket) {
        return ticketDao.insertTicket(ticket);
    }

    public LiveData<List<Ticket>> getTicket(Long userId) {
        return ticketDao.getTicket(userId);
    }
}
