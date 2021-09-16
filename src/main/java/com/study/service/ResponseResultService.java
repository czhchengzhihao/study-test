package com.study.service;

import com.study.model.ResponseResult;

import java.util.List;

public interface ResponseResultService {
    /**
     * 查询用例列表
     */
    List<ResponseResult> queryResponseResultList();

    /**
     * 根据id查询用例信息
     */
    ResponseResult queryResponseResultById(int id);

    /**
     * 添加响应结果
     */
    int addResponseResult(ResponseResult responseResult);
}
