package com.blog.common;

import java.util.Date;
import java.lang.reflect.Method;

import org.junit.Test;

import com.blog.dao.BaseTest;


public class commonTest extends BaseTest {
	@Test
	public void MethodNoteTest() throws NoSuchMethodException, SecurityException {
		System.out.println("==1===");
		Class classOfDateUtil = CommonUtil.class;
		 Method formatDate =
		 classOfDateUtil.getMethod("formatDate",Date.class,String.class);

//		Method formatDate = classOfDateUtil.getMethod("formatDate",
//				new Class[] { Date.class, String.class });
		MethodNote methodNote = formatDate.getAnnotation(MethodNote.class);

		System.out.println("方法描述：" + methodNote.description());
		System.out.println("方法创建时间：" + methodNote.createTime());
	}

}

// Method formatDate = classOfDateUtil.getMethod("formatDate",
// Date.class,String.class);
// MethodNote methodNote = formatDate.getAnnotation(MethodNote.class);
