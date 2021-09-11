package com.study.service.impl;

import com.study.dao.ResponseResultDao;
import com.study.pojo.ResponseResult;
import com.study.service.ResponseResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ResponseResultService")
public class ResponseResultServiceImpl implements ResponseResultService {

    @Autowired
    private ResponseResultDao responseResultDao;


    @Override
    public int addResponseResult(ResponseResult responseResult) {
        return responseResultDao.addResponseResult(responseResult);
    }
}
