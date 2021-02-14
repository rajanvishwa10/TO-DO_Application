package com.android.to_doapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    Context context;
    List<TodoList> todoList;

    public TodoAdapter(Context context, List<TodoList> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_notes, parent, false);
        return new TodoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        TodoList todo = todoList.get(position);
        holder.cardView.setCardBackgroundColor(Color.parseColor(todo.getColor()));
        holder.title.setText(todo.getTitle());
        holder.notes.setText(todo.getNote());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, AddActivity.class);
                intent.putExtra("string", "cardView");
                intent.putExtra("title", todo.getTitle());
                intent.putExtra("notes", todo.getNote());
                intent.putExtra("color", todo.getColor());
                intent.putExtra("date", todo.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView notes, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notes = itemView.findViewById(R.id.notes);
            title = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
