package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setup3Activity extends SetupBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
    }


    @Override
    public void next_activity() {
        Intent intent=new Intent(Setup3Activity.this,Setup4Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void prev_activity() {
        Intent intent=new Intent(Setup3Activity.this,Setup2Activity.class);
        startActivity(intent);
        finish();
    }
}
