package com.shotdog.panda.combi.component;

import com.shotdog.panda.common.model.Table;
import com.shotdog.panda.inner.model.DbConf;
import com.shotdog.panda.inner.query.DbConfQuery;

import java.util.List;

/***
 * 数据库配置组合服务
 * @author ziming  Create At 2018-12-01 10:05
 *
 */
public interface DbConfComponent {
    /**
     * 条件查询数据库配置总数
     * @param query 查询条件
     * @return
     */
    int queryCountByParam(DbConfQuery query);


    /**
     * 条件分页查询
     * @param query 查询条件
     * @return
     */
    List<DbConf> queryListByParam(DbConfQuery query);


    /**
     * 新建数据库配置
     * @param dbConf 数据库配置
     * @return
     */
    Boolean addConf(DbConf dbConf);

    /**
     * 修改数据库配置
     * @param dbConf 数据库配置
     * @return
     */
    Boolean editConf(DbConf dbConf);

    /**
     * 根据主键删除数据库配置
     * @param id
     * @return
     */
    Boolean deleteConfById(Long id);

    /**
     * 根据数据库配置id查询表数据列表
     * @param id 数据库配置id
     * @return
     */
    List<Table> queryTableListByDbId(Long id);
}
