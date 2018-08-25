package com.bwei.liqingshuai.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwei.liqingshuai.R;
import com.bwei.liqingshuai.data.CartInfo;
import com.bwei.liqingshuai.di.IConter;
import com.bwei.liqingshuai.di.adapter.Myadapter;
import com.bwei.liqingshuai.di.presenter.Persenter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class GouWuCheActivity extends AppCompatActivity implements IConter.IView, View.OnClickListener {

    private IConter.IPersenter<IConter.IView> persenter;
    private String url = "https://www.zhaoapi.cn/product/getCarts?uid=71";
    private ExpandableListView idelc_show_mian;
    private CheckBox cb_allcheck_mian;
    private TextView btn_allprice_mian;
    private Button btn_allnubmer_mian;
    private Myadapter myadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gouwuche);
        initView();
        //连接P层
        persenter = new Persenter();
        //绑定
        persenter.attchview(this);

        persenter.requestData(url);
    }

    @Override
    public void responseMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Log.d("aaa", string);
                CartInfo cartInfo = gson.fromJson(string, CartInfo.class);
                List<CartInfo.DataBean> sellerData = cartInfo.getData();

                //创建适配器
                myadapter = new Myadapter(sellerData);
                idelc_show_mian.setAdapter(myadapter);
                myadapter.setOnCartListChangeListest(new Myadapter.OnCartListChangeListest() {
                    @Override
                    public void sellerSelectedChange(int groupPosition) {
                        boolean cuurllerAllSelected = myadapter.isCuurllerAllSelected(groupPosition);
                        myadapter.ChangeCuurentSellerAll(groupPosition,!cuurllerAllSelected);
                        myadapter.notifyDataSetChanged();
                        shuaxinF2();
                    }


                    @Override
                    public void productNumberChange(int groupPosition, int childPosition, int number) {
                        myadapter.changeNumder(groupPosition,childPosition,number);
                        myadapter.notifyDataSetChanged();
                        shuaxinF2();
                    }
                });
                for (int i = 0; i <sellerData.size() ; i++) {
                        idelc_show_mian.expandGroup(i);
                }
            }
        });


    }

    //刷新下部的方法
    public void shuaxinF2(){
        boolean allPresentSelected = myadapter.isAllPresentSelected();
        cb_allcheck_mian.setChecked(allPresentSelected);
        double jiaGe = myadapter.JiSuanJiaGe();
        btn_allprice_mian.setText("总价:￥"+jiaGe);
        int suanshuliang = myadapter.JiSuanshuliang();
        btn_allnubmer_mian.setText("去结算("+suanshuliang+")");
    }
    //销毁时解除绑定

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.dttchview(this);
    }

    private void initView() {
        idelc_show_mian = (ExpandableListView) findViewById(R.id.idelc_show_mian);
        cb_allcheck_mian = (CheckBox) findViewById(R.id.cb_allcheck_mian);
        btn_allprice_mian = (TextView) findViewById(R.id.btn_allprice_mian);
        btn_allnubmer_mian = (Button) findViewById(R.id.btn_allnubmer_mian);
        cb_allcheck_mian.setOnClickListener(this);
        btn_allnubmer_mian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_allnubmer_mian:

                break;
            case R.id.cb_allcheck_mian:
                boolean allPresentSelected = myadapter.isAllPresentSelected();
                myadapter.changeAllPresentSelected(!allPresentSelected);
                myadapter.notifyDataSetChanged();
                shuaxinF2();
                break;
        }
    }
}
