package com.example.projekt2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

public class FifthResults extends AppCompatActivity {

    private TextView percent,correct,wrong,playagain;

    private int percent2;

    //This is the last activity is just shows the result and give you the posibility to go back to the first activity and play again
    // I disabled the back button to avaid to go to the game activity directly
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firth_results);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        playagain = findViewById(R.id.textView14);
        percent = findViewById(R.id.textView);
        correct = findViewById(R.id.textView4);
        wrong = findViewById(R.id.textView5);
        onBackPressed();

        percent.setText("Accuracy " + calculateAccuracy() + " %");
        correct.setText(FourthGame.correctresult.get(0)+ " times correct!");
        wrong.setText(FourthGame.wrongresult.get(0)+ " times wrong!");

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     try {
               openfirstactivity();

            }
        });

    }
    //This method just calculate the accuracy
    public int calculateAccuracy() {
        if(FourthGame.correctresult.get(0)==0 && FourthGame.wrongresult.get(0)==0){
            percent2=0;
        }
        else{
            percent2 = ((FourthGame.correctresult.get(0) * 100) / (FourthGame.correctresult.get(0) + FourthGame.wrongresult.get(0)));}
        return percent2;
    }
    public void openfirstactivity() {//this function open the activity 1

        Intent intent = new Intent(this, FirstSelectFigur.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed()
    {

        //thats it
    }
}