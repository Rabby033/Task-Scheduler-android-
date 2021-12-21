package com.codegama.Taskscheduler.activity;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codegama.Taskscheduler.R;

import java.util.List;

public class ConestAdapter extends RecyclerView.Adapter<ConestAdapter.PostViewHolder> {
    List<Cfcontest> contestList;
    Context context;

    public ConestAdapter(Context context , List<Cfcontest> contests){
        this.context = context;
        contestList = contests;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cfcontesttile , parent , false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ConestAdapter.PostViewHolder holder, int position) {
        Cfcontest cf = contestList.get(position);
        holder.name.setText(cf.getName());
        /*Integer sec = Integer.parseInt(cf.duration);
        Integer hour,min;
        hour=sec/3600;
        min=(sec-(hour*3600))/60;
        String dur=hour+" h ";
        if(min>0)
            dur=dur+min+" min";
        holder.duration.setText(dur);*/
        String contest_link= cf.getUrl();
        holder.duration.setText(contest_link);
        String clink =String.format("<a href=\"%s\">Enter</a> ", contest_link);
        holder.duration.setText(Html.fromHtml(clink));
        holder.duration.setMovementMethod(LinkMovementMethod.getInstance());
        String gmt=cf.getStartTime().substring(11,13);
        Integer bd=Integer.parseInt(gmt);
        bd=(bd+6)%24;
        String st = cf.getStartTime().substring(0,10)+" "+bd+cf.getStartTime().substring(13,19);
        holder.start_time.setText(st);
        if(cf.getStatus().equals("BEFORE"))
            holder.status.setText("Not Started Yet");
        else
            holder.status.setText("Runnnig");


    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration,start_time,status;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contitle);
            duration = itemView.findViewById(R.id.link);
            start_time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);

        }
    }

}
