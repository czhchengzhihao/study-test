package com.study.service;

import com.study.model.CasesManagement;

import java.util.List;

/**
 * @PackageName: com.study.mybatisdemo
 * @ClassName: CasesManagementService
 * @Description: CasesManagementService/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/10 15:18
 * @Version: v1.0
 */

public interface CasesManagementService {
    /**
     * 查询用例列表
     */
    List<CasesManagement> queryCasesManagementList();

    /**
     * 根据id查询用例信息
     */
    CasesManagement queryCasesManagementById(int id);


}
