package com.owjmedia.faaz.general.networking;

import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.Global;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Injector {

    private static Retrofit retrofit = null;

    public static Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addInterceptor(provideHeaderInterceptor())
                .cache(provideCache())
                .build();
    }

    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(Global.getInstance().getCacheDir(), "http-cache"),
                    10 * 1024 * 1024);
        } catch (Exception e) {

        }

        return cache;
    }

    private static Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                if (Global.isLogin()) {
                    builder.addHeader("Authorization", Global.getToken());
                }
                return chain.proceed(builder.build());
            }
        };
    }

    private static Interceptor provideOfflineCacheInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Global.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }

        };
    }

    public static ApiService provideApiService() {
        return provideRetrofit().create(ApiService.class);
    }
}
