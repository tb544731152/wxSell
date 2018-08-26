package com.weixin.sell.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
@Entity
@DynamicUpdate
public class ProductCategory {
	/*
	 * `category_id` int(11) NOT NULL AUTO_INCREMENT, `category_name`
	 * varchar(64) NOT NULL COMMENT '类目名字', `category_type` int(11) NOT NULL
	 * COMMENT '类目编号', `create_time` timestamp NOT NULL DEFAULT
	 * CURRENT_TIMESTAMP COMMENT '创建时间', `update_time` timestamp NOT NULL
	 * DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	 * PRIMARY KEY (`category_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	/** 类目id. */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryType=" + categoryType
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}
