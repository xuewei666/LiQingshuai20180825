package com.bwei.liqingshuai.ui.weigth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class LiuSHi extends ViewGroup{

    public LiuSHi(Context context) {
        this(context,null);
    }

    public LiuSHi(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LiuSHi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //得到子布局
        int childCount = getChildCount();

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        //得到父控件的高
        measureChildren(measuredWidth,measuredHeight);
        //初始值
        int top = 0;
        int left = 0;
        int maxheigth = 0;
        for (int i = 0; i <childCount ; i++) {
            View v = getChildAt(i);
            maxheigth = Math.max(maxheigth,v.getMeasuredHeight());
            if (left+v.getMeasuredWidth()>measuredWidth){
                left =  0;
                top += maxheigth;
                maxheigth = 0;
            }
            v.layout(left,top,left+v.getMeasuredWidth(),top+v.getMeasuredHeight());
            left = left + v.getMeasuredWidth();
        }


    }
}
