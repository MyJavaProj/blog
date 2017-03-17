package com.blog.common;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
//import com.fxrh.ykt.sfang.util.SfangConfig;

//公用方法
public class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * 使用自定义注解
	 * 
	 * /
	@MethodNote(createTime="2017-3-15")
	public static String formatDate(Date date,String formatPattern){
		return new SimpleDateFormat(formatPattern).format(date);
	}
	
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));
            if(value != null && !value.trim().equals("")){
            	if(sb.length() > 0)sb.append("&");
                sb.append(key + "=" + value.trim());
            }
        }

        return sb.toString();
    }
    
	public static String getWebText(String urlAddr, String charset)
			throws Exception {
		StringBuffer retVal = new StringBuffer();
		BufferedReader l_reader = null;
		try {
			String sCurrentLine = "";
			URL url = new URL(urlAddr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"openwave (compatible; MSIE 7.0;)");
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(180 * 1000);
			conn.setUseCaches(false);
			l_reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), charset));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				if (!"".equals(sCurrentLine.trim()))
					retVal.append(sCurrentLine.trim() + "\n");
			}
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(urlAddr);
			throw e;
		}
		return retVal.toString();
	}

	public static String friendlyDate(Date inDate) {
		if (inDate == null)
			return "";
		Date curDate = new Date();
		long mDiff = (curDate.getTime() - inDate.getTime()) / 60000;
		if (mDiff < 1)
			return "刚才";
		else if (mDiff < 60)
			return mDiff + "分钟前";
		else {
			if (CommonUtil.getDateTimeStr("yyyyMMdd", curDate).equals(
					CommonUtil.getDateTimeStr("yyyyMMdd", inDate)))
				return "今天 " + CommonUtil.getDateTimeStr(" HH:mm", inDate);
			else if (CommonUtil.getDateTimeStr("yyyy", curDate).equals(
					CommonUtil.getDateTimeStr("yyyy", inDate)))
				return CommonUtil.getDateTimeStr("MM-dd HH:mm", inDate);
			else
				return CommonUtil.getDateTimeStr("yyyy-MM-dd HH:mm", inDate);
		}
	}



	/**
	 * 获取http请求的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		if (request == null)
			return null;
		String remoteIp = request.getHeader("x-forwarded-for");
		if (remoteIp == null) {
			remoteIp = request.getRemoteAddr();
		} else {
			int tIndex = remoteIp.indexOf(",");
			if (tIndex > 0)
				remoteIp = remoteIp.substring(0, tIndex).trim();
		}
		return remoteIp;
	}

	/**
	 * 给定时间与当前时间相差秒数
	 * 
	 * @param inDate
	 * @return
	 */
	public static long diffSecond(Date inDate) {
		if (inDate == null)
			return 0;
		long diff = (new Date()).getTime() - inDate.getTime();
		long diffSec = diff / 1000;
		return diffSec;
	}

	/**
	 * 给定时间1与给定时间2相差秒数
	 * 
	 * @param inDate
	 * @return
	 */
	public static long diffSecond(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		long diff = date2.getTime() - date1.getTime();
		long diffSec = diff / 1000;
		return diffSec;
	}

	/**
	 * 返回给定位数的随机字符串
	 * 
	 * @param number
	 * @return
	 */
	public static String randomString(int number) {
		if (number < 1)
			return null;
		String sample = "123456789abcdefhjkmnpqstuvwxy123456789ABCDEFHJKMNPQSTUVWXY123456789";
		Random random = new Random();
		char str[] = sample.toCharArray();
		char ch[] = new char[number];
		for (int i = 0; i < number; i++)
			ch[i] = str[random.nextInt(sample.length())];
		return new String(ch);
	}

	public static String randomNumberString(int number) {
		if (number < 1)
			return null;
		String sample = "1234567890";
		Random random = new Random();
		char str[] = sample.toCharArray();
		char ch[] = new char[number];
		for (int i = 0; i < number; i++)
			ch[i] = str[random.nextInt(sample.length())];
		return new String(ch);
	}

	/**
	 * 非正则替换字符串
	 * 
	 * @param line
	 *            原字符串
	 * @param oldString
	 *            要替换的字符串
	 * @param newString
	 *            新字符串
	 * @return
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 非正则替换字符串（忽略大小写）
	 * 
	 * @param line
	 *            原字符串
	 * @param oldString
	 *            要替换的字符串
	 * @param newString
	 *            新字符串
	 * @return
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 转换xml标记
	 * 
	 * @param input
	 *            待处理字符串
	 * @return 还原后的字符串
	 */
	public static final String transXmlTags(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		String retStr = replace(input, "&", "&amp;");
		retStr = replace(retStr, "<", "&lt;");
		retStr = replace(retStr, ">", "&gt;");
		return retStr;
	}

	/**
	 * 还原html标记
	 * 
	 * @param input
	 *            待处理字符串
	 * @return 还原后的字符串
	 */
	public static final String revertHtmlTags(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		String retStr = replace(input, "&amp;", "&");
		retStr = replace(retStr, "&lt;", "<");
		retStr = replace(retStr, "&gt;", ">");
		retStr = replace(retStr, "&nbsp;", " ");
		return retStr;
	}

	/**
	 * 取日期
	 * 
	 * @param dayOffset
	 *            与当前日期的偏移量，以天为单位
	 * @return yyyy-MM-dd格式字符串
	 */
	public static String getDateStr(long dayOffset) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date tDate = new Date();
		long tTime = tDate.getTime() + 24 * 3600 * 1000L * dayOffset;
		tDate.setTime(tTime);
		String dateString = formatter.format(tDate);
		return dateString;
	}

	/**
	 * 取日期
	 * 
	 * @param dayOffset
	 *            与当前日期的偏移量，以秒为单位
	 * @return
	 */
	public static Date getDate(long secondsOffset) {
		Date tDate = new Date();
		long tTime = tDate.getTime() + 1000L * secondsOffset;
		tDate.setTime(tTime);
		return tDate;
	}

	/**
	 * 按给定格式获取当前时间字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateTimeStr(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(new Date());
		return dateString;
	}

	/**
	 * 按给定格式给定时间字符串
	 * 
	 * @param format
	 * @param inDate
	 * @return
	 */
	public static String getDateTimeStr(String format, Date inDate) {
		if (inDate == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(inDate);
		return dateString;
	}

	public static String getDateTimeStr(String format, long secondsOffset) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date tDate = new Date();
		long tTime = tDate.getTime() + 1000L * secondsOffset;
		tDate.setTime(tTime);
		return formatter.format(tDate);
	}

	public static String getDateTimeStr(String format, String oldTime,
			long secondsOffset) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date tDate = formatter.parse(oldTime);
			long tTime = tDate.getTime() + 1000L * secondsOffset;
			tDate.setTime(tTime);
			return formatter.format(tDate);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 从字符串转成日期
	 * 
	 * @param format
	 * @param dataStr
	 * @return
	 */
	public static Date getDateTime(String format, String dataStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(dataStr);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static String bytes2UpperHex(byte[] source) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < source.length; i++) {
			int digByte = source[i] & 0xFF;
			if (digByte <= 0x0F)
				sb.append("0");
			sb.append(Integer.toHexString(digByte));
		}
		return sb.toString().toUpperCase();
	}

	public static String bytes2LowerHex(byte[] source) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < source.length; i++) {
			int digByte = source[i] & 0xFF;
			if (digByte <= 0x0F)
				sb.append("0");
			sb.append(Integer.toHexString(digByte));
		}
		return sb.toString().toLowerCase();
	}

	public static byte[] hex2Bytes(String source) {
		if (source == null || source.equals("")) {
			return null;
		}
		String hexString = source.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] dest = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			dest[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return dest;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * MD5摘要
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Digest(String src) {
		return md5Digest(src, "utf-8");
	}

	/**
	 * MD5摘要
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Digest(String src, String charSet) {
		if (src == null)
			return "";
		String rst = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(src.getBytes(charSet));
			byte[] dig = md.digest();
			rst = bytes2LowerHex(dig);
		} catch (Exception e) {
			rst = src;
		}
		return rst;
	}

	/**
	 * MD5摘要
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Digest(byte[] src) {
		if (src == null)
			return "";
		String rst = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(src);
			byte[] dig = md.digest();
			rst = bytes2LowerHex(dig);
		} catch (NoSuchAlgorithmException e) {
			rst = "";
		}
		return rst;
	}

	public static String md5DigestUrl(String urlAddr) {
		String rst = "";
		HttpURLConnection conn = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"openwave (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322;openwave; .NET CLR 2.0.50727)");
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(180 * 1000);
			conn.setUseCaches(false);
			InputStream tIn = conn.getInputStream();
			if (tIn != null) {
				byte[] tbb = new byte[8192];
				while (true) {
					int readNum = tIn.read(tbb);
					if (readNum > 0)
						md.update(tbb, 0, readNum);
					else
						break;
				}
				tIn.close();
			}
			byte[] dig = md.digest();
			rst = bytes2LowerHex(dig);
		} catch (Exception e) {
			e.printStackTrace();
			rst = urlAddr;
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return rst;
	}

	/**
	 * 写字符串到文本文件
	 * 
	 * @param fileName
	 *            文件名，全路径
	 * @param fileContent
	 *            文件内容
	 * @param charset
	 *            字符集
	 * @throws Exception
	 */
	public static void writeTextFile(String fileName, String fileContent,
			String charset) throws Exception {
		// 自动创建目录
		String filePath = fileName.substring(0, fileName.lastIndexOf("/"));
		File tPath = new File(filePath);
		if (!tPath.exists()) {
			tPath.mkdirs();
		}

		File tFile = new File(fileName);
		if (tFile.exists()) {
			tFile.delete();
		}
		RandomAccessFile pFile = new RandomAccessFile(fileName, "rw");
		pFile.write(fileContent.getBytes(charset));
		pFile.close();
	}

	/**
	 * 写字节到文件
	 * 
	 * @param fileName
	 *            文件名，全路径
	 * @param fileContent
	 *            文件内容
	 * @throws Exception
	 */
	public static void writeFile(String fileName, byte[] fileContent)
			throws Exception {
		// 自动创建目录
		String filePath = fileName.substring(0, fileName.lastIndexOf("/"));
		File tPath = new File(filePath);
		if (!tPath.exists()) {
			tPath.mkdirs();
		}

		File tFile = new File(fileName);
		if (tFile.exists()) {
			tFile.delete();
		}
		RandomAccessFile pFile = new RandomAccessFile(fileName, "rw");
		pFile.write(fileContent);
		pFile.close();
	}

	/**
	 * 读文本文件到字符串
	 * 
	 * @param fileName
	 *            文件名，全路径
	 * @param charset
	 *            字符集
	 * @return
	 * @throws Exception
	 */
	public static String readTextFile(String fileName, String charset)
			throws Exception {
		BufferedInputStream pFile = new BufferedInputStream(
				new FileInputStream(fileName));
		byte[] buffer = new byte[pFile.available()];
		pFile.read(buffer);
		String stFile = new String(buffer, charset);
		pFile.close();
		return stFile;
	}

	/**
	 * 把通过url把web文件下载到本地
	 * 
	 * @param urlAddr
	 *            url
	 * @param fileName
	 *            本地文件名
	 * @throws Exception
	 */
	public static void saveWebFile(String urlAddr, String fileName)
			throws Exception {
		RandomAccessFile pFile = null;
		URL url = new URL(urlAddr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty(
				"User-Agent",
				"openwave (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322;openwave; .NET CLR 2.0.50727)");
		conn.setConnectTimeout(30 * 1000);
		conn.setReadTimeout(180 * 1000);
		conn.setUseCaches(false);
		String localDir = fileName.substring(0, fileName.lastIndexOf("/"));
		File fPath = new File(localDir);
		fPath.mkdirs();
		File tFile = new File(fileName);
		if (tFile.exists()) {
			tFile.delete();
		}
		pFile = new RandomAccessFile(fileName, "rw");
		InputStream tIn = conn.getInputStream();
		if (tIn != null) {
			byte[] tbb = new byte[8192];
			while (true) {
				int readNum = tIn.read(tbb);
				if (readNum > 0)
					pFile.write(tbb, 0, readNum);
				else
					break;
			}
			tIn.close();
		}
		pFile.close();
		conn.disconnect();
	}

	/**
	 * 用正则表达式提取内容
	 * 
	 * @param html
	 * @param regStr
	 *            正则表达式
	 * @return
	 */
	public static String getFirstMatchString(String html, String regStr) {
		if (html == null)
			return null;
		Pattern p = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(html);// 开始编译
		if (m.find())
			return m.group(1);
		else
			return null;
	}

	/**
	 * 用正则表达式提取内容
	 * 
	 * @param html
	 * @param regStr
	 *            正则表达式
	 * @return
	 */
	public static List<String> getMatchStrings(String html, String regStr) {
		List<String> retList = new ArrayList<String>();
		Pattern p = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(html);// 开始编译
		while (m.find())
			retList.add(m.group(1));
		return retList;
	}

	/**
	 * 判断一个或多个对象,只要其中一个为空就返回true 否则返回false<br/>
	 * <h4>注意</h4> String长度为0：<strong>return true</strong> <br/>
	 * Integer<1 ：<strong>return true</strong>
	 * 
	 * @param o
	 * @return 空:true，否则 ：false.
	 */
	public static boolean isEmpty(Object... o) {
		if (null == o)
			return true;
		for (Object obj : o) {
			if (null == obj)
				return true;
			if (obj instanceof String
					&& String.valueOf(obj).trim().length() == 0)
				return true;
			if (obj instanceof Integer
					&& Integer.valueOf(String.valueOf(obj)) < 1)
				return true;
		}
		return false;
	}

	/**
	 * 判断一个或多个对象,全部元素为空就返回true 否则返回false
	 * 
	 * @param o
	 * @return 空:true，否则 ：false.
	 */
	public static boolean isAllEmpty(Object... o) {
		boolean b = true;
		for (Object obj : o)
			b = b && isEmpty(obj);
		return b;
	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 转换为Integer类型
	 * 
	 * @param o
	 * @return
	 */
	public static Integer toInt(Object o) {
		try {
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 读取文件 一次一行
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> readFileByLines(String fileName) {
		List<String> listStr = new ArrayList<String>();

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				listStr.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return listStr;
	}

	/**
	 * 图片大小转换
	 * 
	 * @param filePath
	 *            图片路径
	 * @param iNewWidth
	 * @param iNewHeight
	 * @return
	 * @throws Exception
	 */
	public static long getResizeImage(String filePath, int iNewWidth,
			int iNewHeight) throws Exception {
		File tempFile = new File(filePath);
		Image oldImage = javax.imageio.ImageIO.read(tempFile);// 原图片
		Image newImage = javax.imageio.ImageIO.read(tempFile);// 新图片
		int width = oldImage.getWidth(null); // 得到原图宽
		int height = oldImage.getHeight(null); // 得到原图长
		int newWidth = iNewWidth;
		int newHeight = iNewHeight;
		if (newWidth >= width && newHeight >= height) {
			newWidth = width;
			newHeight = height;
		}
		if (width * newHeight > height * newWidth)
			newHeight = (height * newWidth) / width;
		else if (width * newHeight < height * newWidth)
			newWidth = (width * newHeight) / height;

		BufferedImage tag = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();// 获取图形上下文
		g.setColor(Color.WHITE); // 设定背景色
		g.fillRect(0, 0, newWidth, newHeight);
		g.drawImage(newImage.getScaledInstance(newWidth, newHeight,
				BufferedImage.SCALE_SMOOTH), 0, 0, null);// 绘制缩小后的图
		ImageIO.write(tag, "JPEG", tempFile);// 写文件
		return tempFile.length();
	}

	/**
	 * 格式化带小数数字串
	 * 
	 * @param source
	 *            原字符串
	 * @param scale
	 *            小数位数
	 * @return 格式化后的字符串
	 */
	public static String formatDecimalString(String source, int scale) {
		String retStr = source;
		int tIndex = retStr.indexOf(".");
		if (scale == 0) {
			if (tIndex >= 0)
				retStr = retStr.substring(0, tIndex);
			if ("".equals(retStr))
				retStr = "0";
		} else {
			if (tIndex == 0)
				retStr = "0" + retStr;
			else if (tIndex < 0)
				retStr += ".";
			tIndex = retStr.indexOf(".");
			int oldScale = retStr.length() - tIndex - 1;
			for (int i = 0; i < (scale - oldScale); i++)
				retStr += "0";
			retStr = retStr.substring(0, tIndex + scale + 1);
		}
		return retStr;
	}

	/**
	 * 格式化带小数数字串
	 * 
	 * @param fSource
	 *            原浮点数
	 * @param scale
	 *            小数位数
	 * @return 格式化后的字符串
	 */
	public static String formatDecimalString(float fSource, int scale) {
		String retStr = String.valueOf(fSource);
		// 处理四设五入
		int iIndex = retStr.indexOf(".");
		if (iIndex >= 0 && iIndex + scale + 1 < retStr.length()) {
			iIndex = iIndex + scale + 1;
			if (retStr.substring(iIndex, iIndex + 1).compareTo("4") > 0) {
				String fixValue = "0.";
				for (int i = 0; i < scale; i++)
					fixValue += "0";
				fixValue += "5";
				try {
					retStr = String.valueOf(fSource
							+ Float.parseFloat(fixValue));
				} catch (Exception ec) {
					ec.printStackTrace();
				}
			}
		}
		return formatDecimalString(retStr, scale);
	}

	/**
	 * 格式化带小数数字串
	 * 
	 * @param dSource
	 *            原双精度数
	 * @param scale
	 *            小数位数
	 * @return 格式化后的字符串
	 */
	public static String formatDecimalString(double dSource, int scale) {
		String retStr = String.valueOf(dSource);
		// 处理四设五入
		int iIndex = retStr.indexOf(".");
		if (iIndex >= 0 && iIndex + scale + 1 < retStr.length()) {
			iIndex = iIndex + scale + 1;
			if (retStr.substring(iIndex, iIndex + 1).compareTo("4") > 0) {
				String fixValue = "0.";
				for (int i = 0; i < scale; i++)
					fixValue += "0";
				fixValue += "5";
				try {
					retStr = String.valueOf(dSource
							+ Float.parseFloat(fixValue));
				} catch (Exception ec) {
					ec.printStackTrace();
				}
			}
		}
		return formatDecimalString(retStr, scale);
	}
	
	public static String getUrlByArea(String url,String area,String type){
//		String northcityList = SfangConfig.northCityList;
//		String southcityList = SfangConfig.southCityList;
//		String shcityList = SfangConfig.shCityList;
		String rtnStr = "";
		
		rtnStr = url + "/" + type;
//		if(northcityList.contains(area)){
//			rtnStr = CommonUtil.getExchangedPushUrl(url,"http://" + SfangConfig.northCityUrl+ "/" + type);
//		}else if(southcityList.contains(area)){
//			rtnStr = CommonUtil.getExchangedPushUrl(url,"http://" + SfangConfig.southCityUrl + "/" + type);
//		}else if(shcityList.contains(area)){
//			rtnStr = CommonUtil.getExchangedPushUrl(url,"http://" + SfangConfig.shCityUrl+ "/" + type);
//		}
		return rtnStr;
	}
	
	/**
	 * 正则替换 推送地址
	 * 
	 * @param o_url
	 *            原推送地址
	 * @param partUrl
	 *            替换为的字符串
	 * @return 替换后后的推送地址
	 */
	public static String getExchangedPushUrl(String o_url,String partUrl){
		String regStr = "[a-zA-z]+://[^/]*";
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(o_url);
		String rtn = m.replaceFirst(partUrl); 
		return rtn;
	}



	public static void main(String[] args) throws Exception {
		//System.out.println(CommonUtil.threeDesDecode("Ay/yQDnMk0X/a2ynDgdpyg=="));
		//System.out.println(CommonUtil.md5Digest("测试模板2015-03-032015-03-03测试短信内容测试彩信标题111111".getBytes("utf-8")));
	}

}