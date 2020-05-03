package com.haphap.movieticketapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.model.Movie;
import com.haphap.movieticketapp.model.TicketDescription;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailMovieActivity extends BaseActivity{

    private Toolbar toolbar;
    private Button btnBuy;
    private RadioGroup rgDate, rgTime;
    private RadioButton rbDate1
            , rbDate2
            , rbDate3
            , rbDate4
            , rbDate5
            , rbTime1
            , rbTime2
            , rbTime3
            , rbTime4
            , rbTime5;
    private ImageView ivBackdrop;
    private TextView tvTitle
            , tvVoteAverage
            , tvOverview
            , tvMonth
            , tvReleaseDate
            , tvStudio;
    private Movie movie = new Movie();
    private TicketDescription ticketDescription = new TicketDescription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        toolbar = findViewById(R.id.toolbar);
        rgDate = findViewById(R.id.rg_date);
        rgTime = findViewById(R.id.rg_time);
        rbTime1 = findViewById(R.id.rb_time_1);
        rbTime2 = findViewById(R.id.rb_time_2);
        rbTime3 = findViewById(R.id.rb_time_3);
        rbTime4 = findViewById(R.id.rb_time_4);
        rbTime5 = findViewById(R.id.rb_time_5);
        rbDate1 = findViewById(R.id.rb_date_1);
        rbDate2 = findViewById(R.id.rb_date_2);
        rbDate3 = findViewById(R.id.rb_date_3);
        rbDate4 = findViewById(R.id.rb_date_4);
        rbDate5 = findViewById(R.id.rb_date_5);
        btnBuy = findViewById(R.id.btn_buy);

        ivBackdrop = findViewById(R.id.iv_backdrop_path);
        tvTitle = findViewById(R.id.tv_title);
        tvVoteAverage = findViewById(R.id.tv_vote_average);
        tvOverview = findViewById(R.id.tv_overview);
        tvMonth = findViewById(R.id.tv_date_now);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvStudio = findViewById(R.id.tv_studio);
    }

    @Override
    public void initView() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        movie = (Movie) getIntent().getExtras().get(DetailMovieActivity.class.getSimpleName());

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdropPath())
                .into(ivBackdrop);
        tvTitle.setText(movie.getTitle());
        tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvOverview.setText(movie.getOverview());
        tvReleaseDate.setText(movie.getReleaseDate());

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat date = new SimpleDateFormat("dd");
        int formattedDate = Integer.parseInt(date.format(currentTime));
        String formattedMonth= month.format(currentTime);

        tvMonth.setText(formattedMonth);
        rbDate1.setText(String.valueOf(formattedDate));
        rbDate2.setText(String.valueOf(formattedDate + 1));
        rbDate3.setText(String.valueOf(formattedDate + 2));
        rbDate4.setText(String.valueOf(formattedDate + 3));
        rbDate5.setText(String.valueOf(formattedDate + 4));
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(v -> finish());

        btnBuy.setOnClickListener(view -> {

            int selectedDateID = rgDate.getCheckedRadioButtonId();
            int selectedTimeID = rgTime.getCheckedRadioButtonId();

            if (selectedDateID != -1 && selectedTimeID != -1) {
                RadioButton selectedDate = findViewById(selectedDateID);
                RadioButton selectedTime = findViewById(selectedTimeID);
                String date = selectedDate.getText() + " " + tvMonth.getText();
                String time = selectedTime.getText().toString();

                ticketDescription.setTitle(movie.getTitle());
                ticketDescription.setDate(date);
                ticketDescription.setTime(time);
                ticketDescription.setStudio(tvStudio.getText().toString());

                Intent intent = new Intent(getApplicationContext(), BuyTicketActivity.class);
                intent.putExtra(BuyTicketActivity.class.getSimpleName(), ticketDescription);
                startActivity(intent);
            }
            else{
                showToast("Nothing selected from Date or Time !");
            }
        });
    }

    public void onDateSelection(View view) {
        boolean isSelected = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_date_1 :
                if (isSelected) {
                    rbDate1.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbDate2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_date_2 :
                if (isSelected) {
                    rbDate2.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbDate1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_date_3 :
                if (isSelected) {
                    rbDate3.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbDate1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_date_4 :
                if (isSelected) {
                    rbDate4.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbDate1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_date_5 :
                if (isSelected) {
                    rbDate5.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbDate1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbDate4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
        }
    }


    public void onTimeSelection(View view) {
        boolean isSelected = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_time_1 :
                if (isSelected) {
                    rbTime1.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbTime2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_time_2 :
                if (isSelected) {
                    rbTime2.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbTime1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_time_3 :
                if (isSelected) {
                    rbTime3.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbTime1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_time_4 :
                if (isSelected) {
                    rbTime4.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbTime1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime5.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
            case R.id.rb_time_5 :
                if (isSelected) {
                    rbTime5.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                    rbTime1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    rbTime4.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                break;
        }
    }
}
