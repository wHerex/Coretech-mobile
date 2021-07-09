package com.example.coretech_mobile.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Event;

import java.util.List;

public class DashbordRecyclerViewAdapter extends RecyclerView.Adapter<DashbordRecyclerViewAdapter.MyViewHolder>{

    Event[] events;
    Context context;
    OnEventListener onEventListener;
    Button deleteButton;

    public DashbordRecyclerViewAdapter(Context context, List<Event> events, OnEventListener onEventListener){
        this.context = context;
        this.events = events.toArray(new Event[0]);
        this.onEventListener = onEventListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dashboard_recycler_view_row_layout, parent, false);
        return new MyViewHolder(view, onEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DashbordRecyclerViewAdapter.MyViewHolder holder, int position) {
        String startDateTime = events[position].getStartDateTime();
        String subject = events[position].getSubject();
        holder.event = events[position];
        holder.textView.setText(startDateTime + ". " + subject);
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Event event;
        OnEventListener onEventListener;
        TextView textView;

        public MyViewHolder(@NonNull View itemView, OnEventListener onEventListener){
            super(itemView);
            textView = itemView.findViewById(R.id.dsh_text_view);
            itemView.setOnClickListener(this);
            this.onEventListener = onEventListener;
            deleteButton = itemView.findViewById(R.id.dsh_delete_button);
            deleteButton.setOnClickListener(c ->{
                onEventListener.onDeleteButtonClick(event.getId());
            });
        }


        @Override
        public void onClick(View v) {
        onEventListener.onProductClick(event);
        }
    }

    public interface OnEventListener {
        void onProductClick(Event event);
        void onDeleteButtonClick(String id);
    }
}