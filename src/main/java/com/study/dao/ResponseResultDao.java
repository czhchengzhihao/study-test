package com.study.dao;

import com.study.model.ResponseResult;

import java.util.List;

/**
 * @PackageName: com.study.mybatisdemo
 * @ClassName: ResponseResultDao
 * @Description: ResponseResultDao/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/10 14:43
 * @Version: v1.0
 */
public interface ResponseResultDao {
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
