package com.bwei.liqingshuai.ui.weigth;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.liqingshuai.R;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class MyAddSubView extends LinearLayout implements View.OnClickListener {
    TextView tv_sub_view;
    TextView tv_number_view;
     TextView tv_add_view;
    private int number = 1;

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tv_number_view.setText(number+"");
    }

    public MyAddSubView(Context context) {
        super(context, null);
    }

    public MyAddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.layout_jia_jian_qi, this);
        tv_sub_view = inflate.findViewById(R.id.tv_sub_view);
        tv_number_view = inflate.findViewById(R.id.tv_number_view);
        tv_add_view = inflate.findViewById(R.id.tv_add_view);
        tv_sub_view.setOnClickListener(this);
        tv_add_view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sub_view:
                    --number;
                    tv_number_view.setText(number+"");
                    if (onNumberChangeLiser!=null){
                        onNumberChangeLiser.onNumberChang(number);

                    }
                break;
            case R.id.tv_add_view:
                ++number;
                tv_number_view.setText(number+"");
                if (onNumberChangeLiser!=null){
                    onNumberChangeLiser.onNumberChang(number);

                }
                break;

        }
    }
    OnNumberChangeLiser onNumberChangeLiser;

    public void setOnNumberChangeLiser(OnNumberChangeLiser onNumberChangeLiser) {
        this.onNumberChangeLiser = onNumberChangeLiser;
    }

    public interface  OnNumberChangeLiser{

        void onNumberChang(int number);
    }
}
