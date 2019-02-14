package com.shotdog.panda.web.check;

import com.shotdog.panda.combi.model.GenerateConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/***
 *
 * @author ziming  Create At 2018-12-02 20:46
 *
 */
@Component
public class GenerateConfigCheckComponent {

    public  void  checkParam(GenerateConfig generateConfig){


        Assert.notNull(generateConfig,"generateConfig 不能为空");
        Assert.hasText(generateConfig.getModelPackage(),"model 包名不能为空");
        Assert.hasText(generateConfig.getQueryPackage(),"query 包名不能为空");
        Assert.hasText(generateConfig.getDaoPackage(),"dao 包名不能为空");
        Assert.hasText(generateConfig.getServicePackage(),"service 包名不能为空");

        Assert.notEmpty(generateConfig.getTableList(),"请您至少选择一张表用于代码生成");

    }
}
