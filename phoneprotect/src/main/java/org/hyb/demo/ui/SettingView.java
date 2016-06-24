package org.hyb.demo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.hyb.demo.phoneprotect.R;

/**
 * Created by hyb on 2016/6/24.
 */
public class SettingView  extends RelativeLayout{
    TextView title_tv;
    TextView desc_tv;
    CheckBox update_cb;
    public SettingView(Context context) {
        super(context);
        init();
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        View view= View.inflate(getContext(), R.layout.setting_view,this);
        //初始化标题、描述信息、checkbox控件
        title_tv=(TextView)view.findViewById(R.id.tv_setting_title);
        desc_tv=(TextView)view.findViewById(R.id.tv_setting_desc);
        update_cb=(CheckBox)view.findViewById(R.id.cb_setting_update);

    }
    //添加一些方法,使程序员能方便的去改变自定义控件中俄控件的值
    /**
     * 设置标题
     * */
    public void setTitle(String title)
    {
        title_tv.setText(title);
    }
    public void setDesc(String desc)
    {
        desc_tv.setText(desc);
    }
    public void setCheckBox(boolean isChecked)
    {
        update_cb.setChecked(isChecked);
    }
    public boolean isChecked()
    {
        return update_cb.isChecked();
    }
}
