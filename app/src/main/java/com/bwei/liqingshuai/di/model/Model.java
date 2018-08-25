package com.bwei.liqingshuai.di.model;

import com.bwei.liqingshuai.di.IConter;
import com.bwei.liqingshuai.di.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class Model implements IConter.IModel {

    @Override
    public void requestMag(String url, final OnCalkBack onCalkBack) {
        OkHttpUtils.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String string = e.getMessage().toString();
                onCalkBack.responseData(string);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                onCalkBack.responseData(string);
            }
        });

    }
}
