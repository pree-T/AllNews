package com.example.allnews;

import android.content.Context;
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

import java.util.List;

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
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Adapter.ViewHolder holder, int position) {
        Articles a=articles.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPub());

        String imageUrl=a.getUtom();
        Picasso.get().load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
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
}
