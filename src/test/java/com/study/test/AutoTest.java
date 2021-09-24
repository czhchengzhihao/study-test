package com.study.test;


import com.alibaba.fastjson.JSONObject;
import com.study.connection.MybatisConnection;
import com.study.dao.ResponseResultDao;
import com.study.model.ResponseResult;
import com.study.service.CasesManagementService;
import com.study.service.ResponseResultService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    CasesManagementService CasesManagementServiceImpl = (CasesManagementService) context.getBean("CasesManagementService");

    @Test
    public void test1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());// new Date()为获取当前系统时间
        Timestamp dates = Timestamp.valueOf(format);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess("aaa");
        responseResult.setMessage("aaaa");
        responseResult.setResponseData("aaaa");
        responseResult.setRemark("aaa");
        responseResult.setCreationTime(dates);
        int i = responseResultServiceImpl.addResponseResult(responseResult);
        System.out.println(i);

    }

    @Test
    public void test2() {
        List<ResponseResult> responseResults = responseResultServiceImpl.queryResponseResultList();
        for (ResponseResult r : responseResults) {
            System.out.println(r);
            String s = JSONObject.toJSONString(r);
            System.out.println(s);
        }
    }

    //直接使用mybatis进行数据库增删改查
    @Test
    public void test() {
        SqlSession sqlSession = MybatisConnection.getSqlSession();
        ResponseResultDao mapper = sqlSession.getMapper(ResponseResultDao.class);
        List<ResponseResult> responseResults = mapper.queryResponseResultList();
        for (ResponseResult r : responseResults) {
            System.out.println(r);
            String s = JSONObject.toJSONString(r);
            System.out.println(s);
        }
    }

}
