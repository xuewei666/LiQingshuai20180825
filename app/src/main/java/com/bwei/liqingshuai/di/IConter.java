package com.bwei.liqingshuai.di;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public interface IConter {

    interface IView{
        /**
         * 接受回调数据
         * @param string
         */
        void responseMsg(String string);
    }
    interface IPersenter<IView>{
        /**
         * 绑定
         */
        void attchview(IView iView);

        /**
         * 解除
         */
        void dttchview(IView iView);

        /**
         *
         * @param url
         */
        void requestData(String url);

    }
    interface IModel{
        interface OnCalkBack{
          void   responseData(String string);

        }

        void requestMag(String url, OnCalkBack onCalkBack);
    }
}
