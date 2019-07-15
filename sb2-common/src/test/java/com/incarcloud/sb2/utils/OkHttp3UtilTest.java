package com.incarcloud.sb2.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * OkHttp3UtilTest
 *
 * @author Aaric, created on 2019-07-15T14:43.
 * @since 0.4.2-SNAPSHOT
 */
public class OkHttp3UtilTest {

    @Test
    public void testShowDocList() throws Exception {
        // Init
        int serverPort = 8080;
        String serverHost = "127.0.0.1";

        // OkHttpClient3
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .addHeader("Referer", "http://" + serverHost + ":" + serverPort + "/doc.html")
                .url("http://" + serverHost + ":" + serverPort + "/v2/api-docs")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        //System.out.println(result);

        // FastJson
        JSONObject jsonObject;
        JSONObject rootObject = JSON.parseObject(result);
        List<String> apiNameList = new ArrayList<>();
        for (Map.Entry<String, Object> entry1 : rootObject.getJSONObject("paths").entrySet()) {
            jsonObject = (JSONObject) entry1.getValue();
            for (Map.Entry<String, Object> entry2 : jsonObject.entrySet()) {
                apiNameList.add(((JSONObject) entry2.getValue()).getString("summary"));
            }
        }
        //System.out.println(apiNameList.size());

        // Sort
        if (null != apiNameList) {
            List<String> sortNameList = apiNameList.stream().distinct().sorted().collect(Collectors.toList());
            System.out.println(sortNameList.size());
            sortNameList.forEach(object -> System.out.println(object.substring(0, 9)));
            //sortNameList.forEach(object -> System.out.println(object.substring(10)));
            //sortNameList.forEach(object -> System.out.println("车厂端-" + object.substring(10)));
        }
    }
}
