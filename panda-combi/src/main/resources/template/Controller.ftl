package	${servicePackage};

import com.hikvision.intelb2b.core.base.DataResult;
import com.hikvision.intelb2b.core.base.Pageable;
import ${modelPackage}.${table.modelName}DTO;
import ${servicePackage}.${table.modelName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

/***
 * @author zhangjianlong6
 * @Date: ${.now}
 * @Description: controller for ${table.remark}
 */
@RestController
@CrossOrigin(value = CorsConfiguration.ALL)
@Api(description = "controller for ${table.remark}")
public class ${table.modelName}Controller extends PortalManagerBaseController

	@Autowired
	private ${table.modelName}Service ${table.modelName?uncap_first}Service;


}
