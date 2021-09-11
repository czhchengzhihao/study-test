package com.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PackageName: com.study.pojo
 * @ClassName: Automation
 * @Description: Automation/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/6 10:56
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasesManagement {
    private int id;
    private String moduleName;
    private String interfaceName;
    private String apiUrl;
    private String casesName;
    private String requestMethod;
    private String theGinseng;
    private String expectedResults;
    private String actualResults;
    private Date updateTime;
    private Date creationTime;

}
