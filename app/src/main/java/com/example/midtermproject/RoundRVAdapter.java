package com.example.midtermproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoundRVAdapter extends RecyclerView.Adapter<RoundRVAdapter.ViewHolder>{

    // variable for our array list and context
    private ArrayList<RoundModal> roundModalArrayList;
    private Context context;

    // constructor
    public RoundRVAdapter(ArrayList<RoundModal> roundModalArrayList, Context context) {
        this.roundModalArrayList = roundModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.round_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        RoundModal modal = roundModalArrayList.get(position);
        holder.roundNumberTV.setText(modal.getRoundNumber());
        holder.roundNotesTV.setText(modal.getRoundNotes());
        holder.opponentColorsTV.setText(modal.getOpponentColors());
        holder.roundScoreTV.setText(modal.getRoundScore());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateRoundActivity.class);

                // below we are passing all our values.
                i.putExtra("number", modal.getRoundNumber());
                i.putExtra("notes", modal.getRoundNotes());
                i.putExtra("colors", modal.getOpponentColors());
                i.putExtra("score", modal.getRoundScore());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return roundModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView roundNumberTV, roundNotesTV, opponentColorsTV, roundScoreTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            roundNumberTV = itemView.findViewById(R.id.idTVRoundNumber);
            roundNotesTV = itemView.findViewById(R.id.idTVRoundNotes);
            opponentColorsTV = itemView.findViewById(R.id.idTVOpponentColors);
            roundScoreTV = itemView.findViewById(R.id.idTVRoundScore);
        }
    }
}


