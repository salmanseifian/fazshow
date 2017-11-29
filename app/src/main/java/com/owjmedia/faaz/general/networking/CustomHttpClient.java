package com.owjmedia.faaz.general.networking;

import android.content.Context;

import com.owjmedia.faaz.general.Global;
import com.owjmedia.faaz.general.utils.AppManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by salman on 11/29/17.
 */

public class CustomHttpClient extends OkHttpClient {

    public CustomHttpClient(String token) {
        interceptors().add(provideHeaderInterceptor());
    }

    private Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "?").build();
                return chain.proceed(request);
            }
        };
    }

}
