package org.hyb.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by hyb on 2016/7/13.
 */
public class BootCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("hyb","手机重启了");
        //检查sim卡是否发生变化
        SharedPreferences sp=context.getSharedPreferences("Config",Context.MODE_PRIVATE);
        String sp_sim=sp.getString("sim","");
        //电话管理者
        TelephonyManager tel=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        //获取sim卡绑定的电话号码 line1:双卡双待,在中国不太适合,运营商一般不会将sim卡和手机号码绑定
        //tel.getLine1Number();
        String sim=tel.getSimSerialNumber();//获取sim卡序列号,需要权限android.permission.READ_PHONE_STATE
        if(!TextUtils.isEmpty(sp_sim) && !TextUtils.isEmpty(sim))
        {
            if(!sim.equals(sp_sim))
            {
                //发送警告短信
                //短信管理者
                /**
                 *
                 * destinationAddress,收件人
                 scAddress,短信中心地址,一般为null
                 text,短信内容
                 sentIntent,是否发送成功
                 deliveryIntent 短信协议一般为null
                 * */
                SmsManager smsManager= SmsManager.getDefault();
                //发送短信需要权限android.permission.SEND_SMS
                smsManager.sendTextMessage("黄小波",null,"哈哈哈",null,null);

            }
        }


    }
}
