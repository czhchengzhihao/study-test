package com.study.util;

import com.study.constant.AllConstant;
import org.testng.Assert;

/**
 * @PackageName: com.qixin.util
 * @ClassName: AssertUtil
 * @Description: AssertUtil/description:
 * @Author: ChengZhiHao
 * @Date: 2021/7/30 9:37
 * @Version: v1.0
 */
public class AssertUtil {

    /**
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO 重复断言代码
     * @Date 9:38 2021/7/30
     * @Param [code, success]
     **/
    public static void assertUtil(String code, String msg, String success) {
        Assert.assertEquals(msg, AllConstant.RESPONSE_MSG);
        Assert.assertEquals(code, AllConstant.RESPONSE_CODE);
        Assert.assertEquals(success, AllConstant.RESPONSE_TRUE);
    }

}
