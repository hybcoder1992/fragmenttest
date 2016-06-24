package org.hyb.demo.weixin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button wx_btn=(Button)findViewById(R.id.wx_btn);
        wx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WxFragment fragment=new WxFragment();
                //LinearLayout ll=(LinearLayout)findViewById(R.id.ll);
                getFragmentManager().beginTransaction().replace(R.id.ll,fragment).commit();
            }
        });
    }
}
