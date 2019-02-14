package com.shotdog.panda.common.enums;

import lombok.Getter;

/***
 * 数据库类型
 * @author ziming  Create At 2018-12-01 22:16
 *
 */
@Getter
public enum DatabaseTypeEnum {

    MYSQL(1, "mysql"),
    ORACLE(2, "oracle");


    private final int value;

    private String name;

    DatabaseTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }



}
