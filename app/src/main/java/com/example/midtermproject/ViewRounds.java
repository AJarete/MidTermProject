package com.example.midtermproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewRounds extends AppCompatActivity{

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<RoundModal> roundModalArrayList;
    private DBHandler dbHandler;
    private RoundRVAdapter roundRVAdapter;
    private RecyclerView roundsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rounds);

        // initializing our all variables.
        roundModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewRounds.this);

        // getting our round array
        // list from db handler class.
        roundModalArrayList = dbHandler.readRounds();

        // on below line passing our array list to our adapter class.
        roundRVAdapter = new RoundRVAdapter(roundModalArrayList, ViewRounds.this);
        roundsRV = findViewById(R.id.idRVRounds);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewRounds.this, RecyclerView.VERTICAL, false);
        roundsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        roundsRV.setAdapter(roundRVAdapter);
    }
}