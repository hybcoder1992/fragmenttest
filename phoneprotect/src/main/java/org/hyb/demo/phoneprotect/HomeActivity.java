package org.hyb.demo.phoneprotect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    GridView gridView;
    String [] items=new String[]{"手机防盗","通讯卫士","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"};
    int [] logos=new int[]{R.mipmap.safe,R.mipmap.callmsgsafe,R.mipmap.app,R.mipmap.taskmanager,R.mipmap.netmanager,
    R.mipmap.trojan,R.mipmap.sysoptimize,R.mipmap.atools,R.mipmap.settings};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView=(GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    case 8:
                        Intent intent=new Intent(HomeActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
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
