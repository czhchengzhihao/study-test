package com.study.httputil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @PackageName: com.qixin.util
 * @ClassName: HttpClientPostUtil
 * @Description: HttpClientPostUtil/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/20 13:54
 * @Version: v1.0
 */
public class HttpPostUtil {

    /**
     * 日志打印
     */
    private static final Logger log = Logger.getLogger(Test.class);
    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin post请求,入参JSON格式
     * @Date 14:31 2021/7/20
     * @Param [testUrl, interfaceAddress, data, headerKey, headerValue]
     **/
    public static JSONObject httpPost(String testUrl, String interfaceAddress, String data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        JSONObject parameter = JSONObject.parseObject(data);
        log.info("入参：" + parameter);
        Reporter.log("入参：" + parameter, true);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        httpClientResponseUtil.httpSetHeader(post);
        post.setEntity(new StringEntity(parameter.toString(), "utf-8"));
        return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, post);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin post请求,无需传参
     * @Date 14:32 2021/7/20
     * @Param [testUrl, interfaceAddress, headerKey, headerValue]
     **/
    public static JSONObject httpPost(String testUrl, String interfaceAddress) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        httpClientResponseUtil.httpSetHeader(post);
        return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, post);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin post请求,入参数组格式
     * @Date 14:37 2021/7/20
     * @Param [testUrl, interfaceAddress, data, headerKey, headerValue]
     **/
    public static JSONObject httpPostArray(String testUrl, String interfaceAddress, String data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("请求地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        httpClientResponseUtil.httpSetHeader(post);
        JSONArray parameter = JSONObject.parseArray(data);
        log.info("入参：" + parameter);
        Reporter.log("入参：" + parameter, true);
        post.setEntity(new StringEntity(parameter.toString(), "utf-8"));
        return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, post);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin post请求,form-data格式
     * @Date 14:41 2021/7/20
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPostFormData(String testUrl, String interfaceAddress, String data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        httpClientResponseUtil.httpSetHeader(post);
        JSONObject parameter = JSONObject.parseObject(data);
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        //通过迭代器取出所有的key，再获取每一个键对应的值
        Set<String> keySet = parameter.keySet();
        for (String key : keySet) {
            String value = (String) parameter.get(key);
            params.add(new BasicNameValuePair(key, value));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
            return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin post请求,参数在接口地址上
     * @Date 14:53 2021/7/20
     * @Param [testUrl, interfaceAddress, data, headerKey, headerValue]
     **/
    public static JSONObject httpPostUrl(String testUrl, String interfaceAddress, String data) {
        URIBuilder build;
        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            JSONObject parameter = JSONObject.parseObject(data);
            if (parameter != null) {
                Set<String> keys = parameter.keySet();
                for (String key : keys) {
                    String value = String.valueOf(parameter.get(key));
                    //在请求地址上拼接参数
                    build.setParameter(key, value);
                }
            }
            HttpPost post = new HttpPost(build.build());
            HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
            httpClientResponseUtil.httpSetHeader(post);
            return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, post);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
