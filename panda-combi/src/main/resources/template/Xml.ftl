<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${table.modelName}Dao">
    <resultMap type="${modelPackage}.${table.modelName}" id="${table.modelName}Map">
    <#list  table.fieldList as field >
        <result column="${field.columnName}" property="${field.fieldName}"/>
    </#list>
    </resultMap>

    <sql id="_field_list">
 <#list  table.fieldList as field >
    <#if (table.fieldList?size -1) ==  field_index>
         `${field.columnName}`
    <#else >
         `${field.columnName}`,
    </#if>
 </#list>
    </sql>

    <sql id="_value_list">
 <#list  table.fieldList as field >
 <#if (table.fieldList?size -1) ==  field_index>
     <#noparse>#</#noparse>{${field.fieldName}}
 <#else >
     <#noparse>#</#noparse>{${field.fieldName}},
 </#if>
 </#list>
    </sql>

    <sql id="_batch_value_list">
 <#list  table.fieldList as field >
 <#if (table.fieldList?size -1) ==  field_index>
     <#noparse>#</#noparse>{item.${field.fieldName}}
 <#else >
     <#noparse>#</#noparse>{item.${field.fieldName}},
 </#if>
 </#list>
    </sql>

    <sql id="_common_where">
 <#list  table.fieldList as field >
     <if test="${field.fieldName} !=null">AND `${field.columnName}`=<#noparse>#</#noparse>{${field.fieldName}}</if>
 </#list>
    </sql>

    <sql id="_common_sort">
        <if test="sorts !=null">
            ORDER BY
            <foreach collection="sorts" item="sort" separator=",">
            <#noparse>${sort.columnName} ${sort.sortMode.mode}</#noparse>
            </foreach>
        </if>
    </sql>

    <sql id="_page_field">
        <if test="offset != null and rows !=null">
           <#noparse>LIMIT #{offset},#{rows}</#noparse>
        </if>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyProperty="${table.pkFieldName}">
        INSERT INTO `${table.tableName}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_field_list"/>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_value_list"/>
        </trim>
    </insert>

    <insert id="batchInsert" useGeneratedKeys="true" keyProperty="${table.pkFieldName}">
        INSERT INTO `${table.tableName}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_field_list"/>
        </trim>
        VALUES
        <foreach collection="list" item="item" separator=",">
            (<include refid="_batch_value_list"/>)
        </foreach>

    </insert>

    <select id="selectById" parameterType="java.lang.${table.pkFieldType}" resultMap="${table.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${table.tableName}`
        WHERE `${table.pkColumnName}` = <#noparse>#</#noparse>{${table.pkFieldName}} limit 1
    </select>

    <select id="selectByIdForUpdate" parameterType="java.lang.${table.pkFieldType}" resultMap="${table.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${table.tableName}`
        WHERE `${table.pkColumnName}` = <#noparse>#</#noparse>{${table.pkFieldName}} limit 1 FOR UPDATE
    </select>

    <delete id="deleteById" parameterType="java.lang.${table.pkFieldType}">
        DELETE FROM `${table.tableName}` WHERE `${table.pkColumnName}` = <#noparse>#</#noparse>{${table.pkFieldName}} limit 1
    </delete>

    <update id="updateById">
        UPDATE `${table.tableName}`
        <trim prefix="SET" suffixOverrides="," prefixOverrides=",">
<#list  table.fieldList as field >
        <#if field.columnName !=table.pkColumnName >
            <if test="${field.fieldName} !=null">`${field.columnName}`=<#noparse>#</#noparse>{${field.fieldName}},</if>
        </#if>
    </#list>
        </trim>
        WHERE `${table.pkColumnName}`=<#noparse>#</#noparse>{${table.pkFieldName}} LIMIT 1
    </update>

    <update id="updateByQuery">
        UPDATE `${table.tableName}`
        <trim prefix="SET" suffixOverrides="," prefixOverrides=",">
<#list  table.fieldList as field >
        <#if field.columnName !=table.pkColumnName >
            <if test="po.${field.fieldName} !=null">`${field.columnName}`=<#noparse>#</#noparse>{po.${field.fieldName}},</if>
        </#if>
</#list>
        </trim>
        <where>
        <#list  table.fieldList as field >
            <if test="query.${field.fieldName} !=null">AND `${field.columnName}`=<#noparse>#</#noparse>{query.${field.fieldName}}</if>
        </#list>
        </where>
    </update>

    <select id="queryListByParam" parameterType="map" resultMap="${table.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${table.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
        <include refid="_common_sort"/>
        <include refid="_page_field"/>
    </select>

    <select id="queryCountByParam" parameterType="map" resultType="java.lang.Integer">
        SELECT
        COUNT(*)
        FROM `${table.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
    </select>

    <select id="queryPkListByParam" parameterType="map" resultType="java.lang.${table.pkFieldType}">
        SELECT
            `${table.pkColumnName}`
        FROM `${table.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
        <include refid="_common_sort"/>
        <include refid="_page_field"/>
    </select>

</mapper>