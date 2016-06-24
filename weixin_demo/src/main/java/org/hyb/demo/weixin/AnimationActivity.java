package org.hyb.demo.weixin;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Button walkBtn=(Button)findViewById(R.id.walk_btn);
        Button frameAnimBtn=(Button)findViewById(R.id.anim_btn);
        imgView=(ImageView)findViewById(R.id.imageView);
        walkBtn.setOnClickListener(this);
        frameAnimBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.walk_btn:

                imgView.setBackgroundResource(R.drawable.walk);
                AnimationDrawable animation=(AnimationDrawable) imgView.getBackground();
                animation.start();
                break;
            case R.id.anim_btn:
                AnimationDrawable frameAnim=new AnimationDrawable();
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d1), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d2), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d3), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d4), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d5), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d6), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d7), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d8), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d9), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d10), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d11), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d12), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d13), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d14), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d15), 100);

                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d16), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d17), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d18), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d19), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d20), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d21), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d22), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d23), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d24), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d25), 100);
                frameAnim.addFrame(getResources().getDrawable(R.mipmap.d26), 100);
                imgView.setBackgroundDrawable(frameAnim);
                frameAnim.start();
                break;
        }
    }


}
