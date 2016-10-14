package com.quizz.biblegame.quiz.biblegame.util;

import com.quizz.biblegame.quiz.biblegame.quiz.Question;

import java.util.Iterator;
import java.util.List;

public class Utility
{
  public static String capitalise(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    return paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
  }
  
  public static String getAnswers(List<Question> paramList)
  {
    int i = 1;
    StringBuilder localStringBuffer = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localStringBuffer.toString();
      }
      Question localQuestion = (Question)localIterator.next();
      localStringBuffer.append("Question").append(i).append(") ").append(localQuestion.getQuestion()).append("? \n");
      localStringBuffer.append("Answer: ").append(localQuestion.getAnswer()).append("\n\n");
      i++;
    }
  }
}

