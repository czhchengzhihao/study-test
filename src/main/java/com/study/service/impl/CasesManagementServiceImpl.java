package com.study.service.impl;

import com.study.dao.CasesManagementDao;
import com.study.model.CasesManagement;
import com.study.service.CasesManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName: com.study.mybatisdemo
 * @ClassName: CasesManagementServiceImpl
 * @Description: CasesManagementServiceImpl/description:
 * @Author: ChengZhiHao
 * @Date: 2021/9/10 15:19
 * @Version: v1.0
 */
@Service("CasesManagementService")
public class CasesManagementServiceImpl implements CasesManagementService {

    //service掉dao  业务层调dao层

    @Autowired
    private CasesManagementDao casesManagementDao;


    @Override
    public List<CasesManagement> queryCasesManagementList() {
        return casesManagementDao.queryCasesManagementList();
    }

    @Override
    public CasesManagement queryCasesManagementById(int id) {
        return casesManagementDao.queryCasesManagementById(id);
    }
}
