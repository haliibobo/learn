package com.github.haliibobo.learn.tools;

import com.alibaba.fastjson.JSONObject;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @description 公共工具定义
 */

public class CommonUtil {
	/**
	 * @description 判断是否为空,true为空，false为非空
	 * @author
	 * @time
	 * @param val
	 * @return
	 */
	public static boolean isNull(String... val) {
		if (val == null)
			return true;
		if (val.length == 0)
			return true;
		String v = val[0];
		if (v == null)
			return true;
		if (v.trim().length() == 0)
			return true;
		if (v.toUpperCase().equals("NULL"))
			return true;
		return false;
	}

	/**
	 * @description 转换时间，由string转换为timestamp 格式：yyyy-MM-dd
	 * @author
	 * @param time
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp stringToTimestamp(String time)
			throws ParseException {
		if (!isNull(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date tt = sdf.parse(time);
			Timestamp sb = new Timestamp(tt.getTime());
			return sb;
		} else {
			return null;
		}
	}

	/**
	 * @description 转换时间，由string转换为timestamp
	 * @author
	 * @param time
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp stringToTimestampByFormat(String time, String format) {
		try {
			if (!isNull(time)) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date tt = sdf.parse(time);
				Timestamp sb = new Timestamp(tt.getTime());
				return sb;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description 转换时间，String转换为Date，这里为java.util.Date 格式：yyyy-MM-dd
	 * @author
	 * @param time
	 * @return java.util.Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String time) throws ParseException {
		if (!isNull(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date tt = (Date) sdf.parse(time);
			return tt;
		} else {
			return null;
		}
	}

	/**
	 * @description 根据格式进行转换，String->java.util.date
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateByFormat(String time, String format)
			throws ParseException {
		if (!isNull(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date tt = (Date) sdf.parse(time);
			return tt;
		} else {
			return null;
		}
	}

	/**
	 * @description String转为java.sql.Date 格式：yyyy-MM-dd
	 * @author
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringToSqlDate(String time)
			throws ParseException {
		if (!isNull(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date tt = new java.sql.Date(sdf.parse(time).getTime());
			return tt;
		} else {
			return null;
		}
	}

	/**
	 * @description 根据格式进行转换，String->java.sql.date
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringToSqlDateByFormat(String time,
			String format) throws ParseException {
		if (!isNull(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.sql.Date tt = new java.sql.Date(sdf.parse(time).getTime());
			return tt;
		} else {
			return null;
		}
	}

	/**
	 * @description java.sql.Date 转换成 java.util.Date
	 * @param time
	 * @return
	 */
	public static Date sqlDateToUtilDate(java.sql.Date time) {
		return new Date(time.getTime());
	}

	/**
	 * @description java.util.Date 转换成 java.sql.Date
	 * @param time
	 * @return
	 */
	public static java.sql.Date utilDateToSqlDate(java.sql.Date time) {
		return new java.sql.Date(time.getTime());
	}

	/**
	 * @description timestamp转换为String 格式为：yyyy-MM-dd
	 * @param time
	 * @return
	 */
	public static String timeStampToString(Timestamp time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (time == null) {
			return "";
		}
		return sdf.format(time);
	}

	/**
	 * @description 比较2个timestamp的大小，如果前大于后返回-1，相当0，大于1
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int compare2Time(Timestamp t1, Timestamp t2) {
		if (t1 == null) {
			return -1;
		}
		if (t2 == null) {
			return -1;
		}
		long tt1 = t1.getTime();
		long tt2 = t2.getTime();
		if (tt1 - tt2 < 0) {
			return -1;
		} else if (tt1 - tt2 > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @description 判断2个时间，如果第一个大于第二个，返回false，否则true。传入时间为String java.util.Date
	 * @param time1
	 * @param time2
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare2Time(String time1, String time2)
			throws ParseException {
		if (CommonUtil.isNull(time1) || CommonUtil.isNull(time2)) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sb1 = sdf.parse(time1);
		Date sb2 = sdf.parse(time2);
		if (sb1.getTime() > sb2.getTime()) {
			return false;
		}
		return true;
	}

	/**
	 * @description 判断时间格式是否正确
	 * @param time
	 * @param format
	 * @return
	 */
	public static boolean validTimeForamt(String time, String format) {
		if (isNull(time)) {
			return true;
		} else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.parse(time);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(String timeStart, String timeEnd)
			throws ParseException {

		long start = stringToDate(timeStart).getTime();

		long end = stringToDate(timeEnd).getTime();

		return (end - start) / (1000 * 60 * 60 * 24);
	}


	public static String jsonTransfer(String jsonContent, String dataSourceId,boolean isDecode) {
		DataResUtil daResUtil = DataResUtil.getInstance();
		String primaryKey = "";
		String primaryKeyValue = "";
		if (isDecode) {
			jsonContent = Base64Util.decode(jsonContent);
		}
		StringBuffer newJsonContent = new StringBuffer();
		newJsonContent.append("{");
		List<Map<String, String>> list = daResUtil.getDataItemsByResourceId(dataSourceId);
		JSONObject jsonObject = new JSONObject();
		Iterator<?> iterator = jsonObject.keySet().iterator();
		String key = "";
		String value = "";
		String colNameEn = "";
		String colNameCn = "";
		String isPrimaryKey = "";
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = (String) jsonObject.get(key);
			if(value.indexOf("\r")>-1||value.indexOf("\t")>-1||value.indexOf("\n")>-1){
				value=replaceSpecal(value);
			}
			for (int i = 0; i < list.size(); i++) {
				colNameEn = list.get(i).get("colNameEn");
				colNameCn = list.get(i).get("colNameCn");
				isPrimaryKey = list.get(i).get("isPrimary");
				// String societyPublicProperty = list.get(i).get(
				// "societyPublicProperty");//
				// 控制是否显示,如果是AUTHORITY_QUERY，则不显示
				// 重新组装json
				if (key.equals(colNameEn)) {
					newJsonContent.append("\"").append(colNameCn)
							.append("\":\"").append(value)
							.append("\",");
					if ("1".equals(isPrimaryKey)) {
						primaryKey = colNameEn;
						primaryKeyValue = value;
					}
				}
			}

		}
		return newJsonContent.substring(0, newJsonContent.length() - 1) + "};"+ primaryKey + ";" + primaryKeyValue;

	}

	public static String replaceSpecal(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	public static String generate32Str(String version){
		String temp ="00000000000000000000000000000000";
		return  temp.substring(0, temp.length()-version.length())+version;
	}
	
	/** 
     * 获取WEB-INF目录下面server.xml文件的路径 
     * @return 
     */  
    public static String getWebInfPath(String fileName)  
    {  
        //file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/   
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        path=path.replace('/', '\\'); // 将/换成\  
        path=path.replace("file:", ""); //去掉file:  
        path=path.replace("classes\\", ""); //去掉class\  
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
        path+="classes/"+fileName; 
		String os = System.getProperty("os.name");
		  if (os != null && os.toLowerCase().indexOf("linux") > -1) {
			  path="/" +path;
	        }
        //System.out.println(path);  
        return path;  
    }
	
}
