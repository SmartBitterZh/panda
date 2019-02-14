package	${servicePackage};

import	${modelPackage}.${table.modelName};
import	${queryPackage}.${table.modelName}Query;
import	${daoPackage}.${table.modelName}Dao;
import	org.springframework.stereotype.Service;


import	javax.annotation.Resource;
import	java.util.List;
/***
 *
 * @author Create By Panda
 */
@Service
public class ${table.modelName}Service implements BaseService<${table.pkFieldType},${table.modelName},${table.modelName}Query> {


	@Resource
	private ${table.modelName}Dao ${table.modelName?uncap_first}Dao;


	/***
     * 插入记录
     * @param ${table.modelName?uncap_first} 实体
     * @return
     */
	public int insert(${table.modelName} ${table.modelName?uncap_first}) {
		return this.${table.modelName?uncap_first}Dao.insert(${table.modelName?uncap_first});
	}


	/***
     * 批量插入
     * @param list 待插入列表
     * @return
     */
	public int batchInsert(List<${table.modelName}> list) {
		return this.${table.modelName?uncap_first}Dao.batchInsert(list);
	}


	/***
     * 根据主键更新记录
     * @param ${table.modelName?uncap_first} 实体
     * @return
     */
	public int updateById(${table.modelName} ${table.modelName?uncap_first}) {
		return this.${table.modelName?uncap_first}Dao.updateById(${table.modelName?uncap_first});
	}


    /**
     * 根据条件更新对象
     * @param ${table.modelName?uncap_first} 对象
     * @param query 条件
     * @return
     */
    public int updateByQuery(${table.modelName} ${table.modelName?uncap_first},${table.modelName}Query query){
        return this.${table.modelName?uncap_first}Dao.updateByQuery(${table.modelName?uncap_first},query);
    }


	/***
     * 根据主键查询记录详情
     * @param ${table.pkFieldName} 主键
     * @return
     */
	public ${table.modelName} selectById(${table.pkFieldType} ${table.pkFieldName}) {
		return this.${table.modelName?uncap_first}Dao.selectById(${table.pkFieldName});
	}


	/***
     * 带有行锁根据主键查询记录详情
     * @param ${table.pkFieldName} 主键
     * @return
     */
	public ${table.modelName} selectByIdForUpdate(${table.pkFieldType} ${table.pkFieldName}) {
		return this.${table.modelName?uncap_first}Dao.selectByIdForUpdate(${table.pkFieldName});
	}


	/***
     * 根据主键删除记录
     * @param ${table.pkFieldName} 主键
     * @return
     */
	public int deleteById(${table.pkFieldType} ${table.pkFieldName}) {
		return this.${table.modelName?uncap_first}Dao.deleteById(${table.pkFieldName});
	}


	/***
     * 根据参数分页查询记录列表
     * @param query 查询条件
     * @return
     */
	public List<${table.modelName}> queryListByParam(${table.modelName}Query query) {
        return this.${table.modelName?uncap_first}Dao.queryListByParam(query);
    }


    /***
     * 根据查询条件查询记录总是
     * @param query 查询条件
     * @return
     */
    public int queryCountByParam(${table.modelName}Query query) {
        return this.${table.modelName?uncap_first}Dao.queryCountByParam(query);
    }


    /***
     * 根据查询条件查询主键列表
     * @param query 查询条件
     * @return
     */
    public List<${table.pkFieldType}> queryPkListByParam(${table.modelName}Query query){
           return this.${table.modelName?uncap_first}Dao.queryPkListByParam(query);
    }

}
