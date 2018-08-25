package com.bwei.liqingshuai.di;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class OkHttpUtils {

    public static  OkHttpUtils okHttpUtils ;


    OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(new MyIntercept()).build();
    private OkHttpUtils() {
        if (null == okHttpClient){
            synchronized (OkHttpUtils.class){
                if (null==okHttpClient){
                    okHttpClient = new OkHttpClient();
                }
            }
        }
    }


    public static OkHttpUtils getInstance() {
        if (null == okHttpUtils){
            synchronized (OkHttpUtils.class){
                if (null==okHttpUtils){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    public void get(String url,Callback callback){

        Request build = new Request.Builder().url(url).build();
        okHttpClient.newCall(build).enqueue(callback);

    }
    //自定义适配器
    class  MyIntercept implements Interceptor{


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request build = request.newBuilder().addHeader("source", "android").build();
            Response proceed = chain.proceed(build);
            return proceed;
        }
    }

}
