package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LostFindActivity extends AppCompatActivity {
SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("Config",MODE_PRIVATE);
        //分为两部分1、显示设置过的防盗功能2、设置手机防盗
        //判断用户是否第一次进入手机防盗模块,是,跳转到设置向导界面,不是,跳转到防盗功能显示界面
        if(sp.getBoolean("first",true))
        {
            //第一次进入
            Intent intent=new Intent(this,Setup1Activity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            setContentView(R.layout.activity_lost_find);
        }
        TextView tv_reset=(TextView)findViewById(R.id.tv_reset);
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LostFindActivity.this,Setup1Activity.class);
                startActivity(intent);
            }
        });
    }
}
