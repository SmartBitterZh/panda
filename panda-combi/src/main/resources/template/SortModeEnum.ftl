package	${queryPackage};

import lombok.Getter;
/***
 * @author zhangjianlong6
 * @Date: ${.now}
 * @Description: sort model enum
 */
@Getter
public enum SortModeEnum {


	 ASC("asc"),
	 DESC("desc");

	private String mode;

	 SortModeEnum(String mode) {
		this.mode = mode;
	}
}
