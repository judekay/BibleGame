package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Application;

import com.quizz.biblegame.quiz.biblegame.quiz.GamePlay;

public class BibleApplication
  extends Application
{
  private GamePlay currentGame;
  
  public GamePlay getCurrentGame()
  {
    return this.currentGame;
  }
  
  public void setCurrentGame(GamePlay paramGamePlay)
  {
    this.currentGame = paramGamePlay;
  }
}

