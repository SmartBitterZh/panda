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
 *
 * @author Create By Panda
 */
@Data
@ToString(callSuper = true)
public class ${table.modelName} implements Serializable {



<#list  table.fieldList as field>
	// ${field.remark}
	private ${field.fieldType} ${field.fieldName};

</#list>

}
