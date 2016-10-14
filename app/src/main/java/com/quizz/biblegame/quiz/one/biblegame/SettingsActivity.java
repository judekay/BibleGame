package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

import com.quizz.biblegame.R;

public class SettingsActivity
  extends Activity
  implements OnClickListener
{
RadioButton localradiobutton;
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.settings);
    localradiobutton = (RadioButton) findViewById(R.id.mediumSetting);
    ((Button)findViewById(R.id.nextBtn)).setOnClickListener(this);
    updateButtonWithPreferences();
  }
  private boolean checkSelected()
  {
    return ((RadioButton)findViewById(R.id.mediumSetting)).isChecked();
  }
  
  private int getSelectedSetting()
  {
    //((RadioButton)findViewById(R.id.mediumSetting));
    if (localradiobutton.isChecked()) {}
    return 2;
  }
  
  private void updateButtonWithPreferences()
  {
    RadioButton localRadioButton = (RadioButton)findViewById(R.id.mediumSetting);
    switch (getSharedPreferences("SETTINGS", 0).getInt("DIFFICULTY", 2))
    {
    default:
      localRadioButton.toggle();
      return;
    }

  }
  
  public void onClick(View paramView)
  {
    if (!checkSelected()) {
      return;
    }
    Editor localEditor = getSharedPreferences("SETTINGS", 0).edit();
    localEditor.putInt("DIFFICULTY", getSelectedSetting());
    localEditor.commit();
    finish();
  }
  

}


