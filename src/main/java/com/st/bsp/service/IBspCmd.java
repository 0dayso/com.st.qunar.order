package com.st.bsp.service;

/**
 * @author kxhu
 * 
 */
public interface IBspCmd {
	// ➢ 航班时刻查询
	// ➢ 航班座位可利用状况查询
	// ➢ 航班经停点查询
	// ➢ 运价查询
	// ➢ 散客、团队旅客实时订座
	// ➢ 实时旅客订座记录查询
	// ➢ 散客、团队旅客订座记录修改删除
	// ➢ 航班最低运价信息查询

	public String execCmd(String cmd);
}
