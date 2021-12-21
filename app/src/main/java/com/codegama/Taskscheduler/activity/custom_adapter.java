package com.codegama.Taskscheduler.activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.codegama.Taskscheduler.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class custom_adapter extends FirebaseRecyclerAdapter<model_class,custom_adapter.my_view_holder> {

    public custom_adapter(@NonNull FirebaseRecyclerOptions<model_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull my_view_holder holder, int position, @NonNull model_class model) {
           holder.email.setText("user :"+model.getUsername());
           holder.number.setText("completed text :"+model.getTotal());
    }
    @NonNull
    @Override
    public my_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout,parent,false);
        return new my_view_holder(view);
    }
    class  my_view_holder extends RecyclerView.ViewHolder
    {
        TextView email,number;
        public my_view_holder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.ans);
            number=itemView.findViewById(R.id.number);
        }
    }
}
