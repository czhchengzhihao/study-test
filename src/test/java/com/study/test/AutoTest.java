package com.study.test;


import com.study.pojo.ResponseResult;
import com.study.service.CasesManagementService;
import com.study.service.ResponseResultService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PackageName: com.construct.demo
 * @ClassName: AutoTest
 * @Description: AutoTest/description:
 * @Author: ChengZhiHao
 * @Date: 2021/8/27 15:08
 * @Version: v1.0
 */
public class AutoTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    ResponseResultService responseResultServiceImpl = (ResponseResultService) context.getBean("ResponseResultService");

    @Test
    public void test2() {
        ResponseResult responseResult=new ResponseResult();
        responseResult.setSuccess("aaa");
        responseResult.setMessage("aaaa");
        responseResult.setEntity("aaaa");
        int i = responseResultServiceImpl.addResponseResult(responseResult);
        System.out.println(i);
    }
}
