package	${daoPackage};

import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 *
 * @author Create By Panda
 */
public interface BaseDao<PK, PO, QO> {


	/***
     * 插入记录
     * @param po 实体
     * @return
     */
	int insert(PO po);


    /***
     * 批量插入
     * @param list 待插入列表
     */
    int batchInsert(List<PO> list);


	/***
     * 根据主键更新记录
     * @param po 实体
     * @return
     */
	int updateById(PO po);


    /**
     * 根据条件更新对象
     * @param po 对象
     * @param qo 条件
     * @return
     */
    int updateByQuery(@Param("po")PO po,@Param("query")QO qo);


	/***
     * 根据主键查询记录详情
     * @param pk 主键
     * @return
     */
	PO selectById(PK pk);


	/***
     * 带有行锁根据主键查询记录详情
     * @param pk 主键
     * @return
     */
	PO selectByIdForUpdate(PK pk);


	/***
     * 根据主键删除记录
     * @param pk 主键
     * @return
     */
	int deleteById(PK pk);


	/***
     * 根据参数分页查询记录列表
     * @param qo 查询条件
     * @return
     */
	List<PO> queryListByParam(QO qo);


    /***
    * 根据查询条件查询记录总是
    * @param qo 查询条件
    * @return
    */
    int queryCountByParam(QO qo);


    /***
    * 根据查询条件查询主键列表
    * @param qo 查询条件
    * @return
    */
    List<PK> queryPkListByParam(QO qo);

}
