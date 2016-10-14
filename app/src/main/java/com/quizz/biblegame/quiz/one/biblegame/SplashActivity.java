package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.quizz.biblegame.R;
import com.quizz.biblegame.quiz.biblegame.db.DBHelper;
import com.quizz.biblegame.quiz.biblegame.quiz.GamePlay;
import com.quizz.biblegame.quiz.biblegame.quiz.Question;

import java.io.IOException;
import java.util.List;

public class SplashActivity extends Activity implements OnClickListener
{
  Button play;
  Button rules;
  Button abt;
  Button settings;
  Button exit;
  DBHelper dbHelper;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.welcome);
    dbHelper = new DBHelper(getApplicationContext());
    play = (Button) findViewById(R.id.playBtn);
    rules = (Button) findViewById(R.id.rulesBtn);
    abt = (Button) findViewById(R.id.abtbut);
    settings = (Button) findViewById(R.id.settingsBtn);
    exit = (Button) findViewById(R.id.exitBtn);
    play.setOnClickListener(this);
    rules.setOnClickListener(this);
    abt.setOnClickListener(this);
    settings.setOnClickListener(this);
    exit.setOnClickListener(this);

  }

  private int getDifficultySettings()
  {
    return getSharedPreferences("SETTINGS", 0).getInt("DIFFICULTY", 2);
  }
  
  private int getNumQuestions()
  {
    return getSharedPreferences("SETTINGS", 0).getInt("NUM_ROUNDS", 20);
  }
  
  /* Error */
  private List<Question> getQuestionSetFromDb() throws Error {
    int diff = getDifficultySettings();
    int numQuestions = getNumQuestions();
    DBHelper myDbHelper = new DBHelper(this);
    try {
      myDbHelper.createDataBase();
    } catch (IOException ioe) {
      throw new Error("Unable to create database");
    }
    try {
      myDbHelper.openDataBase();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      throw sqle;
    }
    List<Question> questions = myDbHelper.getQuestionSet(diff, numQuestions);
    myDbHelper.close();
    return questions;
  }

  public void onClick(View paramView)
  {
      switch (paramView.getId())
      {
        case R.id.playBtn:
          List<Question> questions = getQuestionSetFromDb();
          GamePlay localGamePlay = new GamePlay();
          localGamePlay.setQuestions(questions);
          localGamePlay.setNumRounds(getNumQuestions());
          ((BibleApplication)getApplication()).setCurrentGame(localGamePlay);
          startActivityForResult(new Intent(this, QuestionActivity.class), 1);
          break;
        case R.id.abtbut:
          startActivityForResult(new Intent(this, AboutActivity.class), 3);
          break;
        case R.id.settingsBtn:
          startActivityForResult(new Intent(this, SettingsActivity.class), 4);
          break;
        case R.id.rulesBtn:
          startActivityForResult(new Intent(this, RulesActivity.class), 2);
          break;
        case R.id.exitBtn:
          finish();
          break;


      }


  }
  

}

