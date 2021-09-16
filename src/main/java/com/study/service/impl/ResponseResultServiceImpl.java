package com.study.service.impl;

import com.study.dao.ResponseResultDao;
import com.study.model.ResponseResult;
import com.study.service.ResponseResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ResponseResultService")
public class ResponseResultServiceImpl implements ResponseResultService {

    @Autowired
    private ResponseResultDao responseResultDao;


    @Override
    public List<ResponseResult> queryResponseResultList() {
        return responseResultDao.queryResponseResultList();
    }

    @Override
    public ResponseResult queryResponseResultById(int id) {
        return responseResultDao.queryResponseResultById(id);
    }

    @Override
    public int addResponseResult(ResponseResult responseResult) {
        return responseResultDao.addResponseResult(responseResult);
    }
}
