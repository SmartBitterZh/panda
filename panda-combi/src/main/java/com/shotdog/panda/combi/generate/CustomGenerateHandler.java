package com.shotdog.panda.combi.generate;

import com.shotdog.panda.common.model.Field;
import com.shotdog.panda.common.model.Table;
import com.shotdog.panda.common.util.CommonUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/***
 *
 * @author ziming  Create At 2018-12-08 20:37
 *
 */
@Component
public class CustomGenerateHandler {


    public void handler(List<Table> tableList){

        Assert.notEmpty(tableList,"请至少选择一张表进行生成");


        for (Table table:tableList){

            this.tableHandler(table);
        }

    }

    /**
     * 处理表信息
     * @param table 表配置
     */
    private void tableHandler(Table table) {

        String tableName = table.getTableName();
        String pkColumnName = table.getPkColumnName();
        String pkColumnType = table.getPkColumnType();

        Assert.hasText(tableName,"表名不能为空");
        Assert.hasText(pkColumnName,tableName +" 主键名不能为空");
        Assert.hasText(pkColumnType,tableName +" 主键类型不能为空");

        String modelName = CommonUtils.toFirstUpper(CommonUtils.toHump(CommonUtils.toLower(tableName)));
        String fieldName = CommonUtils.toFirstLower(CommonUtils.toHump(CommonUtils.toLower(pkColumnName)));
        String fieldType = CommonUtils.toJavaType(pkColumnType);

        table.setModelName(modelName);
        table.setPkFieldName(fieldName);
        table.setPkFieldType(fieldType);


        List<Field> fieldList = table.getFieldList();
        Assert.notEmpty(fieldList,String.format("表 %s 至少要一个字段呀~",tableName));


        for (Field field:fieldList){

            this.fieldHandler(table.getTableName(),field);
        }
    }

    /**
     * 字段处理
     * @param tableName 表名
     * @param field 字段
     */
    private void fieldHandler(String tableName,Field field) {

        String columnName = field.getColumnName();
        String columnType = field.getColumnType();
        String remark = field.getRemark();
        Assert.hasText(columnName,String.format("表【%s】字段名不能为空",tableName));
        Assert.hasText(columnType,String.format("表【%s】字段类型不能为空",tableName));
        Assert.hasText(remark,String.format("表【%s】字段备注不能为空",tableName));

        String fieldName = CommonUtils.toFirstLower(CommonUtils.toHump(CommonUtils.toLower(columnName)));
        String fieldType = CommonUtils.toJavaType(CommonUtils.toUpper(columnType));
        field.setFieldName(fieldName);
        field.setFieldType(fieldType);
    }
}
