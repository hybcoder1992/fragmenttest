package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setup1Activity extends SetupBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

    }

    @Override
    public void next_activity() {
        Intent intent=new Intent(Setup1Activity.this,Setup2Activity.class);
        startActivity(intent);
        finish();
        //执行平移动画
        //enterAnim新的界面进入动画
        //exitAnim旧的界面退出动画
        //在 startActivity(Intent) or finish()后执行
        overridePendingTransition(R.anim.setup_enter_next,R.anim.setup_exit_next);
    }

    @Override
    public void prev_activity() {

    }
}
