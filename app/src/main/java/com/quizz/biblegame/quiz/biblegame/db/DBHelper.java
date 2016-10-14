package com.quizz.biblegame.quiz.biblegame.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.quizz.biblegame.quiz.biblegame.quiz.Question;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{
  private static String DB_NAME = "myDatabase";
  private static String DB_PATH = "/data/data/com.quizz.biblegame/databases/";
  private final Context myContext;
  private SQLiteDatabase myDataBase;

  SQLiteDatabase localSQLiteDatabase1 = null;
  
  public DBHelper(Context paramContext)
  {
    super(paramContext, DB_NAME, null, 1);
    this.myContext = paramContext;
  }

  public void createDataBase() throws IOException{

    boolean dbExist = checkDataBase();
    if(!dbExist)
    {
      this.getReadableDatabase();

      try {
        copyDataBase();
      } catch (IOException e) {
        throw new Error("Error copying database");
      }
    }
  }

  private boolean checkDataBase(){
    SQLiteDatabase checkDB = null;
    try{
      String myPath = DB_PATH + DB_NAME;
      checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }catch(SQLiteException e){
      //database does't exist yet.
    }
    if(checkDB != null){
      checkDB.close();
    }

    return checkDB != null;
  }


  private void copyDataBase() throws IOException{
    //Open your local db as the input stream
    InputStream myInput = myContext.getAssets().open(DB_NAME);

    // Path to the just created empty db
    String outFileName = DB_PATH + DB_NAME;

    //Open the empty db as the output stream
    OutputStream myOutput = new FileOutputStream(outFileName);

    //transfer bytes from the inputfile to the outputfile
    byte[] buffer = new byte[1024];
    int length;
    while ((length = myInput.read(buffer))>0){
      myOutput.write(buffer, 0, length);
    }

    //Close the streams
    myOutput.flush();
    myOutput.close();
    myInput.close();

  }


  public void openDataBase() throws SQLException{
    //Open the database
    String myPath = DB_PATH + DB_NAME;
    myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
  }

  @Override
  public synchronized void close() {
    if(myDataBase != null)
      myDataBase.close();
    super.close();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }
  public List<Question> getQuestionSet(int difficulty, int numQ){
    List<Question> questionSet = new ArrayList<Question>();
    Cursor c = myDataBase.rawQuery("SELECT * FROM QUESTIONS WHERE DIFFICULTY=" + difficulty +
            " ORDER BY RANDOM() LIMIT " + numQ, null);
    while (c.moveToNext()){
      //Log.d("QUESTION", "Question Found in DB: " + c.getString(1));
      Question q = new Question();
      q.setQuestion(c.getString(1));
      q.setAnswer(c.getString(2));
      q.setOption1(c.getString(3));
      q.setOption2(c.getString(4));
      q.setOption3(c.getString(5));
      q.setRating(difficulty);
      questionSet.add(q);
    }
    c.close();
    return questionSet;
  }


  

}



