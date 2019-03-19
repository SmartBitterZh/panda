package ${modelPackage};

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

<#if table.includeDateType>
import    java.util.Date;
</#if>
<#if table.includeDecimal>
import    java.match.BigDecimal;
</#if>
/***
 * @author zhangjianlong6
 * @Date: ${.now}
 * @Description: do for ${table.remark}
 */
@Data
@ToString(callSuper = true)
public class ${table.modelName}DO implements Serializable {



<#list  table.fieldList as field>
	/**
     * ${field.remark}
     */
	private ${field.fieldType} ${field.fieldName};

</#list>

}
