package com.example.projekt2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.projekt2.ThirdGameDifficult.chooseddifficulty;

public class FourthGame extends AppCompatActivity {

    //In this activity will start the game, if the game start we will not have the possibility to go back to avoid
    //problems with the timertaks.We have 2 timers task one is for the duration of the game and the second one set
    // a random figure in a random Position
    public static ArrayList<Integer> correctresult = new ArrayList();
    public static ArrayList<Integer> wrongresult = new ArrayList();
    public static ArrayList<Integer> selection = new ArrayList();


    private ImageView cube,sfera,pyramid;

    private int countercube,countersfera,counterpyramid,correct,wrong,random;
    private TextView beready;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        holdtheorientation();
        onBackPressed();

        correctresult.add(0, 0);
        wrongresult.add(0, 0);
        selection.add(0,0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_game);

        cube = findViewById(R.id.imageViewcube);
        sfera = findViewById(R.id.imageViewsfera);
        pyramid = findViewById(R.id.imageViewpyramid);
        beready = findViewById(R.id.textready);

        countercube = 0;
        countersfera = 0;
        counterpyramid = 0;

        cube.setVisibility(View.INVISIBLE);
        pyramid.setVisibility(View.INVISIBLE);
        sfera.setVisibility(View.INVISIBLE);

        start2();
        start();
        //when we click in a figure we set the counter of click (correct or wrong), we will paint the figure in green if is correct
        // and red if is wrong and we will save the results in an arraylist
        cube.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {

                countercube++;
                selection.set(0,0);
                correctButton();
                setResults();

            }
        });

        sfera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 countersfera++;
                 selection.set(0,1);
                 correctButton();
                 setResults();

            }
        });

        pyramid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 counterpyramid++;
                 selection.set(0,2);
                 correctButton();
                 setResults();

            }
        });


    }

    public void randomPositionForAll() {


        cube.clearColorFilter();
        sfera.clearColorFilter();
        pyramid.clearColorFilter();

        cube.setVisibility(View.INVISIBLE);
        pyramid.setVisibility(View.INVISIBLE);
        sfera.setVisibility(View.INVISIBLE);


        random = getRandomNumber();

       if (random == 0) {

           cube.setVisibility(View.VISIBLE);
           cube.setX(getRandomNumberX());
           cube.setY(getRandomNumberY());

       } else if (random == 1) {
           sfera.setVisibility(View.VISIBLE);
           sfera.setX(getRandomNumberX());
           sfera.setY(getRandomNumberY());

       } else if (random == 2) {
           pyramid.setVisibility(View.VISIBLE);
           pyramid.setX(getRandomNumberX());
           pyramid.setY(getRandomNumberY());

       }
    }

    //In this method we get a random value beetween 0 and almost the maximum value of the size of the display in the axis x
    // the values are different in porttrait and in landscape
    private int getRandomNumberX() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int min = 0;
        int max = 750;
        int orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            max = 1250;
        } else {
            max= 700;
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    //In this method we get a random value beetween 0 and almost the maximum value of the size of the display in the axis y
    // the values are different in porttrait and in landscape
    private int getRandomNumberY() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int min = 0;

        int max = 1250;

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            max = 700;
        } else {
            max= 1250;
        }


        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    // this method get a random  number between 0 and 2 0 is for cube 1 is for square and 2 is for the pyramid
    private static int getRandomNumber() {


        int min = 0;
        int max = 2;

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    // this method is a timetask that set the duration of the game
    public void start() {
        Timer timer1 = new Timer();

        TimerTask ttt = new TimerTask() {
            @Override
            public void run() {
                correctButton();
                setResults();
                try {
                    openfifthactivity();

                } catch (Exception e) {

                }
            }
        };

        timer1.schedule(ttt, SecondSelectTime.choosedgametime.get(0));

    }
    //This method is a timertask that call the method RandomPositionforall in the selected time,
    //as I used the main Thread in this method I had some bugs in the visibility of the figure
    // when I clicked fast and a lot of times in a figure
    //The Main Thread is busy dealing with everyday stuff such as drawing our UI, responding to user interactions and generally
    // ,by default, executing (most) of the code we write.
    // I used the runonuithread to avoid the bug
    // I could found information about this problem in this website https://medium.com/@yossisegev/understanding-activity-runonuithread-e102d388fe93
    public void start2() {
        Timer t = new Timer();

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {


                try {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    beready.setVisibility(View.INVISIBLE);
                                    randomPositionForAll();

                                } catch (Exception e) {
                                    System.err.println("FAIL");
                                    e.printStackTrace();

                                }
                            }
                        });

                } catch (Exception e) {
                    System.err.println("FAIL");
                    e.printStackTrace();
                }
            }
        };

        t.schedule(tt, 1000, chooseddifficulty.get(0));

    }

    //this method is to set the correct and the wrong Figure if the correct figure are clicked we will paint the figure in green and red if is wrong
    public void correctButton() {

        correct = 0;
        wrong = 0;

        if (FirstSelectFigur.choosedfigure.get(0).contains("cube")) {
            correct = countercube;
            wrong = countersfera + counterpyramid;
            if(selection.get(0)==0){

            cube.setColorFilter(Color.GREEN);}
            else if (selection.get(0)==1){

            sfera.setColorFilter(Color.RED);}
            else if(selection.get(0)==2) {

                pyramid.setColorFilter(Color.RED);
            }

        } else if (FirstSelectFigur.choosedfigure.get(0).contains("sfera"))
        {
            if(selection.get(0)==0){
                cube.setColorFilter(Color.RED);}
            else if (selection.get(0)==1){
                sfera.setColorFilter(Color.GREEN);}
            else if(selection.get(0)==2) {
                pyramid.setColorFilter(Color.RED);
            }

            correct = countersfera;
            wrong = countercube + counterpyramid;

        } else if (FirstSelectFigur.choosedfigure.get(0).contains("pyramid")) {

            if(selection.get(0)==0){
                cube.setColorFilter(Color.RED);}
            else if (selection.get(0)==1){
                sfera.setColorFilter(Color.RED);}
            else if(selection.get(0)==2) {
                pyramid.setColorFilter(Color.GREEN);
            }
            correct = counterpyramid;
            wrong = countersfera + countercube;

        }
    }
    //this method sets the wrong and correct moves
    public void setResults() {
        correctresult.set(0, correct);
        wrongresult.set(0, wrong);
    }
    //open last activity
    public void openfifthactivity() {//this function open the activity 5

        Intent intent = new Intent(this, FifthResults.class);
        startActivity(intent);

    }
    //Disable back button
    @Override
    public void onBackPressed()
    {


    }
    //to avoid that the game starts again if u change the orientation u cant change the gameorientation in the game
    public void holdtheorientation()
    {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

}