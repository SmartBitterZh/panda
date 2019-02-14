package com.shotdog.panda.combi.model;

import com.shotdog.panda.common.model.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 *
 * @author ziming  Create At 2018-12-02 20:38
 *
 */
@Data
public class GenerateConfig implements Serializable {


    // model 包名
    private String modelPackage;

    // query 包名
    private String queryPackage;

    // dao 包名
    private String daoPackage;

    private String servicePackage;

    // 表列表
    private List<Table> tableList;


}
