package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.quizz.biblegame.R;
import com.quizz.biblegame.quiz.biblegame.quiz.GamePlay;
import com.quizz.biblegame.quiz.biblegame.quiz.Question;
import com.quizz.biblegame.quiz.biblegame.util.Utility;

import java.util.List;

public class QuestionActivity extends Activity implements OnClickListener
{
  private GamePlay currentGame;
  private Question currentQ;
  TextView textView;
  private Button prevbut;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.question);

    textView = (TextView) findViewById(R.id.questionnumber);
    currentGame = ((BibleApplication)getApplication()).getCurrentGame();
    currentQ = currentGame.getQuestion(currentGame.questionNum);
//    textView.setText(currentGame.getRound());
    Button nextBtn = (Button) findViewById(R.id.nextBtn);
    prevbut = (Button) findViewById(R.id.prevBtn);
    nextBtn.setOnClickListener(this);
    prevbut.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if(currentGame.isGameOver()){
          Intent i = new Intent(QuestionActivity.this, EndgameActivity.class);
          startActivity(i);
          finish();
        }else{

          if(currentGame.getRound() < 1){
            Toast.makeText(getApplicationContext(), "Oh yes we are here", Toast.LENGTH_SHORT).show();
          }
          else {
//          currentGame.questionNum += 1;
            currentQ = currentGame.getPrevQuestion();
            currentGame.decrementQuestionNum();
            setQuestions();
          }

        }
      }
    });
    setQuestions();

  }


  /**
   * Method to set the text for the question and answers from the current games
   * current question
   */
  private void setQuestions() {
    String questionnumber = String.valueOf(currentGame.getRound()+1) + "of 20";
    textView.setText(questionnumber);
    //set the question text from current question
    String question = Utility.capitalise(currentQ.getQuestion()) + "?";
    TextView qText = (TextView) findViewById(R.id.question);
    qText.setText(question);

    //set the available options
    List<String> answers = currentQ.getQuestionOptions();
    TextView option1 = (TextView) findViewById(R.id.answer1);
    option1.setText(Utility.capitalise(answers.get(0)));

    TextView option2 = (TextView) findViewById(R.id.answer2);
    option2.setText(Utility.capitalise(answers.get(1)));

    TextView option3 = (TextView) findViewById(R.id.answer3);
    option3.setText(Utility.capitalise(answers.get(2)));

    TextView option4 = (TextView) findViewById(R.id.answer4);
    option4.setText(Utility.capitalise(answers.get(3)));
  }

  @Override
  public void onClick(View arg0) {

    if(arg0.getId() == R.id.nextBtn) {
      if (!checkAnswer()) return;
      if (currentGame.isGameOver()) {
        Intent i = new Intent(this, EndgameActivity.class);
        startActivity(i);
        finish();
      } else {


//        Toast.makeText(getApplicationContext(), currentGame.getRound()+"",Toast.LENGTH_LONG).show();

        if(currentGame.getRound() >= 19){
          Toast.makeText(getApplicationContext(), "Oh yes we are here", Toast.LENGTH_SHORT).show();
        }
        else {
//          currentGame.questionNum += 1;
          currentQ = currentGame.getNextQuestion();
          currentGame.incrementQuestionNum();
          setQuestions();
        }
      }
    }

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


  private boolean checkAnswer() {
    String answer = getSelectedAnswer();
    if (answer==null){
      return false;
    }
    else {
      if (currentQ.getAnswer().equalsIgnoreCase(answer))
      {
        currentGame.incrementRightAnswers();
      }
      else{
        currentGame.incrementWrongAnswers();
      }
      return true;
    }
  }

  private String getSelectedAnswer() {
    RadioButton c1 = (RadioButton)findViewById(R.id.answer1);
    RadioButton c2 = (RadioButton)findViewById(R.id.answer2);
    RadioButton c3 = (RadioButton)findViewById(R.id.answer3);
    RadioButton c4 = (RadioButton)findViewById(R.id.answer4);
    if (c1.isChecked())
    {
      return c1.getText().toString();
    }
    if (c2.isChecked())
    {
      return c2.getText().toString();
    }
    if (c3.isChecked())
    {
      return c3.getText().toString();
    }
    if (c4.isChecked())
    {
      return c4.getText().toString();
    }

    return null;
  }
}

