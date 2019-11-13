package com.incarcloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * OkHttp3UtilTest
 *
 * @author Aaric, created on 2019-07-15T14:43.
 * @version 0.4.2-SNAPSHOT
 */
@Slf4j
public class OkHttp3UtilTest {

    @Test
    public void testGet() throws Exception {
        String result = OkHttp3Util.get("https://www.baidu.com", null);
        Assertions.assertNotNull(result);
    }

    @Test
    @Disabled
    public void testShowApiNameList() throws Exception {
        // Init
        int serverPort = 9090;
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
        //log.info(result);

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
        //log.info(apiNameList.size());

        // Sort
        List<String> sortNameList = null;
        if (null != apiNameList) {
            sortNameList = apiNameList.stream().distinct().sorted().collect(Collectors.toList());
            log.info("" + sortNameList.size());
            sortNameList.forEach(object -> log.info(object));
        }

        Assertions.assertNotNull(sortNameList);
    }
}
