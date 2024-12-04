package com.example.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText roundNumberEdt, roundScoreEdt, opponentColorsEdt, roundNotesEdt;
    private Button addRoundBtn, readRoundBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        roundNumberEdt = findViewById(R.id.idEdtRoundNumber);
        roundScoreEdt = findViewById(R.id.idEdtRoundScore);
        opponentColorsEdt = findViewById(R.id.idEdtOpponentColors);
        roundNotesEdt = findViewById(R.id.idEdtRoundNotes);
        addRoundBtn = findViewById(R.id.idBtnAddRound);
        readRoundBtn = findViewById(R.id.idBtnReadRound);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add round button.
        addRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String roundNumber = roundNumberEdt.getText().toString();
                String roundScore = roundScoreEdt.getText().toString();
                String opponentColors = opponentColorsEdt.getText().toString();
                String roundNotes = roundNotesEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (roundNumber.isEmpty() || roundScore.isEmpty() || opponentColors.isEmpty() || roundNotes.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // round to sqlite data and pass all our values to it.
                dbHandler.addNewRound(roundNumber, opponentColors, roundNotes, roundScore);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Round has been added.", Toast.LENGTH_SHORT).show();
                roundNumberEdt.setText("");
                roundScoreEdt.setText("");
                opponentColorsEdt.setText("");
                roundNotesEdt.setText("");
            }
        });

        readRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewRounds.class);
                startActivity(i);
            }
        });
    }
}
