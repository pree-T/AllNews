package com.example.allnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.allnews.Models.Articles;
import com.example.allnews.Models.HeadLines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final String API_KEY="75b7dfaab177448d89ea53172c3133a1";
    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country=getCountry();
        retrieveJson(country,API_KEY);
    }
    public  void retrieveJson(String country,String apikey){
        Call<HeadLines> call=ApiClient.getInstance().getApi().getHeadlines(country, apikey);
        call.enqueue(new Callback<HeadLines>() {
            @Override
            public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null)
                {
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter=new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<HeadLines> call, Throwable t) {
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