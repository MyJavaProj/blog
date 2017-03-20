package com.blog.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

/**
 * InternalResourceViewResolver 会永远返回一个视图，即使在查找不到合适的视图的情况下也不会返回 null，导致后续的视图解析器不会被调用。
 * 所以要设置解析器调用优先级
 * 
 * */
public class GenericFileViewResolver extends AbstractCachingViewResolver implements Ordered{
	final static int BUFFER_SIZE = 4096;
	
	private int order = Integer.MIN_VALUE;
	private String location;
	private String viewName;
	
	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		if (locale == null) {
			throw new Exception("No location Specified for GenericFileViewResolver.");
		}
		
		String requestedFilePath = location + viewName;
		Resource resource = null;
		try {
			resource = getApplicationContext().getResource(requestedFilePath);
		} catch (Exception e) {
			System.out.println("No file fond for file:" + location);
//			在找不到请求文件的时候，需要返回 null，这样 Spring 容器中所注册的其他低优先级的视图解析器才能被调用。
			return null;
		}
		
		GenericFileView view = this.getApplicationContext().getBean(this.viewName, GenericFileView.class);
		view.setUrl(requestedFilePath);
		view.setResponseContent(inputStreamTOString(resource.getInputStream(), "ISO-8859-1"));
		return view;
	}
	
	
	/**
     * Convert Input to String based on specific encoding
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
	public static String inputStreamTOString(InputStream in,String encoding) throws Exception{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data,0,BUFFER_SIZE)) != -1) {
			outputStream.write(data,0,count);
		}
		data = null;
		return new String(outputStream.toByteArray(),encoding);
	}
}
