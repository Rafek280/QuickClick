package com.example.projekt2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class SecondSelectTime extends AppCompatActivity {
    //In this arraylist I will save the duration of the game
    public static ArrayList<Integer> choosedgametime = new ArrayList();

    private ImageView twenty,thirty,sixty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_select_time);



        choosedgametime.add(0, 0);

        twenty = findViewById(R.id.imagetwenty);
        thirty = findViewById(R.id.imagethirty);
        sixty = findViewById(R.id.imagesixty);

        twenty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choosedgametime.set(0,21000);
                openThirdActivity();
            }
        });

        thirty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choosedgametime.set(0,31000);
                openThirdActivity();
            }
        });

        sixty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choosedgametime.set(0,61000);
                openThirdActivity();
            }
        });


    }

    public void openThirdActivity() {//this function open the activity 3

        Intent intent = new Intent(this, ThirdGameDifficult.class);
        startActivity(intent);

    }
}