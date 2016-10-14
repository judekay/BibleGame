package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.quizz.biblegame.R;
import com.quizz.biblegame.quiz.biblegame.quiz.Constants;
import com.quizz.biblegame.quiz.biblegame.quiz.GamePlay;
import com.quizz.biblegame.quiz.biblegame.quiz.Helper;

public class EndgameActivity
  extends Activity
  implements OnClickListener
{

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.endgame);
    GamePlay localGamePlay = ((BibleApplication)getApplication()).getCurrentGame();
    String str1 = "You Got " + localGamePlay.getRight() + "/" + localGamePlay.getNumRounds() + ".. ";
    String str2 = Helper.getResultComment(localGamePlay.getRight(), localGamePlay.getNumRounds(), getDifficultySettings());
    ((TextView)findViewById(R.id.endgameResult)).setText(str1 + str2);
    ((Button)findViewById(R.id.finishBtn)).setOnClickListener(this);
    ((Button)findViewById(R.id.answerBtn)).setOnClickListener(this);
  }
  private int getDifficultySettings()
  {
    return getSharedPreferences("SETTINGS", 0).getInt("DIFFICULTY", 2);
  }


  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    switch (keyCode)
    {
      case KeyEvent.KEYCODE_BACK :
        return true;
    }

    return super.onKeyDown(keyCode, event);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.finishBtn :
        finish();
        break;

      case R.id.answerBtn :
        Intent i = new Intent(this, AnswersActivity.class);
        startActivityForResult(i, Constants.PLAYBUTTON);
        break;
    }
  }
}

