package com.shotdog.panda.combi.component.impl;

import com.shotdog.panda.combi.component.DbConfComponent;
import com.shotdog.panda.common.model.DataBase;
import com.shotdog.panda.common.model.Table;
import com.shotdog.panda.common.util.CommonUtils;
import com.shotdog.panda.common.util.MetaDataUtils;
import com.shotdog.panda.inner.model.DbConf;
import com.shotdog.panda.inner.query.DbConfQuery;
import com.shotdog.panda.inner.service.DbConfService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/***
 * 数据库配置组合服务
 * @author ziming  Create At 2018-12-01 10:06
 *
 */
@Component
public class DbConfComponentImpl implements DbConfComponent {


    @Resource
    private DbConfService dbConfService;


    public int queryCountByParam(DbConfQuery query) {
        return this.dbConfService.queryCountByParam(query);
    }

    public List<DbConf> queryListByParam(DbConfQuery query) {
        return this.dbConfService.queryListByParam(query);
    }

    /**
     * 新建数据库配置信息
     *
     * @param dbConf 数据库配置
     * @return
     */
    public Boolean addConf(DbConf dbConf) {
        return this.dbConfService.insert(dbConf) > 0;
    }

    /**
     * 修改数据库配置
     *
     * @param dbConf 数据库配置
     * @return
     */
    public Boolean editConf(DbConf dbConf) {

        return this.dbConfService.updateById(dbConf) > 0;
    }

    public Boolean deleteConfById(Long id) {

        DbConf dbConf = this.dbConfService.selectById(id);
        Assert.notNull(dbConf, "此配置记录不存在!");
        return this.dbConfService.deleteById(id) > 0;
    }


    /**
     * 根据数据库配置id查询表数据列表
     * @param id 数据库配置id
     * @return
     */
    public List<Table> queryTableListByDbId(Long id) {

        DbConf dbConf = this.dbConfService.selectById(id);
        Assert.notNull(dbConf, "数据库配置信息不存在");
        String dbName = dbConf.getDbName();
        String host = dbConf.getHost();
        Integer type = dbConf.getType();
        Integer port = dbConf.getPort();
        String userName = dbConf.getUserName();
        String password = dbConf.getPassword();

        String jdbcUrl = CommonUtils.generateJdbcUrl(dbName, type, host, port);
        String driverClass = CommonUtils.generateDriverClass(type);

        try {
            DataBase dataBase = new DataBase(jdbcUrl, userName, password, driverClass);
            return MetaDataUtils.loadTableList(dataBase.getConnection(), null);
        } catch (Exception e) {
            throw new IllegalArgumentException("加载数据库表失败", e);
        }
    }
}
