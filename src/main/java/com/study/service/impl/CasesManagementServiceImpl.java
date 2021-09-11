package com.study.service.impl;

import com.study.dao.CasesManagementDao;
import com.study.pojo.CasesManagement;
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

    public void setCasesManagementDao(CasesManagementDao casesManagementDao) {
        this.casesManagementDao = casesManagementDao;
    }

    @Override
    public List<CasesManagement> queryAutomationList() {
        return casesManagementDao.queryAutomationList();
    }

    @Override
    public CasesManagement queryById(int id) {
        return casesManagementDao.queryById(id);
    }


}
