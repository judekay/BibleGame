package com.quizz.biblegame.quiz.one.biblegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.quizz.biblegame.R;

public class BibleSplashActivity
  extends Activity
{
  private void startAnimating()
  {
    TextView localTextView = (TextView)findViewById(R.id.textView1);
    Animation localAnimation1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
    localTextView.startAnimation(localAnimation1);
    ((TextView)findViewById(R.id.textView2)).startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in2));
    localAnimation1.setAnimationListener(new AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        BibleSplashActivity.this.startActivity(new Intent(BibleSplashActivity.this, SplashActivity.class));
        BibleSplashActivity.this.finish();
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    Animation localAnimation2 = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
    new LayoutAnimationController(localAnimation2);
    ((ImageView)findViewById(R.id.imageView1)).startAnimation(localAnimation2);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.bible_splash);
    startAnimating();
  }
  
  protected void onPause()
  {
    super.onPause();
    ((TextView)findViewById(R.id.textView1)).clearAnimation();
    ((TextView)findViewById(R.id.textView2)).clearAnimation();
    ((ImageView)findViewById(R.id.textView3)).clearAnimation();
  }
  
  protected void onResume()
  {
    super.onResume();
    startAnimating();
  }
}


