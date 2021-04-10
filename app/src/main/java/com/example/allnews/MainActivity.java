package com.example.allnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allnews.Models.Articles;
import com.example.allnews.Models.HeadLines;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final String API_KEY="75b7dfaab177448d89ea53172c3133a1";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter;
    Button searchBtn;
    EditText etQuery;
    List<Articles> articles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout=findViewById(R.id.swipe);
        recyclerView=findViewById(R.id.recyclerv);
        searchBtn=findViewById(R.id.btnSearch);
        etQuery=findViewById(R.id.etQuery);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country=getCountry();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson("",country,API_KEY);
            }
        });


        retrieveJson("",country,API_KEY);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etQuery.getText().toString().equals("")){
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson(etQuery.getText().toString(),country,API_KEY);
                        }
                    });
                    retrieveJson(etQuery.getText().toString(),country,API_KEY);
                }else{
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson("",country,API_KEY);
                        }
                    });
                    retrieveJson("",country,API_KEY);
                }
            }
        });
    }
    public  void retrieveJson(String query,String country,String apikey){
        swipeRefreshLayout.setRefreshing(true);
        Call<HeadLines> call;
        if (!etQuery.getText().toString().equals("")){
            call= ApiClient.getInstance().getApi().getSpecificData(query,apikey);
        }else{
            call= ApiClient.getInstance().getApi().getHeadlines(country,apikey);
        }

        call.enqueue(new Callback<HeadLines>() {
            @Override
            public void onResponse(@NotNull Call<HeadLines> call, @NotNull Response<HeadLines> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getArticles() != null) {
                        swipeRefreshLayout.setRefreshing(false);
                        articles.clear();
                        articles = response.body().getArticles();
                        adapter = new Adapter(MainActivity.this, articles);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<HeadLines> call, @NotNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
    public String getCountry()
    {
        Locale locale= Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}