package com.example.allnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allnews.Models.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articles;

    public Adapter(Context context,List<Articles> articles) {
        this.context = context;
        this.articles=articles;
    }

    public Adapter(List<Articles> articles) {
        this.articles = articles;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Adapter.ViewHolder holder, int position) {
        Articles a=articles.get(position);

        String imageUrl=a.getUtom();
        Picasso.get().load(imageUrl).into(holder.imageView);

        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText("\u2022"+dateTime(a.getPub()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Details.class);
                intent.putExtra("title",a.getTitle());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",dateTime(a.getPub()));
                intent.putExtra("desc",a.getDesp());
                intent.putExtra("imageUrl",a.getUtom());
                intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;


        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvid);
            tvSource=itemView.findViewById(R.id.tvsource);
            tvDate=itemView.findViewById(R.id.tvdate);
            imageView=itemView.findViewById(R.id.image1);
            cardView=itemView.findViewById(R.id.cardView);


        }
    }
    public String dateTime(String t)
    {
        PrettyTime prettyTime=new PrettyTime(new Locale(getCountry()));
        String time=null;
        try {

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
                Date date=simpleDateFormat.parse(t);
                time=prettyTime.format(date);

        }
        catch( ParseException e)
        {
            e.printStackTrace();
        }
        return time;

    }
    public String getCountry()
    {
        Locale locale= Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}
