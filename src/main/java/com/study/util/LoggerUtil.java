package com.study.util;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * @PackageName: com.study.util
 * @ClassName: LoggerUtil
 * @Description: TODO LoggerUtil/description:一般用DEBUG,info,warn,error 4个级别
 * @Author: ChengZhiHao
 * @Date: 2021/9/13 15:05
 * @Version: v1.0
 */
public class LoggerUtil {
    Logger log = Logger.getLogger(Test.class);


    public void logInfo(String logInformation) {
        log.info(logInformation);
    }

    public void logWarn(String logInformation) {
        log.warn(logInformation);
    }

    public void logError(String logInformation) {
        log.error(logInformation);
    }

    public void logDebug(String logInformation) {
        log.debug(logInformation);
    }
}
