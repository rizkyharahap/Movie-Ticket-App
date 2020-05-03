package com.haphap.movieticketapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.adapter.SeatListAdapter;
import com.haphap.movieticketapp.model.Ticket;
import com.haphap.movieticketapp.model.TicketDescription;
import com.haphap.movieticketapp.viewmodel.TicketViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyTicketActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView tvTitle
            , tvSeat
            , tvTotalPrice
            , tvStudio
            , tvDate
            , tvTime;
    private Button btnBuy;
    private List<String> listSeat;
    private List<String> selectedSeat = new ArrayList<>();
    private RecyclerView rvSeat;
    private SeatListAdapter seatListAdapter;
    private TicketViewModel ticketViewModel;

    private TicketDescription ticketDescription = new TicketDescription();

    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        toolbar = findViewById(R.id.toolbar);
        rvSeat = findViewById(R.id.rv_seat);
        tvTitle = findViewById(R.id.tv_title);
        tvStudio = findViewById(R.id.tv_studio);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        tvSeat = findViewById(R.id.tv_seat);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnBuy = findViewById(R.id.btn_buy);
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        ticketDescription = (TicketDescription) getIntent().getExtras().get(BuyTicketActivity.class.getSimpleName());
        ticketViewModel = ViewModelProviders.of(this).get(TicketViewModel.class);

        tvTitle.setText(ticketDescription.getTitle());
        tvStudio.setText(ticketDescription.getStudio());
        tvDate.setText(ticketDescription.getDate());
        tvTime.setText(ticketDescription.getTime());

        String[] seatListArray = getApplication().getResources().getStringArray(R.array.seatName);
        listSeat = Arrays.asList(seatListArray);

        seatListAdapter = new SeatListAdapter(getApplicationContext(),listSeat);

        rvSeat.setLayoutManager(new GridLayoutManager(this,8));
        rvSeat.setAdapter(seatListAdapter);
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(v -> finish());

        seatListAdapter.setListener((position, button, isChecked) -> {

            String seatTemp = "";

            if (isChecked) {
                showToast("Seat " + button.getText().toString() + " selected");
                button.setTextColor(getApplicationContext().getColor(R.color.colorWhite));
                selectedSeat.add(button.getText().toString());
                totalPrice = totalPrice + 50000;
            } else {
                button.setTextColor(getApplicationContext().getColor(R.color.colorPrimary));
                selectedSeat.remove(button.getText().toString());
                totalPrice = totalPrice - 50000;
            }

            for (int i = 0; i < selectedSeat.size(); i++) {
                seatTemp = seatTemp + selectedSeat.get(i) + " ";
            }
            tvSeat.setText(seatTemp);

            DecimalFormat kursRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("IDR. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');

            kursRp.setDecimalFormatSymbols(formatRp);
            tvTotalPrice.setText(kursRp.format(totalPrice));
        });

        btnBuy.setOnClickListener(view -> {

            if (tvSeat.getText().toString().equals(" ")) {
                showToast("You must select yout seat");
            } else {
                new  MaterialAlertDialogBuilder(this)
                        .setTitle("Ticket Purchase")
                        .setMessage("Are you sure you bought a ticket for " + tvTotalPrice.getText())
                        .setPositiveButton("Yes", (dialogInterface, i) -> {

                            Long userID = getSharedPrefence().getUserId();
                            String title = ticketDescription.getTitle();
                            String studio = ticketDescription.getStudio();
                            String date = ticketDescription.getDate();
                            String time = ticketDescription.getTime();
                            String seat = tvSeat.getText().toString();

                            Ticket ticket = new Ticket(userID, title, studio, date, time, seat);
                            Long insertedTicketId = ticketViewModel.insertTicket(ticket);

                            if (insertedTicketId != null) {
                                showToast("Successful Purchase");
                                finish();
                            } else {
                                showToast("Error Purchase");
                            }
                        })
                        .setNegativeButton("Cencel", (dialogInterface, i) -> {
                            dialogInterface.cancel();
                        })
                        .show();
            }
        });
    }
}
