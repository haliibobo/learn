package com.github.haliibobo.learn.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil
{
  private Properties props = new Properties();
  private static Map<String, PropertiesUtil> propMap = null;
  public void load(InputStream input) throws IOException {
    this.props.load(input);
    TransCode();
  }

  private void TransCode()
  {
    Iterator<Object> iter = this.props.keySet().iterator();
    while (iter.hasNext()) {
      String key = (String)iter.next();
      String newValue = null;
      try
      {
        String propertiesFileEncode = "utf-8";
        newValue = new String(this.props.getProperty(key).getBytes(StandardCharsets.ISO_8859_1), propertiesFileEncode);
      } catch (UnsupportedEncodingException e) {
        newValue = this.props.getProperty(key);
      }
      this.props.setProperty(key, newValue);
    }
  }

  public Properties getProps() {
    return this.props;
  }

  public String getProperty(String key) {
    return this.props.getProperty(key);
  }

  public void setProperty(String key, String value) {
    this.props.setProperty(key, value);
  }

  public Set<Object> getKeys() {
    return this.props.keySet();
  }

  public static Properties getInstance(String filename)
  {
    if (propMap == null) {
      propMap = new HashMap<String, PropertiesUtil>();
    }
    if (propMap.get(filename) == null) {
      PropertiesUtil propsUtil = new PropertiesUtil();
      InputStream is = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(filename);
      try {
        propsUtil.load(is);
      } catch (IOException e) {
        e.printStackTrace();
        try
        {
          is.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
      finally
      {
        try
        {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      propMap.put(filename, propsUtil);
    }
    return ((PropertiesUtil)propMap.get(filename)).getProps();
  }

  public static String getValue(String filename, String key)
  {
    return getInstance(filename).getProperty(key);
  }

  public static String getValue(String filename, String key, String defaultValue)
  {
    String str = getInstance(filename).getProperty(key);
    if (str == null) {
      return defaultValue;
    }
    return str;
  }
}