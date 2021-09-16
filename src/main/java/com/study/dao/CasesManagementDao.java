package com.study.dao;


import com.study.model.CasesManagement;

import java.util.List;

/**
 * @PackageName: com.study.dao
 * @ClassName: AutomationDao
 * @Description: AutomationDao/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/6 10:59
 * @Version: v1.0
 */

/**
 * 这是一个mybatis的mapper类
 *
 * @author admin
 */
public interface CasesManagementDao {
    /**
     * 查询用例列表
     */
    List<CasesManagement> queryCasesManagementList();

    /**
     * 根据id查询用例信息
     */
    CasesManagement queryCasesManagementById(int id);

}
