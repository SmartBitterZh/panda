package com.shotdog.panda.combi.generate.support;

import com.shotdog.panda.combi.generate.CodeGenerator;
import com.shotdog.panda.common.model.Config;
import com.shotdog.panda.common.model.Table;
import com.shotdog.panda.common.util.CommonUtils;
import com.shotdog.panda.common.util.ZipUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *  基于freemarker 代码生成器
 * @author ziming  Create At 2018-11-24 14:13
 *
 */
@Component
public class FreemarkerCodeGenerator implements CodeGenerator, InitializingBean {


    private final static String BASE_DAO_TEMPLATE = "BaseDao.ftl";
    private final static String DAO_TEMPLATE = "Dao.ftl";
    private final static String BASE_SERVICE_TEMPLATE = "BaseService.ftl";
    private final static String SERVICE_TEMPLATE = "Service.ftl";
    private final static String CONTROLLER_TEMPLATE = "Controller.ftl";
    private final static String BASE_QUERY_TEMPLATE = "BaseQuery.ftl";
    private final static String QUERY_TEMPLATE = "Query.ftl";
    private final static String MODEL_TEMPLATE = "Model.ftl";
    private final static String DTO_TEMPLATE = "DTO.ftl";
    private final static String SORT_MODE_TEMPLATE = "SortMode.ftl";
    private final static String SORT_MODE_ENUM_TEMPLATE = "SortModeEnum.ftl";
    private final static String XML_TEMPLATE = "Xml.ftl";


    private Configuration freemarkerConfiguration;


    /**
     * 根据配置和指定的表信息生成java 倒霉
     * @param config 配置信息
     * @param tableList 表信息列表
     * @return
     * @throws Exception
     */
    public String generate(Config config,List<Table> tableList) throws Exception {


        String daoPackage = config.getDaoPackage();
        String modelPackage = config.getModelPackage();
        String queryPackage = config.getQueryPackage();
        String servicePackage = config.getServicePackage();

        Map<String, Object> root = new HashMap<String, Object>();

        root.put("daoPackage", daoPackage);
        root.put("modelPackage", modelPackage);
        root.put("queryPackage", queryPackage);
        root.put("servicePackage", servicePackage);

        String basePath = System.getProperty("user.dir");
        //System.getProperty("java.io.tmpdir");

        long currentTimeMillis = System.currentTimeMillis();
        basePath = basePath.concat(currentTimeMillis + "");

        String daoPath = basePath.concat("/").concat(daoPackage.replaceAll("\\.", "/"));
        String modelPath = basePath.concat("/").concat(modelPackage.replaceAll("\\.", "/"));
        String queryPath = basePath.concat("/").concat(queryPackage.replaceAll("\\.", "/"));
        String servicePath = basePath.concat("/").concat(servicePackage.replaceAll("\\.", "/"));
        System.err.println("daoPath=" + daoPath);
        System.err.println("modelPath=" + modelPath);
        System.err.println("queryPath=" + queryPath);
        System.err.println("servicePath=" + servicePath);

        String xmlPath = modelPath.concat("/xml");
        CommonUtils.createDirNotExist(modelPath);
        CommonUtils.createDirNotExist(queryPath);
        CommonUtils.createDirNotExist(daoPath);
        CommonUtils.createDirNotExist(servicePath);
        CommonUtils.createDirNotExist(xmlPath);
        this.generateCode(root, BASE_DAO_TEMPLATE, daoPath, "BaseDao.java");
        this.generateCode(root, BASE_SERVICE_TEMPLATE, servicePath, "BaseService.java");
        this.generateCode(root, SORT_MODE_ENUM_TEMPLATE, queryPath, "SortModeEnum.java");
        this.generateCode(root, SORT_MODE_TEMPLATE, queryPath, "SortMode.java");
        this.generateCode(root, BASE_QUERY_TEMPLATE, queryPath, "BaseQuery.java");


        for (Table table : tableList) {


            root.put("table", table);
            // model
            this.generateCode(root, MODEL_TEMPLATE, modelPath, String.format("%sDO.java", table.getModelName()));
            // dto
            this.generateCode(root, DTO_TEMPLATE, modelPath, String.format("%sDTO.java", table.getModelName()));
            // query
            this.generateCode(root, QUERY_TEMPLATE, queryPath, String.format("%sQuery.java", table.getModelName()));
            // dao
            this.generateCode(root, DAO_TEMPLATE, daoPath, String.format("%sDao.java", table.getModelName()));
            // service
            this.generateCode(root, SERVICE_TEMPLATE, servicePath, String.format("%sService.java", table.getModelName()));
            // controller
            this.generateCode(root, CONTROLLER_TEMPLATE, servicePath, String.format("%sController.java", table.getModelName()));
            // mapper xml
            this.generateCode(root, XML_TEMPLATE, xmlPath, String.format("%sMapper.xml", table.getModelName()));
        }

        FileOutputStream fileOutputStream = new FileOutputStream(basePath.concat(".zip"));
        ZipUtils.toZip(basePath, fileOutputStream, true);
        return basePath.concat(".zip");
    }
    /**
     * 根据目标生成文件
     *
     * @param root         数据
     * @param templateName 模板名称
     * @param fileName     生成的文件名
     * @throws IOException
     * @throws TemplateException
     */
    private void generateCode(Map<String, Object> root, String templateName, String path, String fileName) throws IOException, TemplateException {

        Template template = this.freemarkerConfiguration.getTemplate(templateName);
        fileName = path.concat("/").concat(fileName);
        System.err.println(fileName);
        template.process(root, new FileWriter(fileName));
    }


    public void afterPropertiesSet() throws Exception {

        this.freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_27);
        this.freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), "/template");

    }
}
