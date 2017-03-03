package com.blog.entity;

import java.util.Date;

public class BlogInfo {
	private long blogInfoId;
	private String mark;
	private String title;
	private String content;
	private String typeNumber;
	private Date createTime;

	public long getBlogInfoId() {
		return blogInfoId;
	}



	public void setBlogInfoId(long blogInfoId) {
		this.blogInfoId = blogInfoId;
	}



	public String getMark() {
		return mark;
	}



	public void setMark(String mark) {
		this.mark = mark;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTypeNumber() {
		return typeNumber;
	}



	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createDate) {
		this.createTime = createDate;
	}



	public String toString() {
		return "BlogInfo{" + "mark:" + mark + "title:" + title + "content:"
				+ content + "typeNumber:" + typeNumber + "createDate:"
				+ createTime + "}";

	}
}
