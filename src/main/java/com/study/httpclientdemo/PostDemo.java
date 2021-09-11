package com.study.httpclientdemo;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.study.httpclientdemo
 * @ClassName: PostDemo
 * @Description: PostDemo/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/10 14:36
 * @Version: v1.0
 */
public class PostDemo {

    @Test
    public void testes() throws IOException {
        // 1.创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //2.创建请求方法的实例，并指定请求URL
        HttpPost post = new HttpPost("http://www.lemonban.com/user/ajax/login");
        // 3.设置请求头信息
        //post.setHeader("Content-Type", "application/json");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // 4.设置入参
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("account", "c17639617036"));
        params.add(new BasicNameValuePair("password", "czh980405"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        post.setEntity(entity);
        // 5.执行请求
        CloseableHttpResponse response = client.execute(post);
        //6.获取响应信息
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println("result:" + result);
        // 6.将响应信息转换为json格式
        JSONObject jsonResult = JSONObject.parseObject(result);
        System.out.println("jsonResult:" + jsonResult);
        // 7.断言验证
        Assert.assertEquals(jsonResult.getString("message"), "登陆成功");
    }
}
