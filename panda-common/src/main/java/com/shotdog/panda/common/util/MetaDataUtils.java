package com.shotdog.panda.common.util;

import com.shotdog.panda.common.exception.PandaException;
import com.shotdog.panda.common.model.Field;
import com.shotdog.panda.common.model.Table;
import javafx.util.Pair;
import oracle.jdbc.OracleConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author ziming  Create At 2018-11-24 14:02
 *
 */
public class MetaDataUtils {


    private final static Logger log = LoggerFactory.getLogger(MetaDataUtils.class);


    /**
     * 加载指定表名的表定义列表
     *
     * @param connection 连接
     * @param tables     指定的表名列表
     * @return
     */
    public static List<Table> loadTableList(Connection connection, List<String> tables) throws SQLException {
        if (tables != null && !tables.isEmpty()) {
            checkTableName(connection, tables);
        } else {
            tables = loadAllTableName(connection);
        }

        if (tables == null || tables.isEmpty()) {
            throw new PandaException("the provide database has no table error!");
        }
        return loadTables(connection, tables);
    }

    /**
     * 加载表定义列表
     *
     * @param connection 连接
     * @param tableNames 表名列表
     * @return
     */
    public static List<Table> loadTables(Connection connection, List<String> tableNames) throws SQLException {

        List<Table> tableList = new ArrayList<Table>();
        log.info("---------------Start load all table info-------------------------");
        for (String tableName : tableNames) {

            // 根据表名加载表数据信息
            Table table = loadTableInfoByTableName(connection, tableName);
            log.info("load table  is :{}", table);
            tableList.add(table);

        }
        return tableList;
    }

    /**
     * 根据表名加载表定义信息
     *
     * @param connection 连接
     * @param tableName  表名
     * @return
     */
    public static Table loadTableInfoByTableName(Connection connection, String tableName) throws SQLException {

        ((OracleConnection) connection).setRemarksReporting(true);
        ResultSet columnsResult = connection.getMetaData().getColumns(null, null, tableName, null);

        List<Field> fieldList = new ArrayList<Field>();

        boolean includeDateType = false;
        boolean includeDecimalType = false;
        //  循环迭代获取所有的表中的列明和类型集合
        while (columnsResult.next()) {

            String column = columnsResult.getString("COLUMN_NAME");
            Integer size = columnsResult.getInt("COLUMN_SIZE");
            Integer digits = columnsResult.getInt("DECIMAL_DIGITS");
            String typeName = columnsResult.getString("TYPE_NAME");
            String remark = columnsResult.getString("REMARKS");
            log.info("load column name >>>>>>>>>" + column);
            log.info("load column type name >>>>>>>>" + typeName);
            log.info("load column remark >>>>>>>>" + remark);

            String filedName = CommonUtils.toFirstLower(CommonUtils.toHump(CommonUtils.toLower(column)));
            String fieldType = CommonUtils.toJavaType(typeName, size, digits);

            if (!includeDateType) {
                includeDateType = CommonUtils.isDateType(fieldType);
            }
            if (!includeDecimalType) {
                includeDecimalType = CommonUtils.isBigDecimalType(fieldType);
            }


            Field field = new Field();
            field.setColumnName(CommonUtils.toLower(column));
            field.setFieldName(filedName);
            field.setRemark(remark);

            field.setFieldType(fieldType);
            field.setColumnType(typeName);
            field.setSize(size);
            field.setDigits(digits);
            fieldList.add(field);

        }

        // 关闭
        columnsResult.close();

        //  加载主键信息
        Pair<String, Integer> pkInfoResult = loadPkInfoByTableName(connection, tableName);

        if (pkInfoResult == null)
            throw new PandaException(String.format("table %s is no primary key ", tableName));


        String pkFieldName = CommonUtils.toFirstLower(CommonUtils.toHump(CommonUtils.toLower(pkInfoResult.getKey())));
        pkInfoResult.getValue();
        //  构造表实体
        String modelName = CommonUtils.toFirstUpper(CommonUtils.toHump(CommonUtils.toLower(tableName)));
        Table table = new Table();
        table.setTableName(tableName);
        table.setModelName(modelName);
        table.setFieldList(fieldList);
        table.setPkFieldName(pkFieldName);
        table.setPkColumnName(pkInfoResult.getKey());
        table.setPkSeq(pkInfoResult.getValue());
        table.setPkColumnType(table.getFieldList().get(table.getPkSeq() - 1).getColumnType());
        table.setPkFieldType(table.getFieldList().get(table.getPkSeq() - 1).getFieldType());
        table.setIncludeDateType(includeDateType);
        table.setIncludeDecimal(includeDecimalType);
        table.setRemark("");
        return table;
    }

    /**
     * 根据指定的表名查询主键信息
     *
     * @param connection 连接
     * @param tableName  指定的表名
     * @return
     */
    public static Pair<String, Integer> loadPkInfoByTableName(Connection connection, String tableName) throws SQLException {
        ResultSet primaryKeysResult = connection.getMetaData().getPrimaryKeys(null, null, tableName);

        if (primaryKeysResult.next()) {

            String pkName = primaryKeysResult.getString("COLUMN_NAME");
            int pkSeq = primaryKeysResult.getInt("KEY_SEQ");
            primaryKeysResult.close();
            return new Pair<String, Integer>(pkName, pkSeq);
        }

        return null;
    }

    /**
     * 加载所有表名列表
     *
     * @param connection 连接
     * @return
     */
    public static List<String> loadAllTableName(Connection connection) throws SQLException {
        ((OracleConnection) connection).setRemarksReporting(true);
        ResultSet tableResult = null;
        if (connection.getMetaData().getDriverName().equals("Oracle JDBC driver")) {
            tableResult = connection.getMetaData().getTables(null, connection.getMetaData().getUserName(), "%", new String[]{"TABLE"});
        } else {
            tableResult = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        }
        List<String> tableNameList = new ArrayList<String>();
        while (tableResult.next()) {

            String tableName = tableResult.getString("TABLE_NAME");
            log.info(String.format("load table name 【%s】 success!", tableName));
            log.info("--------------------------------------------------------");
            tableNameList.add(tableName);
        }
        return tableNameList;
    }


    /**
     * 校验表名是否合法
     *
     * @param connection 连接
     * @param tableNames 表名列表
     */
    public static void checkTableName(Connection connection, List<String> tableNames) throws SQLException {
        for (String tableName : tableNames) {
            ResultSet tableResult = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});
            if (!tableResult.next()) {
                throw new PandaException(String.format("tableName:[%s] not found", tableName));
            }
        }

    }
}
