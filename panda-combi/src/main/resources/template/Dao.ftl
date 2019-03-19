package    ${daoPackage};

import	${modelPackage}.${table.modelName}DO;
import    ${queryPackage}.${table.modelName}Query;
/***
* @author zhangjianlong6
* @Date: ${.now}
* @Description: dao for ${table.remark}
*/
public interface ${table.modelName}Dao extends BaseDao<${table.pkFieldType},${table.modelName},${table.modelName}Query> {


}
