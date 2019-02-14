package	${queryPackage};

import lombok.Getter;
/***
 *
 * @author create by Panda
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
