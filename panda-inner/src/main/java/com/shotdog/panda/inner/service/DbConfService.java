package com.shotdog.panda.inner.service;

import com.shotdog.panda.inner.dao.DbConfDao;
import com.shotdog.panda.inner.model.DbConf;
import com.shotdog.panda.inner.query.DbConfQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 *
 * @author Create By AutoGenerator
 */
@Service
public class DbConfService implements BaseService<Long, DbConf, DbConfQuery> {


    @Resource
    private DbConfDao dbConfDao;


    public int insert(DbConf dbConf) {
        return this.dbConfDao.insert(dbConf);
    }

    public int updateById(DbConf dbConf) {
        return this.dbConfDao.updateById(dbConf);
    }

    public DbConf selectById(Long id) {
        return this.dbConfDao.selectById(id);
    }

    public DbConf selectByIdForUpdate(Long id) {
        return this.dbConfDao.selectByIdForUpdate(id);
    }

    public int deleteById(Long id) {
        return this.dbConfDao.deleteById(id);
    }

    public List<DbConf> queryListByParam(DbConfQuery dbConfQuery) {
        return this.dbConfDao.queryListByParam(dbConfQuery);
    }

    public int queryCountByParam(DbConfQuery dbConfQuery) {
        return this.dbConfDao.queryCountByParam(dbConfQuery);
    }

}
