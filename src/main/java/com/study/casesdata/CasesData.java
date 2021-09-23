package com.study.casesdata;

import com.study.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @PackageName: com.qixin.parameterized
 * @ClassName: Read_Parameter
 * @Description: Read_Parameter/description:
 * @Author: ChengZhiHao
 * @Date: 2021/6/17 17:09
 * @Version: v1.0
 */
public class CasesData {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);

    /**
     * 根据个人信息新增应用--可新增应用提供
     */
    @DataProvider(name = "searchCanAddWorkspaceAppCaseData")
    public Object[][] searchCanAddWorkspaceAppCaseData() {
        return dataS(new int[]{2, 3, 4, 5, 6, 7}, new int[]{6, 7});
    }


    /**
     * @return java.lang.Object[][]
     * @Author ChengZhiHao
     * @Description //TODO 读取excel用例工具类
     * @Date 17:28 2021/6/17
     * @Param [rows, cells]行号数组，列号数组
     * singleInterfaceCases //TODO 单接口case
     **/
    public static Object[][] dataS(int[] rows, int[] cells) {
        /**
         Object[][] dataS = ExcelUtil.DiscreteDataS(BUNDLE.getString("excelAddress"), BUNDLE.getString("sheet"), rows, cells);
         return dataS;
         **/
        return ExcelUtil.DiscreteDataS(BUNDLE.getString("excelAddress"), BUNDLE.getString("sheet"), rows, cells);

    }

    /**
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO 测试case
     * @Date 17:29 2021/6/17
     * @Param []
     **/
    @Test
    public void test() {
        Object[][] data = dataS(new int[]{2}, new int[]{7});
        for (Object[] objectss : data) {
            for (Object object : objectss) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }

    }
}
