package com.bwei.liqingshuai.di.presenter;

import com.bwei.liqingshuai.di.IConter;
import com.bwei.liqingshuai.di.model.Model;

import java.lang.ref.WeakReference;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class Persenter implements IConter.IPersenter<IConter.IView> {

     IConter.IModel model;
     IConter.IView iView;
    private WeakReference<IConter.IModel> iModelWeakReference;
    private WeakReference<IConter.IView> iViewWeakReference;

    @Override
    public void attchview(IConter.IView iView) {
        this.iView = iView;
        //连接M层
        model = new Model();

        iModelWeakReference = new WeakReference<>(model);
        iViewWeakReference = new WeakReference<>(iView);


    }

    @Override
    public void dttchview(IConter.IView iView) {
        iModelWeakReference.clear();
        iViewWeakReference.clear();
    }

    @Override
    public void requestData(String url) {
        model.requestMag(url,new IConter.IModel.OnCalkBack() {
            @Override
            public void responseData(String string) {
                    iView.responseMsg(string);
            }
        });
    }
}
