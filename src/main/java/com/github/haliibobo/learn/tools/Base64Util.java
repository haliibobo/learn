package com.github.haliibobo.learn.tools;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Base64Util {

	private static final Log log = LogFactory.getLog(Base64Util.class);

	public static String encode(String str) {
		if (str == null || "".equals(str)) {
			return str;
		}
		String result = "";
		try {
			result = new String(Base64.encodeBase64(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			log.error("Base64编码错误: " + str, e);
		}
		return result;
	}

	public static String decode(String str) {
		if (str == null || "".equals(str)) {
			return str;
		}
		String result = "";
		try {
			result = new String(Base64.decodeBase64(str.getBytes("utf-8")),"utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Base64解码错误: " + str, e);
		}
		return result;
	}
}
