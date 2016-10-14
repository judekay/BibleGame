package com.quizz.biblegame.quiz.biblegame.quiz;

public class Helper
{
  private static int calculatePercentage(int paramInt1, int paramInt2)
  {
    return (int)(100.0D * (paramInt1 / paramInt2));
  }
  
  public static String getResultComment(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = calculatePercentage(paramInt1, paramInt2);
    switch (paramInt3)
    {
    default: 
      if (i > 90) {
        return "Awesome!";
      }
      break;
    case 2: 
      if (i > 90) {
        return "Waoh, What a great score";
      }
      if (i >= 80) {
        return "Nice Score";
      }
      if (i >= 60) {
        return "Not too bad";
      }
      if (i >= 40) {
        return "Well, don't give up..";
      }
      return "Try to read your bible very well";
    }
    if (i >= 80) {
      return "Nice Score";
    }
    if (i >= 60) {
      return "Not too bad..";
    }
    if (i >= 40) {
      return "Well, don't give up";
    }
    return "Try to read your bible very well";
  }
  
  public static int getResultImage(int paramInt1, int paramInt2, int paramInt3)
  {
    calculatePercentage(paramInt1, paramInt2);
    return paramInt1;
  }
}


