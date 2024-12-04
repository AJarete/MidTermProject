package com.example.midtermproject;

public class RoundModal {

    // variables for our round number,
    // description, tracks and duration, id.
    private String roundNumber;
    private String opponentColors;
    private String roundScore;
    private String roundNotes;
    private int id;

    // creating getter and setter methods
    public String getRoundNumber() { return roundNumber; }

    public void setRoundNumber(String roundNumber)
    {
        this.roundNumber = roundNumber;
    }

    public String getOpponentColors()
    {
        return opponentColors;
    }

    public void setOpponentColors(String opponentColors)
    {
        this.opponentColors = opponentColors;
    }

    public String getRoundScore() { return roundScore; }

    public void setRoundScore(String roundScore)
    {
        this.roundScore = roundScore;
    }

    public String getRoundNotes()
    {
        return roundNotes;
    }

    public void
    setRoundNotes(String roundNotes)
    {
        this.roundNotes = roundNotes;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public RoundModal(String roundNumber,
                       String opponentColors,
                       String roundScore,
                       String roundNotes)
    {
        this.roundNumber = roundNumber;
        this.opponentColors = opponentColors;
        this.roundScore = roundScore;
        this.roundNotes = roundNotes;
    }
}
