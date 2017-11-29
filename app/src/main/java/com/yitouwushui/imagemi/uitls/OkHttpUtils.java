package com.yitouwushui.imagemi.uitls;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yitouwushui
 */
public class OkHttpUtils {

    private static final long DEFAULT_MILLISECONDS = 10L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        // 避免一些字符自动转换为Unicode转义字符
        builder.disableHtmlEscaping();
        gson = builder.create();
    }

    public static Gson getGson() {
        return gson;
    }

    /**
     * 构造方法
     *
     * @param okHttpClient
     */
    private OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                    .build();
        } else {
            mOkHttpClient = okHttpClient;
        }

    }

    /**
     * 双重校验单例
     *
     * @param okHttpClient
     * @return
     */
    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        return initClient(null);
    }

    /**
     * Get请求
     *
     * @param url    url
     * @param params HashMap参数
     * @return
     */
    public Call get(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            sb.append("?");
            for (Map.Entry<String, String> entry : entrySet) {
                sb.append(entry.getKey());
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url + sb.toString()).get().build();
        return mOkHttpClient.newCall(request);
    }


    /**
     * Post 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public Call post(String url, Map<String, String> params) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        //构建请求参数form
        FormBody formBody = formBuilder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        return mOkHttpClient.newCall(request);
    }

}

