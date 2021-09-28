package com.study.interfacedemo;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author admin TODO https://mp.weixin.qq.com/s/qNOl5s_kri5XMF6ASLH6cg
 */
public class UploadFile {
    @Test
    public void testUpload1() throws IOException {
        String url ="https://www.baidu.com/";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        File file = new File("C:/Users/hetiantian/Desktop/学习/docker_practice.pdf");
        FileBody fileBody = new FileBody(file);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //addPart上传文件
        builder.addPart("file", fileBody);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    @Test
    public void upload() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("aaaaaaaaaa");
        //设置请求头
        String filePath = "aa";
        String userId = "aa";
        String remotePath = "bb";
        String isOverWrite = "vv";
        //构造待上传数据,加入builder
        File file = new File(filePath);
        String fimeName = file.getName();
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.RFC6532);
        builder.setCharset(Charset.forName("UTF-8"));
        builder.addPart("userId", new StringBody("aa", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("remotePath", new StringBody(remotePath, ContentType.MULTIPART_FORM_DATA));
        builder.addPart("isOverWrite", new StringBody(isOverWrite, ContentType.MULTIPART_FORM_DATA));
        builder.addPart("isSendEmail", new StringBody(String.valueOf(false), ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowChunkNumber", new StringBody("1", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowChunkSize", new StringBody("104857600", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowCurrentChunkSize", new StringBody("90058", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowTotalSize", new StringBody("90058", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowIdentifier", new StringBody("90058-2pdf", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowFilename", new StringBody(fimeName, ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowRelativePath", new StringBody(fimeName, ContentType.MULTIPART_FORM_DATA));
        builder.addPart("flowTotalChunks", new StringBody("1", ContentType.MULTIPART_FORM_DATA));
        builder.addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY));
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        HttpResponse response;
        try {
            response = client.execute(post);
            System.out.println("---->reponse:" + response);
            entity = response.getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}