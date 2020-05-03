package com.haphap.movieticketapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haphap.movieticketapp.R;

import java.util.HashMap;
import java.util.List;

public class SeatListAdapter extends RecyclerView.Adapter<SeatListAdapter.ViewHolder> {

    private HashMap<Integer, Boolean> isChecked = new HashMap<>();
    private Context context;
    private List<String> seatList;
    private Listener listener;

    public SeatListAdapter(Context context, List<String> seatList){
        this.context = context;
        this.seatList = seatList;
    }

    public interface Listener {
        void onCheckedChanged(int position, CompoundButton button, boolean isChecked);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_seat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cbSeat.setText(seatList.get(position));
        if (isChecked.containsKey(position)){
            holder.cbSeat.setChecked(isChecked.get(position));
        } else {
            holder.cbSeat.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbSeat;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            cbSeat = itemView.findViewById(R.id.cb_seat);
            cbSeat.setOnCheckedChangeListener((compoundButton, isChecked) -> {
               if (listener != null) {
                   listener.onCheckedChanged(getAdapterPosition(), compoundButton, isChecked);
               }
            });
        }
    }
}
