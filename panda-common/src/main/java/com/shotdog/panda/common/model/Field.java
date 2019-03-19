package com.shotdog.panda.common.model;

import lombok.Data;

import java.io.Serializable;

/***
 * 字段定义模型
 * @author ziming  Create At 2018-11-24 12:31
 *
 */
@Data
public class Field implements Serializable {


    // 属性名称
    private String fieldName;

    // 列字段名称
    private String columnName;

    // 属性类型
    private String fieldType;

    // 列字段类型
    private String columnType;

    // 备注
    private String remark;

    //  大小
    private Integer size;
    //  精度
    private Integer digits;

}
