package org.hyb.demo.phoneprotect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.hyb.demo.utils.StreamUtils;
import org.json.JSONObject;


import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final int MSG_UPDATE_DIALOG=1;
    public static final int MSG_ENTER_HOME=2;
    public static final int MSG_SERVICE_ERROR=3;
    long startTime=0;
    String code;
    String apkurl;
    String desc;
    TextView tv_splash_plan;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case MSG_UPDATE_DIALOG:
                    showDialog();
                    //弹出对话框
                break;
                case MSG_ENTER_HOME:
                    enterHomeActivity();
                    break;
                case MSG_SERVICE_ERROR:
                    Toast.makeText(getApplicationContext(),"网络异常",Toast.LENGTH_SHORT).show();
                    enterHomeActivity();
                    break;
            }
        }
    };
    /**
     * 安装最新版本
     * */
    private void installApk()
    {
        /**
         * <intent-filter>
             <action android:name="android.intent.action.VIEW" />
             <category android:name="android.intent.category.DEFAULT" />
             <data android:scheme="content" /> //content : 从内容提供者中获取数据  content://
             <data android:scheme="file" /> // file : 从文件中获取数据
             <data android:mimeType="application/vnd.android.package-archive" />
            </intent-filter>
         * */
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        /*
        //单独设置会相互覆盖
        intent.setData(Uri.fromFile(new File("/mnt/sdcard/test.apk")));
        intent.setType("application/vnd.android.package-archive");
        */
        intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/test.apk")),"application/vnd.android.package-archive");
        //startActivity(intent);
        //requestCode用来区分intent是从哪个activity传递过来的
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        enterHomeActivity();
    }

    /**
     * 下载最新版本
     * */
    private void download()
    {
        //判断sd卡是否挂载
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            HttpUtils httpUtils=new HttpUtils();
            //url:新版本下载的路径
            //target:保存新版本的目录
            //callback:RequestCallback

            httpUtils.download(apkurl, "/mnt/sdcard/test.apk", new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    //安装最新版本
                    installApk();
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Log.d("hyb",e.getMessage());
                }
                //显示当前下载进度操作
                //total:下载总进度
                //current:当前下载进度
                //isUploading:是否支持断点续传
                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    tv_splash_plan.setVisibility(View.VISIBLE);
                    tv_splash_plan.setText(current+"/"+total);

                }
            });
        }


    }
    private void showDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //设置点击对话框空白处也不会消失
        builder.setCancelable(false);
        builder.setTitle("新版本:"+code);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(desc);
        //设置升级取消的按钮
        builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 //下载最新版本
                download();
            }
        });
        //设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //隐藏对话框
                dialogInterface.dismiss();
                //跳转到主界面
                enterHomeActivity();
            }
        });
        //显示对话框
        builder.create().show();
    }
    /**
     * 跳转到主界面
     * */
    private void enterHomeActivity()
    {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView versionName=(TextView)findViewById(R.id.version);
        tv_splash_plan=(TextView)findViewById(R.id.tv_splash_plan);
        versionName.setText("版本号:"+getVersionName());
        SharedPreferences sp=getSharedPreferences("Config",MODE_PRIVATE);

        if(sp.getBoolean("update",true))
        {

            Update();
        }
        else{
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(2000);//先睡2秒钟,不能让主线程sleep,主线程有个渲染界面的操作,主线程sleep就没有办法渲染界面
                    enterHomeActivity();
                }
            }.start();

        }
    }
    /**
     * 提醒用户跟新版本
     * */
    private void Update()
    {
        //1.连接服务器，查看是否有最新版本.4.0以后耗时操作不能在主线程中执行，放到子线程
        new Thread(){
            @Override
            public void run() {
                Message message=Message.obtain();
                //在连接之前获取一个时间
                startTime=System.currentTimeMillis();
                try
                {
                    //1.连接服务器，设置连接路径
                    URL url=new URL("http://hybcode-hyb.stor.sinaapp.com/infotest.json");
                    //获取连接操作
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    //设置超时时间
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);//设置读取超时时间
                    //设置请求方式
                    conn.setRequestMethod("GET");
                    //获取服务器返回的状态码,200,404,500
                    int responseCode=conn.getResponseCode();
                    if(responseCode==200)
                    {
                        //连接成功,获取服务器返回的数据,code(新的版本号),apkurl(新版本的下载路径),desc(描述信息,跟新了什么功能)
                        //获取流数据
                        InputStream in= conn.getInputStream();
                        String json= StreamUtils.parserStreamUtil(in);
                        //解析json
                        JSONObject jo=new JSONObject(json);
                        //获取数据
                        //{"code":"2.0","apkurl":"xxx","desc":"新版本上线了"}
                        code=jo.getString("code");
                        apkurl=jo.getString("apkurl");
                        desc=jo.getString("desc");
                        Log.d("hyb",code+apkurl+desc);
                        if(code.equals(getVersionName()))//没有最新版本
                        {
                            message.what=MSG_ENTER_HOME;
                        }
                        else
                        {
                            //弹出对话框提醒用户跟新版本
                            message.what=MSG_UPDATE_DIALOG;
                        }
                    }
                    else
                    {
                        //连接失败
                        message.what=MSG_SERVICE_ERROR;
                    }
                }catch (Exception e)
                {
                    message.what=MSG_SERVICE_ERROR;
                    e.printStackTrace();
                }finally {
                    long endTime=System.currentTimeMillis();
                    long dTime=endTime - startTime;
                    if(dTime<2000)
                        SystemClock.sleep(2000-dTime);//先睡2秒钟
                    handler.sendMessage(message);
                }


            }
        }.start();
    }
    /**
     * 获取当前版本号
     * */
    private String getVersionName()
    {
        //包的管理者,获取清单文件中的所有者
        PackageManager pm=getPackageManager();
        //packageName:应用程序包名
        //flag:指定信息的标签,0(获取基础的信息，比如包名，版本号)
        //getPackageName()获取包名
        PackageInfo info;
        try
        {
            info = pm.getPackageInfo(getPackageName(), 0);
            Log.d("hyb",info.versionName);
            return info.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
