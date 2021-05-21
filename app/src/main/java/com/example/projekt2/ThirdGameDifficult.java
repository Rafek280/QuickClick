package com.example.projekt2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdGameDifficult extends AppCompatActivity {

    public static ArrayList<Integer> chooseddifficulty = new ArrayList();

    private TextView easy,medium,hard,godmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_game_difficult);
        //In this arraylist I will save different duration for each difficult
        chooseddifficulty.add(0, 0);

        easy = findViewById(R.id.textEasy);
        medium = findViewById(R.id.textMedium);
        hard = findViewById(R.id.textHard);
        godmode = findViewById(R.id.textGodMode);



       easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                chooseddifficulty.set(0,2000);
                openfourthactivity();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                chooseddifficulty.set(0,1250);
                openfourthactivity();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                chooseddifficulty.set(0,800);
                openfourthactivity();
            }
        });

        godmode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                chooseddifficulty.set(0,550);
                openfourthactivity();
            }
        });

    }

    public void openfourthactivity() {//this function open the activity 2

        Intent intent = new Intent(this, FourthGame.class);
        startActivity(intent);

    }
}