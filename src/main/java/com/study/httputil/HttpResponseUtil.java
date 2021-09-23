package com.study.httputil;

import com.alibaba.fastjson.JSONObject;
import com.study.connection.HttpClientConnection;
import com.study.constant.AllConstant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @PackageName: com.qixin.util
 * @ClassName: HttpClientResponseUtil
 * @Description: HttpClientResponseUtil/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/20 13:56
 * @Version: v1.0
 */
public class HttpResponseUtil {
    /**
     * 日志打印
     */
    private static final Logger log = Logger.getLogger(Test.class);
    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);

    private CloseableHttpResponse closeableHttpResponse;
    private CloseableHttpClient client;

    /* **
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO admin 设置头信息公共类/POST
     * @Date 14:33 2021/7/20
     * @Param [headerKey, headerValue]
     ***/
    /*public void httpSetHeader(String headerKey, String headerValue, HttpPost post) {
        post.setHeader(headerKey, headerValue);
        log.info("头信息(key:value):" + headerKey + ":" + headerValue);
        Reporter.log("头信息(key:value):" + headerKey + ":" + headerValue, true);
    }*/

    /**
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO admin 设置头信息公共类/GET
     * @Date 14:33 2021/7/20
     * @Param [headerKey, headerValue]
     **/
  /*  public void httpSetHeader(String headerKey, String headerValue, HttpGet get) {
        get.setHeader(headerKey, headerValue);
        log.info("头信息(key:value):" + headerKey + ":" + headerValue);
        Reporter.log("头信息(key:value):" + headerKey + ":" + headerValue, true);
    }*/


    /**
     * @return
     * @Author ChengZhiHao
     * @Description //TODO admin 请求头信息默认设置
     * @Date 15:31 2021/7/20
     * @Param
     **/
    public void httpSetHeader(HttpGet get) {
        get.setHeader("Content-Type", "application/json");
    }

    public void httpSetHeader(HttpPost post) {
        post.setHeader("Content-Type", "application/json");
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin 请求执行公共类/POST
     * @Date 14:33 2021/7/20
     * @Param [testUrl, interfaceAddress]
     **/
    public JSONObject requestExecution(String testUrl, String interfaceAddress, HttpPost post) {
        client = HttpClientConnection.getHttpClientConnection();
        try {
            closeableHttpResponse = client.execute(post);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            return responseResults(testUrl, interfaceAddress, closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin 请求执行公共类/GET
     * @Date 14:33 2021/7/20
     * @Param [testUrl, interfaceAddress]
     **/
    public JSONObject requestExecution(String testUrl, String interfaceAddress, HttpGet get) {
        client = HttpClientConnection.getHttpClientConnection();
        try {
            closeableHttpResponse = client.execute(get);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            return responseResults(testUrl, interfaceAddress, closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO admin 响应结果返回公共类
     * @Date 14:34 2021/7/20
     * @Param [testUrl, interfaceAddress, closeableHttpResponse, code]
     **/
    public JSONObject responseResults(String testUrl, String interfaceAddress, CloseableHttpResponse closeableHttpResponse, int code) {
        if (code == AllConstant.HTTP_CODE_200) {
            String result = null;
            try {
                result = EntityUtils.toString(closeableHttpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject resultJsonObject = JSONObject.parseObject(result);
            log.info("响应结果：" + resultJsonObject);
            Reporter.log("响应结果：" + resultJsonObject, true);
            return resultJsonObject;
        } else {
            log.info("状态码：" + code + "," + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress) + "接口请求响应错误");
            Reporter.log("状态码：" + code + "," + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress) + "接口请求响应错误", true);
            return null;
        }
    }
}
