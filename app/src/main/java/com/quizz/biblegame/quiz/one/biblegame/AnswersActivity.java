package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.quizz.biblegame.R;
import com.quizz.biblegame.quiz.biblegame.quiz.GamePlay;
import com.quizz.biblegame.quiz.biblegame.util.Utility;

public class AnswersActivity
  extends Activity
  implements OnClickListener
{
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      finish();
      return;
    }

  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.answers);
    GamePlay localGamePlay = ((BibleApplication)getApplication()).getCurrentGame();
    ((TextView)findViewById(R.id.answers)).setText(Utility.getAnswers(localGamePlay.getQuestions()));
    ((Button)findViewById(R.id.finishBtn)).setOnClickListener(this);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
//    return true;
  }
}

