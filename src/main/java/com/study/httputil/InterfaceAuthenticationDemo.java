package com.study.httputil;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.czh.httputil
 * @ClassName: InterfaceAuthenticationDemo
 * @Description: InterfaceAuthenticationDemo/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/1 19:29
 * @Version: v1.0
 */
public class InterfaceAuthenticationDemo {
    //保存获取的Cookie值

    public Map<String, String> cookie = new HashMap<String, String>();

    @Test
    public void test_1() {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://10.20.5.244:9023/xxzx-workspace/workspace/addMark");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "测试专员"));
        params.add(new BasicNameValuePair("pwd", "123456"));
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse report = httpClient.execute(post);
            getAndStoreCookiesFromResponse(report);
            if (report.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(report.getEntity());
                System.out.println(result);
            } else {
                System.out.println("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"test_1"})
    public void test_2() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("接口地址");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "webApii"));
        params.add(new BasicNameValuePair("yn", "1"));
        params.add(new BasicNameValuePair("authScope", "3"));
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("menuIds", "325,1011,1102,11024,11029"));
        post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        addCookieInRequestHeaderBeforeRequest(post);
        HttpResponse report = httpClient.execute(post);
        if (report.getStatusLine().getStatusCode() == 200) {
            String result = EntityUtils.toString(report.getEntity());
            System.out.println(result);
        } else {
            System.out.println("请求失败");
        }
    }
    //将cookie设置到需要使用cookie的接口请求头中

    private void addCookieInRequestHeaderBeforeRequest(HttpPost post) {
        String pinIdCookie = cookie.get("pin");
        System.out.println(pinIdCookie);
        if (pinIdCookie != null) {
            post.setHeader("Cookie", pinIdCookie);
        }
    }

    //获取cookie，将cookie报存到map中


    private void getAndStoreCookiesFromResponse(HttpResponse report) {
        Header setCookieHeader = report.getFirstHeader("Set-Cookie");
        if (setCookieHeader != null) {
            String cookiePairsString = setCookieHeader.getValue();
            System.out.println("Set-Cookie:" + cookiePairsString);
            if (cookiePairsString != null && cookiePairsString.trim().length() > 0) {
                String[] cookiePairs = cookiePairsString.split(";");
                if (cookiePairs != null) {
                    for (String cookiePair : cookiePairs) {
                        if (cookiePair.contains("pin")) {
                            cookie.put("pin", cookiePair);
                        }
                    }
                }
            }
        }
    }

}
