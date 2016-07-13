package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import org.hyb.demo.ui.SettingView;

public class Setup2Activity extends SetupBaseActivity {
    private SettingView settingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        settingView=(SettingView)findViewById(R.id.sv_setting_sim);
        /*
        if(!TextUtils.isEmpty(sp.getString("sim","")))
        {
            settingView.setCheckBox(true);
        }
        else {
            settingView.setCheckBox(false);
        }
        */
        settingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingView.isChecked())
                {
                    //解绑
                    settingView.setCheckBox(false);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("sim","");
                    editor.commit();
                }
                else
                {
                    //绑定
                    //电话管理者
                    TelephonyManager tel=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
                    //获取sim卡绑定的电话号码 line1:双卡双待,在中国不太适合,运营商一般不会将sim卡和手机号码绑定
                    //tel.getLine1Number();
                    String sim=tel.getSimSerialNumber();//获取sim卡序列号,需要权限android.permission.READ_PHONE_STATE
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("sim",sim);
                    editor.commit();
                    settingView.setCheckBox(true);
                }
            }
        });

    }
    @Override
    public void next_activity() {
        Intent intent=new Intent(Setup2Activity.this,Setup3Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void prev_activity() {
        Intent intent=new Intent(Setup2Activity.this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }

}
