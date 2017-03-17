//package com.blog.service;
//
//import java.io.IOException;
//
//import org.junit.Test;
//
//public class AxbInterfaceTest extends BaseTest{
//	
//	@Test
//	public void axbTest(){
//		System.out.println("====1==");
//	}
//	
//	public String postJson(String url,String jsonParams){
//		String content = null;
//		try {
//			HttpPost post = new HttpPost(url);
//			StringEntity se = new StringEntity(jsonParams, "utf-8");
//			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, Consts.UTF_8.toString()));
//			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
//			post.setEntity(se);
//
//			post.setHeader("Accept:","application/json");
//			post.setHeader("Authorization", "WSSE realm=\"NGIN\", profile=\"UsernameToken\", type=\"Appkey\"");
//			post.setHeader("X-WSSE", "UsernameToken Username=\"XXXX\", PasswordDigest=\"weYI3nXd8LjMNVksCKFV8t3rgHh3Rw==\", Nonce=\"WScqanjCEAC4mQoBE07sAQ==\", Created=\"2009-03-24T12:30:04Z\"");
//			DefaultHttpClient client = new DefaultHttpClient();
//			HttpResponse response = client.execute(post);
//			byte[] bResultXml = EntityUtils.toByteArray(response.getEntity());
//			content = new String(bResultXml,"utf-8");
//			post.abort();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return content;
//	}
//}