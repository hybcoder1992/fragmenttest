package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public abstract class SetupBaseActivity extends AppCompatActivity {
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
}
