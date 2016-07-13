package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public abstract class SetupBaseActivity extends AppCompatActivity {
    protected SharedPreferences sp;
//将每个界面上一步、下一步的操作抽取到父类中
    public void prev(View view)
    {
        prev_activity();
    }
    public void next(View view)
    {
        next_activity();
    }
    //下一步操作
    public abstract void next_activity();
    //上一步操作
    public abstract void prev_activity();

    protected GestureDetector gestureDetector;
    //在父类中直接对子类的返回键进行统一处理

    @Override
    public void onBackPressed() {
        prev_activity();
        super.onBackPressed();

    }
//坚挺手机物理按钮的点击事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //true:是可以屏蔽按键的事件
            //return true;
            prev_activity();
        }
        return super.onKeyDown(keyCode, event);
    }
    private class MyGestureListener  extends GestureDetector.SimpleOnGestureListener
    {
        /**
         * e1 按下的事件,保存有按下的坐标
         * e2 抬起的事件,保存有抬起的坐标
         * velocityX velocityY 速度,在轴上移动的速度
         * */

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float startX=e1.getRawX();//按下的x坐标
            float endX=e2.getRawX();//抬起的x坐标
            float startY=e1.getRawY();
            float endY=e2.getRawY();
            //判断是否斜滑
            if(Math.abs(startY-endY)>50){
                return false;
            }
            //下一步操作
            if(startX-endX>100){
                next_activity();
            }
            //上一步操作
            if(endX-startX>100)
            {
                prev_activity();
            }
            //true if the event is consumed, else false
            //true事件执行 ,false事件拦截
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("Config",MODE_PRIVATE);
        gestureDetector=new GestureDetector(this,new MyGestureListener());
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gestureDetector==null) Log.d("hyb","is null");
        //要想让手势识别器生效,必须将手势识别器注册到屏幕的触摸事件
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
