package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setup2Activity extends SetupBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);


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
