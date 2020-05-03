package com.haphap.movieticketapp.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.adapter.SearchAdapter;
import com.haphap.movieticketapp.model.Movie;
import com.haphap.movieticketapp.viewmodel.SearchViewModel;

import java.util.List;

public class SearchActivity extends BaseActivity {

    private Toolbar toolbar;
    private String query;
    private SearchViewModel searchViewModel;
    private SearchAdapter searchAdapter;
    private RecyclerView rvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        toolbar = findViewById(R.id.toolbar);
        rvSearch = findViewById(R.id.rv_search);

    }

    @Override
    public void initView() {
        query = getIntent().getStringExtra(SearchActivity.class.getSimpleName());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.getSearchMovie(query).observe(this , new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                searchAdapter = new SearchAdapter(movies, getApplicationContext());
                rvSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rvSearch.setAdapter(searchAdapter);
            }
        });
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_light, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                intent.putExtra(SearchActivity.class.getSimpleName(), query);
                startActivity(intent);
                finish();

                searchMenuItem.getIcon().setVisible(false, false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
