package com.shotdog.panda.web.controller;

import com.shotdog.panda.combi.component.DbConfComponent;
import com.shotdog.panda.common.Result;
import com.shotdog.panda.common.callback.Callback;
import com.shotdog.panda.common.callback.support.ExecuteCallbackTemplate;
import com.shotdog.panda.common.model.Pager;
import com.shotdog.panda.common.model.Table;
import com.shotdog.panda.inner.model.DbConf;
import com.shotdog.panda.inner.query.DbConfQuery;
import com.shotdog.panda.inner.query.SortMode;
import com.shotdog.panda.inner.query.SortModeEnum;
import com.shotdog.panda.web.check.DbConfigCheckComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 *
 *  数据库配置 controller
 * @author ziming  Create At 2018-12-01 10:14
 *
 */
@RestController
@RequestMapping("/db/config")
public class DbConfController {


    private final static Logger log = LoggerFactory.getLogger(DbConfController.class);

    @Resource
    private ExecuteCallbackTemplate callbackTemplate;

    @Resource
    private DbConfComponent dbConfComponent;

    @Resource
    private DbConfigCheckComponent dbConfigCheckComponent;


    /**
     * 分页条件查询数据库配置列表
     *
     * @param query    查询条件
     * @param pageNo   当前页
     * @param pageSize 每页显示条数
     * @return
     */
    @PostMapping("/query/list")
    public Result<Pager<DbConf>> queryDbConfigListForPage(final DbConfQuery query, @RequestParam(defaultValue = "1") final Integer pageNo, @RequestParam(defaultValue = "10") final Integer pageSize) {

        return this.callbackTemplate.execute(new Callback<Pager<DbConf>>() {
            public Result<Pager<DbConf>> doExecute() {

                Pager<DbConf> pager = new Pager<DbConf>(pageNo, pageSize);

                int totalCount = dbConfComponent.queryCountByParam(query);
                pager.setTotalCount(totalCount);
                if (totalCount > 0) {

                    int offset = (pageNo - 1) * pageSize;
                    query.setOffset(offset);
                    query.setRows(pageSize);
                    List<SortMode> sortModeList = new ArrayList<SortMode>();
                    sortModeList.add(new SortMode("id", SortModeEnum.DESC));
                    query.setSorts(sortModeList);

                    List<DbConf> dbConfList = dbConfComponent.queryListByParam(query);
                    pager.setList(dbConfList);
                }
                return Result.buildSuc(pager);


            }
        }, log, "分页查询数据库配置列表失败");
    }


    /**
     * 新建数据库配置
     *
     * @param dbConf 数据库配置
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(final DbConf dbConf) {

        return this.callbackTemplate.execute(new Callback<Boolean>() {
            public Result<Boolean> doExecute() {

                dbConfigCheckComponent.checkParam(dbConf);
                dbConf.setCreateTime(new Date());
                Boolean sec = dbConfComponent.addConf(dbConf);
                return Result.buildSuc(sec);
            }
        }, log, "添加失败,请重试");
    }


    /**
     * 新建数据库配置
     *
     * @param dbConf 数据库配置
     * @return
     */
    @PostMapping("/edit")
    public Result<Boolean> edit(final DbConf dbConf) {

        return this.callbackTemplate.execute(new Callback<Boolean>() {
            public Result<Boolean> doExecute() {

                dbConfigCheckComponent.checkParam(dbConf);
                Assert.notNull(dbConf.getId(), "此配置记录不存在!");
                dbConf.setModifyTime(new Date());
                Boolean sec = dbConfComponent.editConf(dbConf);
                return Result.buildSuc(sec);
            }
        }, log, "修改失败,请重试");
    }

    /**
     * 删除数据库配置
     *
     * @param id 数据库配置主键
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> edit(final Long id) {

        return this.callbackTemplate.execute(new Callback<Boolean>() {
            public Result<Boolean> doExecute() {

                Assert.notNull(id, "此配置记录不存在!");
                Boolean sec = dbConfComponent.deleteConfById(id);
                return Result.buildSuc(sec);
            }
        }, log, "删除失败,请重试");
    }


    /**
     * 根据数据库配置id查询表列表
     *
     * @param id 数据库配置id
     * @return
     */
    @PostMapping("/querytablelistbydbid")
    public Result<List<Table>> queryTableListByDbId(final Long id) {


        return this.callbackTemplate.execute(new Callback<List<Table>>() {
            public Result<List<Table>> doExecute() {
                Assert.notNull(id, "请选择数据库配置");
                List<Table> tableList = dbConfComponent.queryTableListByDbId(id);

                return Result.buildSuc(tableList);
            }
        }, log, "加载表列表信息失败");

    }
}
