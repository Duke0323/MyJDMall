package io.github.duke0323.myjdmall.utils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求
 * okhttp
 * httpUrlConnecton
 * 单例模式
 * Created by ${Duke} on 2016/7/10.
 */
public class HttpUtils {
    private volatile static HttpUtils mHttpUtils;
    private OkHttpClient mHttpClient;

    private HttpUtils() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
    }

    public static HttpUtils getInstance() {
        if (mHttpUtils == null) {
            synchronized (HttpUtils.class) {
                if (mHttpUtils == null) {
                    mHttpUtils = new HttpUtils();
                }
            }
        }
        return mHttpUtils;
    }

    //okhttp3
    public String doGet(String urlPath) {
        Request request = new Request.Builder().get()
                .url(urlPath).build();

        try {
            Response response = mHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String doPost(String urlPath, LinkedHashMap<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> infos : params.entrySet()) {
            builder.add(infos.getKey(), infos.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(urlPath)
                .post(body)
                .build();
        Response response = null;
        try {
            response = mHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




    /*httpUrlConnection*/
    //                public static String doGet(String urlPath) {
    //                    try {
    //                        URL url = new URL(urlPath);
    //                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //                        conn.setRequestMethod("GET");
    //                        if (conn.getResponseCode() == 200) {
    //                            InputStream inputStream = conn.getInputStream();
    //                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    //                            String s = reader.readLine();
    //                            if (BuildConfig.DEBUG)
    //                                Log.d("HttpUtils", s);
    //                            return s;
    //                        }
    //                    } catch (Exception e) {
    //                        e.printStackTrace();
    //                    }
    //                    return "";
    //                }


    //            public static String doPost(String urlPath, HashMap<String, String> params) {
    //                try {
    //                    URL url = new URL(urlPath);
    //                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //                    conn.setRequestMethod("POST");
    //                    //因为输出默认关闭需要特地打开
    //                    conn.setDoOutput(true);
    //                    String paramsStr = "";
    //                    for (Map.Entry<String, String> infos : params.entrySet()) {
    //                        paramsStr += ("&" + infos.getKey() + "=" + infos.getValue());
    //                    }
    //                    //截掉开头一个&
    //                    paramsStr = paramsStr.substring(1);
    //                    if (BuildConfig.DEBUG)
    //                        Log.d("HttpUtilsparamsStr", paramsStr);
    //                    conn.getOutputStream().write(paramsStr.getBytes());
    //                    if (conn.getResponseCode() == 200) {
    //                        InputStream inputStream = conn.getInputStream();
    //                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    //                        String s = reader.readLine();
    //                        if (BuildConfig.DEBUG)
    //                            Log.d("HttpUtilsssss", s);
    //                        return s;
    //                    }
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //                return "";
    //            }

}
