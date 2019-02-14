package com.shotdog.panda.inner.query;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/***
 *
 * @author Create By AutoGenerator
 */
@Data
@ToString(callSuper = true)
public class DbConfQuery extends BaseQuery {


	// 主键
	private Long id;

	// 配置名称
	private String name;

	// 数据名
	private String dbName;

	// 数据类型 1-mysql  2-oracle
	private Integer type;

	// 数据库地址
	private String host;

	// 端口
	private Integer port;

	// 用户名
	private String userName;

	// 密码
	private String password;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;


}
