package com.st.qunar.order.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 通用计数类
 * 
 * @author kxhu
 * 
 */
@Entity
@Table(name = "st_common_count")
public class CommonCount extends IdEntity {
	private String typeName;
	private Long count;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}