package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Setup4Activity extends SetupBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
    }

    @Override
    public void prev_activity() {

    }

    @Override
    public void next_activity() {

        SharedPreferences.Editor editor= sp.edit();
        editor.putBoolean("first",false);
        editor.commit();
        Intent intent=new Intent(this,LostFindActivity.class);
        startActivity(intent);
        finish();
    }
}
