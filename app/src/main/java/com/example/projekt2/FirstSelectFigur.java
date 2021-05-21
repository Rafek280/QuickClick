package com.example.projekt2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;


public class FirstSelectFigur extends AppCompatActivity {

    //In this arraylist we will save the choosed Figure as the correct
    public static ArrayList<String> choosedfigure = new ArrayList();

    // I used imageviews instead of buttons to make the design of the app looks more profesional
    private ImageView cube,sfera,pyramid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_select_figur);

        choosedfigure.add(0, "free");

        cube = findViewById(R.id.imageCube);
        sfera = findViewById(R.id.imageSfera);
        pyramid = findViewById(R.id.imagePyramid);

        cube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
                choosedfigure.set(0,"cube");
            }
        });

        sfera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
                choosedfigure.set(0,"sfera");
            }
        });

        pyramid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
                choosedfigure.set(0,"pyramid");
            }
        });
    }

    public void openSecondActivity() {//this function open the activity 2

        Intent intent = new Intent(this, SecondSelectTime.class);
        startActivity(intent);

    }
}