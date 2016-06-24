package org.hyb.demo.weixin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class InterpolatorActivity extends AppCompatActivity {
    Animation animation;
    ImageView ball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        animation= AnimationUtils.loadAnimation(this,R.anim.translate);
        ball=(ImageView)findViewById(R.id.ball);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(3000);
        animation.setFillAfter(true);
        ball.startAnimation(animation);
    }
}
