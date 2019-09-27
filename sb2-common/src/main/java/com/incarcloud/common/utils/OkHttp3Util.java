package com.incarcloud.common.utils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp3工具类
 *
 * @author Aaric, created on 2019-09-06T14:28.
 * @since 0.13.0-SNAPSHOT
 */
@Slf4j
public class OkHttp3Util {

    /**
     * 默认请求超时时间5秒
     */
    public static final int DEFAULT_NETWORK_TIMEOUT = 5;

    /**
     * GET请求
     *
     * @param url        请求地址
     * @param parameters 请求参数
     * @return
     */
    public static String get(@NonNull String url, Map<String, String> parameters) throws Exception {
        // 异步网络请求
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                // 构建Request对象
                Request request = new Request.Builder()
                        .url(httpUrl(url, parameters))
                        .get()
                        .build();

                // 返回请求结果
                Response response = okHttpClient().newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                log.error("get error, ", e);
            }
            return null;
        });

        return future.get(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * 获得OkHttpClient对象
     *
     * @return
     */
    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建HttpUrl对象
     *
     * @param url        请求地址
     * @param parameters 请求参数
     * @return
     */
    private static HttpUrl httpUrl(@NonNull String url, Map<String, String> parameters) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (null != parameters) {
            for (Map.Entry<String, String> param : parameters.entrySet()) {
                builder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        return builder.build();
    }
}
