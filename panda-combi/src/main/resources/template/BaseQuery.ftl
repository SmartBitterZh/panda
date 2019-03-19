package	${queryPackage};

import	lombok.Data;

import	java.io.Serializable;
import	java.util.List;

/***
* @author zhangjianlong6
* @Date: ${.now}
* @Description: base query
*/
@Data
public class BaseQuery implements Serializable {


	private List<SortMode> sorts;

    private Integer offset;

    private Integer rows;

}
