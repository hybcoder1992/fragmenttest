package org.hyb.demo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.hyb.demo.phoneprotect.R;

/**
 * TODO: document your custom view class.
 */
public class HomeTextView extends TextView {
    //在代码中调用
    public HomeTextView(Context context) {
        super(context);
    }
    //在布局文件中使用时调用
    public HomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置自定义的TextView自动获取焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
