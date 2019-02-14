package com.shotdog.panda.common.model;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/***
 *
 * @author ziming  Create At 2018-11-24 12:39
 *
 */
@Getter
public class Config implements Serializable {

    // as: dao
    private String daoPackage;

    // as: service
    private String servicePackage;

    // model
    private String modelPackage;

    //  query model package
    private String queryPackage;

    // spec table name
    private List<String> tables;


    public Config daoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
        return this;
    }

    public Config servicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
        return this;
    }

    public Config modelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
        return this;
    }

    public Config queryPackage(String queryPackage) {
        this.queryPackage = queryPackage;
        return this;
    }

    public Config tables(List<String> tables) {
        this.tables = tables;
        return this;
    }


}
