package com.shotdog.panda.web.check;

import com.shotdog.panda.inner.model.DbConf;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/***
 *
 * @author ziming  Create At 2018-12-01 12:25
 *
 */
@Component
public class DbConfigCheckComponent {


    public void checkParam(DbConf dbConf){

        Assert.notNull(dbConf,"请输入配置名称");
        Assert.hasText(dbConf.getName(),"配置名称不能为空");
        Assert.notNull(dbConf.getType(),"请选择数据库类型");
        Assert.hasText(dbConf.getDbName(),"数据库名不能为空");
        Assert.hasText(dbConf.getHost(),"数据库地址不能为空");
        Assert.notNull(dbConf.getPort(),"数据库端口不能为空");
        Assert.notNull(dbConf.getUserName(),"数据库用户名不能为空");
        Assert.notNull(dbConf.getPassword(),"数据库密码不能为空");
    }
}
