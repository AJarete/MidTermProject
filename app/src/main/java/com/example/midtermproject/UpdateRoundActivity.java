package com.example.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class UpdateRoundActivity extends AppCompatActivity{

    // variables for our edit text, button, strings and dbhandler class.
    private EditText roundNumberEdt, roundScoreEdt, opponentColorsEdt, roundNotesEdt;
    private Button updateRoundBtn, deleteRoundBtn;
    private DBHandler dbHandler;
    String roundNumber, roundNotes, opponentColors, roundScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_round);

        // initializing all our variables.
        roundNumberEdt = findViewById(R.id.idEdtRoundNumber);
        roundScoreEdt = findViewById(R.id.idEdtRoundScore);
        opponentColorsEdt = findViewById(R.id.idEdtOpponentColors);
        roundNotesEdt = findViewById(R.id.idEdtRoundNotes);
        updateRoundBtn = findViewById(R.id.idBtnUpdateRound);
        deleteRoundBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateRoundActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        roundNumber = getIntent().getStringExtra("number");
        roundNotes = getIntent().getStringExtra("notes");
        opponentColors = getIntent().getStringExtra("colors");
        roundScore = getIntent().getStringExtra("score");

        // setting data to edit text
        // of our update activity.
        roundNumberEdt.setText(roundNumber);
        roundNotesEdt.setText(roundNotes);
        roundScoreEdt.setText(roundScore);
        opponentColorsEdt.setText(opponentColors);

        // adding on click listener to our update round button.
        updateRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update round
                // method and passing all our edit text values.
                dbHandler.updateRound(roundNumber, roundNumberEdt.getText().toString(), roundNotesEdt.getText().toString(), roundScoreEdt.getText().toString(), roundNotesEdt.getText().toString());

                // displaying a toast message that our round has been updated.
                Toast.makeText(UpdateRoundActivity.this, "Round Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateRoundActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our round.
        deleteRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our round.
                dbHandler.deleteRound(roundNumber);
                Toast.makeText(UpdateRoundActivity.this, "Deleted the round", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRoundActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
