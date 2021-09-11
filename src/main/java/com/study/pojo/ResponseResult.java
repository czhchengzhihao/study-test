package com.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.study.mybatisdemo
 * @ClassName: ResponseResult
 * @Description: ResponseResult/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/10 14:42
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private int id;
    private String success;
    private String message;
    private String entity;
}
