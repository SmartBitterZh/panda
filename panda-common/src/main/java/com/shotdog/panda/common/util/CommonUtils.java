package com.shotdog.panda.common.util;

import com.shotdog.panda.common.enums.DatabaseTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/***
 * 通用工具类
 * @author ziming  Create At 2018-11-24 11:07
 *
 */
public class CommonUtils {

    /***
     * 下划线转驼峰
     * @param str 内容
     * @return
     */
    public static String toHump(String str) {


        StringBuilder sb = new StringBuilder();

        //  内容不为空
        if (StringUtils.isNoneBlank(str)) {

            boolean hit = false;
            for (int i = 0; i < str.length(); i++) {

                char c = str.charAt(i);
                if ("_".charAt(0) == c) {
                    hit = true;
                } else {
                    //  命中
                    if (hit) {
                        sb.append(Character.toUpperCase(c));
                        hit = false;

                    } else {
                        sb.append(c);
                    }

                }
            }
        }
        return sb.toString();
    }


    /***
     * 将jdbcType转成javaType
     * @param jdbcType jdbc 类型
     * @return
     */
    public static String toJavaType(String jdbcType, Integer length, Integer digits) {
        if (jdbcType.equals("CHAR") || jdbcType.equals("VARCHAR") || jdbcType.equals("VARCHAR2") || jdbcType.equals("LONGVARCHAR") || jdbcType.equals("TEXT") || jdbcType.equals("LONGTEXT")) {
            return "String";
        } else if (length <= 0 && (jdbcType.equals("NUMERIC") || jdbcType.equals("DECIMAL"))) {
            return "BigDecimal";
        } else if (length > 0 && jdbcType.equals("NUMERIC")) {
            if (digits > 0) {
                return "BigDecimal";
            } else if (length < 7) {
                return "Integer";
            } else {
                return "Long";
            }
        } else if (jdbcType.equals("BIT") || jdbcType.equals("BOOLEAN")) {
            return "boolean";
        } else if (jdbcType.equals("TINYINT") || jdbcType.equals("SMALLINT") || jdbcType.equals("INTEGER") || jdbcType.equals("INT") || jdbcType.equals("INT UNSIGNED") || jdbcType.equals("SMALLINT UNSIGNED") || jdbcType.equals("TINYINT UNSIGNED")) {
            return "Integer";
        } else if (jdbcType.equals("BIGINT") || jdbcType.equals("LONG") || jdbcType.equals("BIGINT UNSIGNED")) {
            return "Long";
        } else if (jdbcType.equals("DATE") || jdbcType.equals("DATETIME")) {
            return "Date";
        } else if (jdbcType.equals("TIME")) {
            return "Time";
        } else if (jdbcType.equals("TIMESTAMP")) {
            return "Timestamp";
        } else if (jdbcType.equals("YEAR")) {
            return "Year";
        } else if (jdbcType.equals("FLOAT")) {
            return "Float";
        } else if (jdbcType.equals("DOUBLE")) {
            return "Double";
        } else if (jdbcType.equals("CLOB")) {
            return "Clob";
        } else if (jdbcType.equals("BLOB")) {
            return "Blob";
        } else if (jdbcType.equals("BIT")) {
            return "Boolean";
        }

        return jdbcType;

    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String toFirstUpper(String str) {

        if (StringUtils.isBlank(str)) return "";

        if (Character.isUpperCase(str.charAt(0))) return str;

        return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toFirstLower(String str) {

        if (StringUtils.isBlank(str)) return "";

        if (Character.isLowerCase(str.charAt(0))) return str;

        return new StringBuilder().append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }


    /**
     * 字母小写
     *
     * @param str
     * @return
     */
    public static String toLower(String str) {

        if (StringUtils.isBlank(str)) return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (Character.isUpperCase(str.charAt(i))) {

                sb.append(Character.toLowerCase(str.charAt(i)));
            } else {

                sb.append(str.charAt(i));
            }
        }


        return sb.toString();
    }


    /**
     * 字母大写
     *
     * @param str
     * @return
     */
    public static String toUpper(String str) {

        if (StringUtils.isBlank(str)) return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (Character.isLowerCase(str.charAt(i))) {

                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {

                sb.append(str.charAt(i));
            }
        }


        return sb.toString();
    }


    public static void createDirNotExist(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    /**
     * 获取jdbc url
     *
     * @param dbName 数据库名
     * @param host   地址
     * @param port   端口
     * @return
     */
    public static String generateJdbcUrl(String dbName, Integer type, String host, int port) {

        if (DatabaseTypeEnum.MYSQL.getValue() == type) {
            return String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", host, port, dbName);
        } else if (DatabaseTypeEnum.ORACLE.getValue() == type) {

            return String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, dbName);
        }

        throw new IllegalArgumentException("不支持数据库类型");
    }


    /**
     * 获取驱动类
     *
     * @param type 数据库类型
     * @return
     */
    public static String generateDriverClass(Integer type) {

        if (DatabaseTypeEnum.MYSQL.getValue() == type) {
            return "com.mysql.jdbc.Driver";
        } else if (DatabaseTypeEnum.ORACLE.getValue() == type) {
            return "oracle.jdbc.driver.OracleDriver";
        }

        throw new IllegalArgumentException("不支持数据库类型");
    }

    /**
     * 是否为Date 类型
     *
     * @param javaType java 类型
     * @return
     */
    public static boolean isDateType(String javaType) {

        return "Date".equalsIgnoreCase(javaType);
    }


    /**
     * 是否为 BigDecimal 类型
     *
     * @param javaType java 类型
     * @return
     */
    public static boolean isBigDecimalType(String javaType) {

        return "BigDecimal".equalsIgnoreCase(javaType);
    }
}
