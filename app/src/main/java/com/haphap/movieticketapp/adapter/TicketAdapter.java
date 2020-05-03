package com.haphap.movieticketapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.model.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private List<Ticket> tickets;
    private Context context;

    public TicketAdapter(Context context) {
        this.context = context;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tickets.get(position));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle
                , tvDate
                , tvStudio
                , tvTime
                , tvSeat;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvStudio = itemView.findViewById(R.id.tv_studio);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvSeat = itemView.findViewById(R.id.tv_seat);
        }

        void bind(Ticket ticket) {
            tvTitle.setText(ticket.getTitle());
            tvDate.setText(ticket.getDate());
            tvStudio.setText(ticket.getStudio());
            tvTime.setText(ticket.getTime());
            tvSeat.setText(ticket.getSeat());
        }
    }
}
