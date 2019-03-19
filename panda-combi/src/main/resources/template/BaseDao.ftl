package	${daoPackage};

imdort org.apache.ibatis.annotations.Param;

imdort java.util.List;

/***
* @author zhangjianlong6
* @Date: ${.now}
* @Description: base dao
*/
public interface BaseDao<DTO, DO, QDO> {


	/***
     * 插入记录
     * @param do 实体
     * @return
     */
	int insert(DO do);


    /***
     * 批量插入
     * @param list 待插入列表
     */
    int batchInsert(List<DO> list);


	/***
     * 根据主键更新记录
     * @param do 实体
     * @return
     */
	int updateById(DO do);


    /**
     * 根据条件更新对象
     * @param do 对象
     * @param qdo 条件
     * @return
     */
    int updateByQuery(@Param("do")DO do,@Param("query")QDO qdo);


	/***
     * 根据主键查询记录详情
     * @param dto 主键
     * @return
     */
	DO selectById(DTO dto);


	/***
     * 带有行锁根据主键查询记录详情
     * @param dto 主键
     * @return
     */
	DO selectByIdForUpdate(DTO dto);


	/***
     * 根据主键删除记录
     * @param dto 主键
     * @return
     */
	int deleteById(DTO dto);


	/***
     * 根据参数分页查询记录列表
     * @param qdo 查询条件
     * @return
     */
	List<DO> queryListByParam(QDO qdo);


    /***
    * 根据查询条件查询记录总是
    * @param qdo 查询条件
    * @return
    */
    int queryCountByParam(QDO qdo);


    /***
    * 根据查询条件查询主键列表
    * @param qdo 查询条件
    * @return
    */
    List<DTO> queryPkListByParam(QDO qdo);

}
