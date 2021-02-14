package com.android.to_doapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddActivity extends AppCompatActivity {
    Toolbar toolbar;
    ConstraintLayout constraintLayout;
    CardView cardView;
    String title = "", note = "";
    ImageButton imageButton, imageButton2;
    LinearLayout linearLayout;
    String color = "#FFFFFFFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        constraintLayout = findViewById(R.id.constraint);
        linearLayout = findViewById(R.id.linearLayout);
        cardView = findViewById(R.id.yellowcardView);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void openColors(View view) {
        linearLayout.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.GONE);
        imageButton2.setVisibility(View.VISIBLE);
    }

    public void closeColors(View view) {
        linearLayout.setVisibility(View.GONE);
        imageButton2.setVisibility(View.GONE);
        imageButton.setVisibility(View.VISIBLE);
    }

    public void addYellowBackgroundColor(View view) {
        color = "#FFEB3B";
        constraintLayout.setBackgroundColor(Color.parseColor("#FFEB3B"));
    }

    public void addRedBackgroundColor(View view) {
        color = "#FF0000";
        constraintLayout.setBackgroundColor(Color.parseColor("#FF0000"));
    }

    public void addOrangeBackgroundColor(View view) {
        color = "#FF4213";
        constraintLayout.setBackgroundColor(Color.parseColor("#FF4213"));
    }

    public void addImage(View view) {
        Toast.makeText(this, "Add Image", Toast.LENGTH_SHORT).show();
    }

    public void submit(View view) {
        EditText editText = findViewById(R.id.title);
        EditText editText2 = findViewById(R.id.note);
        title = editText.getText().toString().trim();
        note = editText2.getText().toString().trim();
        addTODatabase(title, note, color);
    }

    private void addTODatabase(String title, String note, String color) {
        final Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        final String formattedDate = df.format(c);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("title", title);
        hashMap.put("note", note);
        hashMap.put("color", color);
        hashMap.put("date", formattedDate);

        databaseReference.child("User").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(formattedDate)
                .updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddActivity.this, "Submited", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}