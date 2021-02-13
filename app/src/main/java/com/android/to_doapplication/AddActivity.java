package com.android.to_doapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ResourceBundle;

public class AddActivity extends AppCompatActivity {
    Toolbar toolbar;
    ConstraintLayout constraintLayout;
    CardView cardView;
    ImageButton imageButton, imageButton2;
    LinearLayout linearLayout;

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
        constraintLayout.setBackgroundColor(Color.parseColor("#FFEB3B"));
    }

    public void addRedBackgroundColor(View view) {
        constraintLayout.setBackgroundColor(Color.parseColor("#FF0000"));
    }

    public void addOrangeBackgroundColor(View view) {
        constraintLayout.setBackgroundColor(Color.parseColor("#FF4213"));
    }
}