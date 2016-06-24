package org.hyb.demo.phoneprotect;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.hyb.demo.ui.SettingView;

public class SettingActivity extends AppCompatActivity {
    SettingView sv_setting_update;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_setting);
        sp=getSharedPreferences("Config",MODE_PRIVATE);
        sv_setting_update=(SettingView)findViewById(R.id.sv_setting_update);
        //sv_setting_update.setTitle("提示跟新");
        if(sp.getBoolean("update",true))
        {
            //sv_setting_update.setDesc("打开提示更新");
            sv_setting_update.setCheckBox(true);
        }
        else
        {
            //sv_setting_update.setDesc("关闭提示更新");
            sv_setting_update.setCheckBox(false);
        }
        sv_setting_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sp.edit();
                //更改状态
                if(sv_setting_update.isChecked())
                {
                    //sv_setting_update.setDesc("关闭提示更新");
                    sv_setting_update.setCheckBox(false);
                    //保存状态

                    editor.putBoolean("update",false);
                }
                else {
                    //sv_setting_update.setDesc("打开提示更新");
                    sv_setting_update.setCheckBox(true);
                    //保存状态
                    editor.putBoolean("update",true);
                }
                editor.commit();
                editor.apply();//保存到文件中,但仅限于api9之上,api9以下保存到内存中
            }
        });
    }
}
