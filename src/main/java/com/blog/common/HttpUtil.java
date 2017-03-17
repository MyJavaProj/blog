package com.blog.common;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class HttpUtil {
//	org.slf4j.Logger loo = new org.slf4j.Logger();
	Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//	logger.info("Hello World");
	
	
//	static Logger logger = Logger.getLogger(HttpUtil.class);
	
	public static String charset = "UTF-8"; 	// 编码
	public static int size = 1024; 		// 每次读取的包长2^10
//	public static Logger log=Logger.getLogger(HttpUtil.class);
	
//    public static byte[] request(String url) throws HttpException, IOException{
//        if(StringUtils.isEmpty(url)){
//            return null;
//        }
//        String encodeURL = URIUtil.encodePathQuery(url);
//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = new GetMethod(encodeURL);
//        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//        httpClient.executeMethod(getMethod);
//        byte[] responseBody = getMethod.getResponseBody();
//        return responseBody;
//    }
    
    public static String send(String urlStr, String params) throws IOException{
    	return send(urlStr, params, 30, 30, 0, 0);
    }
    
    public static String send(String urlStr, String params, int connectTimeout, int readTimeout) throws IOException{
    	return send(urlStr, params, connectTimeout, readTimeout, 0, 0);
    }
    /**
     * 
     * @function 
     * @param urlStr 请求地址
     * @param params 多个"&"分割
     * @param connectTimeout 连接最大时长
     * @param readTimeout 响应最大时长
     * @param resendNum 重复发起请求次数（连接超时）
     * @param timeoutAdd 每次重发增加 连接最大时长 = connectTimeout + timeoutAdd
     * @return
     * @throws IOException
     */
    public static String send(String urlStr, String params, int connectTimeout, int readTimeout
    		, int resendNum, int timeoutAdd) throws IOException{
		String responseStr = null;
		StringBuffer sb = new StringBuffer();
		URL url = null;
		HttpURLConnection url_con = null;
		if(connectTimeout < 0)connectTimeout = 0;
		if(readTimeout < 0)readTimeout = 0;
		if(resendNum < 0)resendNum = 0;
		if(timeoutAdd < 0)timeoutAdd = 0;
		
		try {
			url = new URL(urlStr);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setConnectTimeout(connectTimeout);
			url_con.setRequestMethod("POST");
			url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			url_con.setDoOutput(true);
			url_con.setReadTimeout(readTimeout);

			url_con.getOutputStream().write(params.getBytes(charset));
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					charset));
			char[] cbuf = new char[size];
			int len = -1;
			while ((len = rd.read(cbuf, 0, cbuf.length)) != -1) {
				sb.append(new String(cbuf, 0, len));
			}
			responseStr = sb.toString().trim();
		} catch(SocketTimeoutException e){
//			log.error("请求失败["+e.getClass().getSimpleName()+"]：[urlStr:"+urlStr+", params:"+params+", connectTimeout:"+
//					connectTimeout+", readTimeout:"+readTimeout+", resendNum:"+resendNum+", timeoutAdd:"+timeoutAdd+"]");
			int rsn = resendNum;
			if(rsn > 0){
				int cto = connectTimeout;
				if(e instanceof SocketTimeoutException){
					cto += timeoutAdd;
				}
				responseStr = send(urlStr, params, cto, readTimeout, rsn-1, timeoutAdd);
			}else{
				throw e;
			}
		} finally {
			if (url != null){
				url_con.disconnect();
			}
		}
		return responseStr;
	}
    
    /**
	 * java.net实现 HTTP POST方法提交
	 * 
	 * @param url
	 * @param paramContent
	 * @return
	 */
	public static StringBuffer submitPost(String url, String paramContent) throws IOException {
		StringBuffer responseMessage = null;
		java.net.URLConnection connection = null;
		java.net.URL reqUrl = null;
		OutputStreamWriter reqOut = null;
		InputStream in = null;
		BufferedReader br = null;
		String param = paramContent;
//		logger.debug("url:"+url+"--param:"+param);
		responseMessage = new StringBuffer();
		reqUrl = new java.net.URL(url);
		connection = reqUrl.openConnection();
		connection.setDoOutput(true);
		connection.setConnectTimeout(120000);
		connection.setReadTimeout(120000);
		reqOut = new OutputStreamWriter(connection.getOutputStream());
		reqOut.write(param);
		reqOut.flush();
		int charCount = -1;
		in = connection.getInputStream();

		br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		while ((charCount = br.read()) != -1) {
			responseMessage.append((char) charCount);
		}
		in.close();
		reqOut.close();
//		logger.debug("res:"+responseMessage.toString());
		return responseMessage;
	}

	/**
	 * java.net实现 HTTP或HTTPs GET方法提交
	 * 
	 * @param strUrl
	 *            提交的地址及参数
	 * @return 返回的response信息
	 * @throws ConnectException 
	 */
	public static String submitGet(String strUrl){
//		logger.debug("url:"+strUrl);
		System.out.println("------------------");
		URLConnection connection = null; 
		String str = null;
		InputStream inputStream=null;
		ByteArrayOutputStream jsonHolder=null;
		try {
			URL url = new URL(strUrl);
			connection = url.openConnection();
			connection.setConnectTimeout(120000);
			connection.setReadTimeout(120000);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			// 取得输入流，并使用Reader读取
			inputStream = connection.getInputStream();
			jsonHolder = new ByteArrayOutputStream();
			byte[] b=new byte[1024];
			int len=0;
			while (( len = inputStream.read(b)) != -1) {
				jsonHolder.write(b, 0, len);
			}
			jsonHolder.flush();
			str=jsonHolder.toString("utf-8");
			if(inputStream!=null)inputStream.close();
			if(jsonHolder!=null)jsonHolder.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
//		logger.debug("res:"+str);
		return str;
	}
    
}