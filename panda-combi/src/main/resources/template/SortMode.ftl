package	${queryPackage};

import lombok.Data;

import java.io.Serializable;

/***
 * @author zhangjianlong6
 * @Date: ${.now}
 * @Description: sort model
 */
@Data
public class SortMode implements Serializable {


	private String columnName;

	private SortModeEnum sortMode;


	public SortMode(String columnName, SortModeEnum sortMode) {
		this.columnName = columnName;
		this.sortMode = sortMode;
	}
}
