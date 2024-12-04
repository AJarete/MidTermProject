package com.example.midtermproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "rounddb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "myrounds";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our round number column
    private static final String NUMBER_COL = "number";

    // below variable id for our opponent colors column.
    private static final String COLOR_COL = "color";

    // below variable for our round notes column.
    private static final String NOTES_COL = "notes";

    // below variable is for our round score column.
    private static final String SCORE_COL = "score";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NUMBER_COL + " TEXT,"
                + COLOR_COL + " TEXT,"
                + NOTES_COL + " TEXT,"
                + SCORE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new round to our sqlite database.
    public void addNewRound(String roundNumber, String opponentColors, String roundNotes, String roundScore) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NUMBER_COL, roundNumber);
        values.put(COLOR_COL, opponentColors);
        values.put(NOTES_COL, roundNotes);
        values.put(SCORE_COL, roundScore);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the rounds.
    public ArrayList<RoundModal> readRounds() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorRounds = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<RoundModal> roundModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorRounds.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                roundModalArrayList.add(new RoundModal(
                        cursorRounds.getString(1),
                        cursorRounds.getString(4),
                        cursorRounds.getString(2),
                        cursorRounds.getString(3)));
            } while (cursorRounds.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorRounds.close();
        return roundModalArrayList;
    }

    // below is the method for updating our courses
    public void updateRound(String originalRoundNumber, String roundNumber, String roundNotes,
                            String roundScore, String opponentColors) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NUMBER_COL, roundNumber);
        values.put(COLOR_COL, opponentColors);
        values.put(NOTES_COL, roundNotes);
        values.put(SCORE_COL, roundScore);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "number=?", new String[]{originalRoundNumber});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteRound(String roundNumber) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "number=?", new String[]{roundNumber});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}


