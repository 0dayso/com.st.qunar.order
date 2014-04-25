package com.st.qunar.order.entity;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author kxhu
 * 
 */
public class Msg {
	// 接口返回的错误信息
	private String content;

	@XmlAttribute
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
