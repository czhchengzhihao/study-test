package com.study.httputil;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @PackageName: com.qixin.util
 * @ClassName: HttpClientGetUtil
 * @Description: HttpClientGetUtil/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/20 14:59
 * @Version: v1.0
 */
public class HttpGetUtil {
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
     * @Description //TODO admin get请求,参数拼接在请求地址中
     * @Date 15:17 2021/7/20
     * @Param [testUrl, interfaceAddress, data, headerKey, headerValue]
     **/
    public static JSONObject httpGet(String testUrl, String interfaceAddress, String data) {
        URIBuilder build;
        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            JSONObject parameter = JSONObject.parseObject(data);
            log.info("入参：" + parameter);
            Reporter.log("入参：" + parameter, true);
            if (parameter != null) {
                Set<String> keys = parameter.keySet();
                for (String key : keys) {
                    String value = String.valueOf(parameter.get(key));
                    //在请求地址上拼接参数
                    build.setParameter(key, value);
                }
            }
            HttpGet get = new HttpGet(build.build());
            HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
            httpClientResponseUtil.httpSetHeader(get);
            return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, get);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject httpGet(String testUrl, String interfaceAddress) {
        HttpGet get = new HttpGet(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        httpClientResponseUtil.httpSetHeader(get);
        return httpClientResponseUtil.requestExecution(testUrl, interfaceAddress, get);
    }

}
