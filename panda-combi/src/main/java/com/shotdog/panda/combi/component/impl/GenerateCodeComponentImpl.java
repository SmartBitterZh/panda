package com.shotdog.panda.combi.component.impl;

import com.shotdog.panda.combi.component.GenerateCodeComponent;
import com.shotdog.panda.combi.generate.CodeGenerator;
import com.shotdog.panda.combi.generate.CustomGenerateHandler;
import com.shotdog.panda.combi.model.GenerateConfig;
import com.shotdog.panda.common.exception.PandaException;
import com.shotdog.panda.common.model.Config;
import com.shotdog.panda.common.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/***
 *
 * @author ziming  Create At 2018-11-24 12:52
 *
 */
@Component
public class GenerateCodeComponentImpl implements GenerateCodeComponent {


    private final static Logger log = LoggerFactory.getLogger(GenerateCodeComponentImpl.class);

    @Resource
    private CodeGenerator codeGenerator;

    @Resource
    private CustomGenerateHandler customGenerateHandler;


    /**
     * 根据指定的配置生成对应的代码
     *
     * @param generateConfig 生成配置
     * @return
     */
    public String generate(GenerateConfig generateConfig) {


        String modelPackage = generateConfig.getModelPackage();
        String queryPackage = generateConfig.getQueryPackage();
        String daoPackage = generateConfig.getDaoPackage();
        String servicePackage = generateConfig.getServicePackage();
        List<Table> tableList = generateConfig.getTableList();
        Config config = new Config().daoPackage(daoPackage)
                .modelPackage(modelPackage)
                .queryPackage(queryPackage)
                .servicePackage(servicePackage);


//        String url = "jdbc:mysql://127.0.0.1:3306/echo?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
//        String userName = "root";
//        String password = "123456";
//        String driverClass = "com.mysql.jdbc.Driver";

        try {

            return this.codeGenerator.generate(config, tableList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new PandaException("生成失败,请重试!", e);
        }
    }

    /**
     * 自定义生成代码
     *
     * @param generateConfig 配置
     * @return
     */
    public String generateCustom(GenerateConfig generateConfig) {

        List<Table> tableList = generateConfig.getTableList();
        this.customGenerateHandler.handler(tableList);

        String modelPackage = generateConfig.getModelPackage();
        String queryPackage = generateConfig.getQueryPackage();
        String daoPackage = generateConfig.getDaoPackage();
        String servicePackage = generateConfig.getServicePackage();
        Config config = new Config().daoPackage(daoPackage)
                .modelPackage(modelPackage)
                .queryPackage(queryPackage)
                .servicePackage(servicePackage);

        try {

            return this.codeGenerator.generate(config, tableList);
        } catch (Exception e) {
            log.error("生成代码失败,", e);
            throw new PandaException(e);
        }
    }
}
