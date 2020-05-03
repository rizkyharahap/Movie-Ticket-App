package com.haphap.movieticketapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.adapter.TicketAdapter;
import com.haphap.movieticketapp.model.User;
import com.haphap.movieticketapp.viewmodel.TicketViewModel;

import static com.haphap.movieticketapp.utils.SharePrefenceHelper.USER_ID_KEY;

public class TicketFragment extends Fragment {

    private TicketAdapter ticketAdapter;
    private RecyclerView rvTicket;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ticket, container, false);
        rvTicket = root.findViewById(R.id.rv_ticket);
        ticketAdapter = new TicketAdapter(getActivity());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        User user = (User) getActivity().getIntent().getExtras().get(USER_ID_KEY);

        TicketViewModel ticketViewModel = ViewModelProviders.of(this).get(TicketViewModel.class);
        ticketViewModel.getTicket(user.getId()).observe(getViewLifecycleOwner(), tickets -> {
            ticketAdapter.setTickets(tickets);
            rvTicket.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTicket.setAdapter(ticketAdapter);
        });
    }
}
