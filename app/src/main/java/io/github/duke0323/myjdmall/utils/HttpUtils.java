package io.github.duke0323.myjdmall.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class HttpUtils {

    public static String doGet(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String s = reader.readLine();
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doPost(String urlPath, HashMap<String, String> params) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //因为输出默认关闭需要特地打开
            conn.setDoOutput(true);
            String paramsStr = "";
            for (Map.Entry<String, String> infos : params.entrySet()) {
                paramsStr += ("&" + infos.getKey() + "=" + infos.getValue());
            }
            //截掉开头一个&
            paramsStr = paramsStr.substring(1);
            conn.getOutputStream().write(paramsStr.getBytes());
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String s = reader.readLine();
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
