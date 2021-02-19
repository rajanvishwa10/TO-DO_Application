package com.android.to_doapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
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
                holder.imageView.setVisibility(View.INVISIBLE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(context, "Long Pressed!!!!!", Toast.LENGTH_SHORT).show();
                //holder.imageView.setVisibility(View.VISIBLE);
                HashMap<String, Object> update = new HashMap<>();
                update.put("pin", !todo.isPin());
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                database.child("User").
                        child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                        child(todo.getDate()).updateChildren(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Pinned!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
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
        LinearLayout imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notes = itemView.findViewById(R.id.notes);
            title = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.linearLayout);
        }
    }
}
