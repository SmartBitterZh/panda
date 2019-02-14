package com.shotdog.panda.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * 表定义配置
 * @author ziming  Create At 2018-11-24 12:34
 *
 */
@Data
public class Table implements Serializable {


    // 表名
    private String tableName;

    // model名称
    private String modelName;

    // 主键属性名称
    private String pkFieldName;

    // 主键列名称
    private String pkColumnName;

    // 主键属性类型
    private String pkFieldType;

    // 主键列类型
    private String pkColumnType;

    // 主键所在列的序号
    private int pkSeq;

    // 字段列表
    private List<Field> fieldList;

    // 是否包含日期类型
    private  boolean includeDateType;

    // 是否包含有decimal 类型
    private boolean includeDecimal;
}
