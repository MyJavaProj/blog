package com.blog.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * 自定义通用视图类代码
 * 主要就是设置文件内容类型和将响应的内容设置到请求响应对象中。
 * 
 */
public class GenericFileView extends AbstractUrlBasedView{

	//默认内容类型
	private final static String CONTENT_TYPE = "text/plain";
	private String responseContent ;
	
	public GenericFileView() {
		super();
		setContentType(CONTENT_TYPE);
	}
	
	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	};
	
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType(getContentType());
		response.getWriter().write(this.responseContent);
		response.getWriter().close();
	}
	
}
