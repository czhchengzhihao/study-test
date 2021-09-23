package com.study.httputil;


import com.alibaba.fastjson.JSONObject;
import com.study.connection.HttpClientConnection;
import com.study.constant.AllConstant;
import org.apache.http.Header;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @PackageName: com.czh.util
 * @ClassName: HttpClientUtil
 * @Description: HttpClientUtil/description:http请求工具类
 * @Author: ChengZhiHao
 * @Date: 2021/4/12 14:46
 * @Version: v1.0
 */

public class HttpRedirectAndFileUploadUtil {

    /**
     * 日志打印
     */
    private static final Logger log = Logger.getLogger(Test.class);
    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);


    /**
     * @return java.lang.String
     * @Author ChengZhiHao
     * @Description //TODO post请求,form-data格式,重定向
     * @Date 14:38 2021/5/10
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static String redirectFormData(String testUrl, String interfaceAddress, String data) {
        CloseableHttpClient client = HttpClientConnection.getHttpClientConnection();
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        JSONObject parameter = JSONObject.parseObject(data);
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        //通过迭代器取出所有的key，再获取每一个键对应的值
        Set<String> keySet = parameter.keySet();
        for (String key : keySet) {
            String value = (String) parameter.get(key);
            params.add(new BasicNameValuePair(key, value));
        }
        CloseableHttpResponse newCloseableHttpResponse = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse closeableHttpResponse = client.execute(post);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            log.info(result);
            if (code == AllConstant.HTTP_CODE_302) {
                //跳转的目标地址是在 HTTP-HEAD 中的
                Header header = closeableHttpResponse.getFirstHeader("location");
                System.out.println(header);
                //这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息
                String newUrl = header.getValue();
                System.out.println(newUrl);
                HttpPost newPost = new HttpPost(newUrl);
                newCloseableHttpResponse = client.execute(newPost);
                String newResult = EntityUtils.toString(newCloseableHttpResponse.getEntity(), "UTF-8");
                log.info(newResult);
                return newResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (newCloseableHttpResponse != null) {
                try {
                    newCloseableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @return java.lang.String
     * @Author ChengZhiHao
     * @Description //TODO post请求,Json格式,重定向
     * @Date 14:39 2021/5/10
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static String redirectPost(String testUrl, String interfaceAddress, String data) {
        CloseableHttpClient client = HttpClientConnection.getHttpClientConnection();
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        JSONObject parameter = JSONObject.parseObject(data);
        post.setEntity(new StringEntity(parameter.toString(), "utf-8"));
        CloseableHttpResponse newCloseableHttpResponse = null;
        try {
            CloseableHttpResponse closeableHttpResponse = client.execute(post);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            log.info(result);
            if (code == AllConstant.HTTP_CODE_302) {
                //跳转的目标地址是在 HTTP-HEAD 中的
                Header header = closeableHttpResponse.getFirstHeader("location");
                System.out.println(header);
                //这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息
                String newUrl = header.getValue();
                System.out.println(newUrl);
                HttpPost newPost = new HttpPost(newUrl);
                newCloseableHttpResponse = client.execute(newPost);
                String newResult = EntityUtils.toString(newCloseableHttpResponse.getEntity(), "UTF-8");
                log.info(newResult);
                return newResult;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (newCloseableHttpResponse != null) {
                try {
                    newCloseableHttpResponse.close();
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
     * @Description //TODO 文件上传
     * @Date 15:19 2021/5/10
     * @Param [testUrl, interfaceAddress, path]
     **/
    public JSONObject uploadFile(String testUrl, String interfaceAddress, String path) {
        File file = new File(path);
        CloseableHttpClient client = HttpClientConnection.getHttpClientConnection();
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY));
        post.setEntity(builder.build());
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            //执行提交
            closeableHttpResponse = client.execute(post);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            if (code == AllConstant.HTTP_CODE_200) {
                String result = EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8);
                JSONObject jsonObject = JSONObject.parseObject(result);
                return jsonObject;
            }
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
}



