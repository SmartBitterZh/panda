package com.shotdog.panda.common.model;

import lombok.Getter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/***
 *  数据库定义配置
 * @author ziming  Create At 2018-11-24 12:35
 *
 */
@Getter
public class DataBase implements Serializable {

    //  jdbc url
    private String url;

    // user name
    private String userName;

    // password
    private String password;

    // 驱动类
    private String driverClass;

    // 数据库连接
    private Connection connection;

    public DataBase(String url, String userName, String password, String driverClass) throws Exception {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driverClass = driverClass;

        this.initConnection();

    }

    /***
     * 初始化连接
     */
    private void initConnection() throws Exception {

        Class.forName(this.driverClass).newInstance();
        this.connection = DriverManager.getConnection(this.url, userName, password);

    }
}
