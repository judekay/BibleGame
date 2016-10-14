package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quizz.biblegame.R;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RulesActivity
  extends Activity
{
  public String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    DataInputStream localDataInputStream = new DataInputStream(paramInputStream);
    for (;;)
    {
      String str = localDataInputStream.readLine();
      if (str == null)
      {
        localDataInputStream.close();
        paramInputStream.close();
        return localStringBuffer.toString();
      }
      localStringBuffer.append(str + "\n");
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.rules);
    ((Button)findViewById(R.id.backBtn)).setOnClickListener(new OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        System.exit(0);
      }
    });
    InputStream localInputStream = getResources().openRawResource(R.raw.biblehelp);
    try
    {
      ((TextView)findViewById(R.id.rulesText)).setText(inputStreamToString(localInputStream));
      return;
    }
    catch (Exception localException)
    {
      Toast localToast = Toast.makeText(this, "File not readable", Toast.LENGTH_SHORT);
      localToast.setGravity(17, 0, 0);
      localToast.show();
    }
  }
}

