package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.hyb.demo.utils.MD5Util;

import java.security.MessageDigest;

public class HomeActivity extends AppCompatActivity {
    int count=2;
    GridView gridView;
    String [] items=new String[]{"手机防盗","通讯卫士","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"};
    int [] logos=new int[]{R.mipmap.safe,R.mipmap.callmsgsafe,R.mipmap.app,R.mipmap.taskmanager,R.mipmap.netmanager,
    R.mipmap.trojan,R.mipmap.sysoptimize,R.mipmap.atools,R.mipmap.settings};
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp=getSharedPreferences("Config",MODE_PRIVATE);
        gridView=(GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    //手机防盗
                    case 0:
                        //判断用户是第一次点击的话,设置密码,设置成功再次点击输入密码,密码正确才能进入手机防盗模块
                        if(!TextUtils.isEmpty(sp.getString("pwd","")))
                        {
                            showEnterPwdDialog();
                        }
                        else
                        {
                            showSetPwdDialog();
                        }

                        break;
                    //设置中心
                    case 8:
                        Intent intent=new Intent(HomeActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    /**
     *显示输入密码对话框
     * */
    private void showEnterPwdDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("输入密码");
        builder.setCancelable(false);
        View view=View.inflate(getApplicationContext(),R.layout.dialog_enter_pwd,null);
        ImageView hide_btn=(ImageView)view.findViewById(R.id.enter_pwd_hide);
        Button btn_enter=(Button)view.findViewById(R.id.btn_enter_pwd);
        Button btn_cancel=(Button)view.findViewById(R.id.btn_cancel_enter);
        final EditText et_enter_pwd=(EditText)view.findViewById(R.id.et_enter_pwd);
        hide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count % 2==0)//显示密码
                {
                    et_enter_pwd.setInputType(0);
                }
                else//隐藏密码
                {
                    et_enter_pwd.setInputType(129);
                }
                count++;
            }
        });
        //builder.setView(view);
        final AlertDialog dialog=builder.create();
        /**
         * int viewSpacingLeft:距离左边框距离
            int viewSpacingTop:距离上边框距离
            int viewSpacingRight:距离右边框距离
            int viewSpacingBottom:距离底边框距离
         * */
        dialog.setView(view,0,0,0,0);//这个方法和builder.setView(view)功能一致

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enter_pwd=et_enter_pwd.getText().toString().trim();
                if(TextUtils.isEmpty(enter_pwd))
                {
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取保存的密码
                String save_pwd=sp.getString("pwd","");

                if(save_pwd.equals(MD5Util.MD5(enter_pwd)))
                {
                    //跳转到手机防盗界面
                    Intent intent=new Intent(HomeActivity.this,LostFindActivity.class);
                    startActivity(intent);

                    //隐藏对话框
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"密码输入正确",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"密码错误,请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
/**
 * 显示设置密码对话框
 * */
    private void showSetPwdDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("设置密码");
        builder.setCancelable(false);
        //将布局文件转成View对象
        View view=View.inflate(getApplicationContext(),R.layout.dialog_password,null);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        Button cancel_btn=(Button) view.findViewById(R.id.cancel_btn);
        Button confirm_btn=(Button)view.findViewById(R.id.confirm_btn);
        final EditText et_pwd=(EditText)view.findViewById(R.id.et_password);
        final EditText et_pwd_confirm=(EditText)view.findViewById(R.id.et_password_confirm);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏对话框
                dialog.dismiss();
            }
        });
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd=et_pwd.getText().toString().trim();

                //判断密码是否为空
                if(TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd_confirm=et_pwd_confirm.getText().toString().trim();
                if(TextUtils.isEmpty(pwd_confirm) || pwd.equals(pwd_confirm))
                {

                    //保存密码
                    //获取数据摘要器

                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString("pwd", MD5Util.MD5(pwd));
                    editor.commit();
                    editor.apply();
                    //隐藏对话框
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"密码设置完成",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            //将布局文件转成View对象
            View item_view= View.inflate(getApplicationContext(),R.layout.item_home,null);

            ImageView icon=(ImageView)item_view.findViewById(R.id.item_icon);
            TextView name=(TextView)item_view.findViewById(R.id.item_name);
            icon.setImageResource(logos[position]);
            name.setText(items[position]);

            return item_view;
        }
    }
}
