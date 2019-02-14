package ${queryPackage};

import lombok.Data;
import lombok.ToString;

<#if table.includeDateType>
import    java.util.Date;
</#if>
<#if table.includeDecimal>
import    java.match.BigDecimal;
</#if>
/***
 *
 * @author Create By Panda
 */
@Data
@ToString(callSuper = true)
public class ${table.modelName}Query extends BaseQuery {


<#list  table.fieldList as field>
	// ${field.remark}
	private ${field.fieldType} ${field.fieldName};

</#list>

}
