/**
 * 
 */
package com.github.haliibobo.learn.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;





/**
 * @author advance
 *
 */
public class XmlUtil {
	private static final Log log = LogFactory.getLog(XmlUtil.class);

	private static volatile XmlUtil xmlUtil;

	private XmlUtil(){

	}
	public static XmlUtil getInstance(){
		if (xmlUtil == null) {
			synchronized (XmlUtil.class){
				if (xmlUtil == null) {
					xmlUtil = new XmlUtil();
				}
			}
		}
		return xmlUtil;
	}
	
	/**
	 * 解析file xml文档
	 *
	 * @return Document
	 * @throws DocumentException
	 */
	public Document parse(String xmlPath) throws DocumentException  {
		SAXReader reader = new SAXReader();
		InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
		if (file == null) {
			throw new NullPointerException();
		}
		BufferedInputStream buffer = new BufferedInputStream(file);
		return  reader.read(buffer);
	}
    /**
    * @Title: getRootElement
    * @Description: 获取根元素节点
    * @param @param doc
    * @param @return    参数
    * @return Element    返回类型
    * @throws
    */
	public   Element getRootElement(Document doc){
    	return doc.getRootElement();
    	
    }
	/**
	* @Title: listAttribute
	* @Description: 遍历解析节点的属性，输出map
	* @param @param element
	* @param @return    参数
	* @return Map    返回类型
	* @throws
	*/
	public   Map<String,String> listAttribute(Element node) {
		return node.attributes()
				.stream().collect(Collectors
						.toMap(Attribute::getName,Attribute::getValue));

	}
	
	public  Map<String,String>  listSubNode(Element node){
		return this.listSubElement(node)
				.stream().collect(Collectors
						.toMap(Element::getName,Element::getText));
	}
	public  List<Element> listSubElement(Element node){
		return node.elements();
	}

	public void save(Document doc, String xmlPath, String encoding) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath), encoding), format);
		writer.write(doc);
		writer.flush();
		writer.close();
	}

	/**
	 * 修改xml某节点的值
	 * 
	 * @param inputXml
	 *            原xml文件
	 * @param nodes
	 *            要修改的节点
	 * @param attributename
	 *            属性名称
	 * @param value
	 *            新值
	 * @param outXml
	 *            输出文件路径及文件名 如果输出文件为null，则默认为原xml文件
	 */
	@SuppressWarnings("rawtypes")
	public  void modifyDocument(File inputXml, String nodes, String attributename, String value, String outXml) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			List<Node> list = document.selectNodes(nodes);
			for (Node o : list) {
				Attribute attribute = (Attribute) o;
				if (attribute.getName().equals(attributename))
					attribute.setValue(value);
			}
			XMLWriter output;
			if (outXml != null) {// 指定输出文件
				output = new XMLWriter(new FileWriter(new File(outXml)));
			} else { // 输出文件为原文件
				output = new XMLWriter(new FileWriter(inputXml));
			}
			output.write(document);
			output.close();
		}

		catch (DocumentException | IOException e) {
			log.error(e);
		}
	}

	/**
	 * xml转换为字符串
	 * 
	 * @param doc
	 * @param encoding
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public  String doc2Str(Document doc, String encoding) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(byteOS, encoding), format);
		writer.write(doc);
		writer.flush();
		writer.close();

		return byteOS.toString(encoding);
	}

	/**
	 * 字符串转换为Document
	 * @param text
	 * @return
	 * @throws DocumentException
	 */
	private  Document str2Doc(String text) throws DocumentException {
		return DocumentHelper.parseText(text);
	}

	@SuppressWarnings({ "rawtypes"})
	public   Map<String,String>  xml2Map(String str) {
		Map<String,String> data = new HashMap<>();
		try {
			Element root =getRootElement(str2Doc(str));
			data = new HashMap<>(listAttribute(root));
			//List<Map<String,String>> list =listSubElement(root).stream().map(this::listSubNode).collect(Collectors.toList());
			//if(!list.isEmpty())
			//data.put("data", list);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        return data;
	}

	 public  String map2Xml(Map<String,Object> map,String encoding) throws IOException {
	        Document document = DocumentHelper.createDocument();  
	        Element nodeElement = document.addElement("node");  
	        for ( Entry<String, Object> entry : map.entrySet()) {
	            Element keyElement = nodeElement.addElement("key");  
	            keyElement.addAttribute("label", entry.getKey());  
	            keyElement.setText(String.valueOf(entry.getValue()));  
	        }  
	        return doc2Str(document,encoding);  
	    }  
	
	    @SuppressWarnings("rawtypes")
		public  String list2Xml(List list,String encoding) throws IOException {
	        Document document = DocumentHelper.createDocument();  
	        Element nodesElement = document.addElement("nodes");  
	        int i = 0;  
	        for (Object o : list) {  
	            Element nodeElement = nodesElement.addElement("node");  
	            if (o instanceof Map) {  
	                for (Object obj : ((Map) o).keySet()) {  
	                    Element keyElement = nodeElement.addElement("key");  
	                    keyElement.addAttribute("label", String.valueOf(obj));  
	                    keyElement.setText(String.valueOf(((Map) o).get(obj)));  
	                }  
	            } else {  
	                Element keyElement = nodeElement.addElement("key");  
	                keyElement.addAttribute("label", String.valueOf(i));  
	                keyElement.setText(String.valueOf(o));  
	            }  
	            i++;  
	        }  
	        return doc2Str(document, encoding);  
	    }

	public Element getRoot (String xml) throws DocumentException {
		return DocumentHelper.parseText(xml)
				.getRootElement();
	}

	public  List<Map<String,String>> xml2List(Element root) {
		try {
			return root.elements().stream()
					.map(Element::asXML)
					.map(this::xml2Map)
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("xml2List error"+e);
			return new ArrayList<>();
		}
	}
	    
	    @SuppressWarnings("rawtypes")
		public  List<Map<String,String>> xml2List(String xml) {
	        try {
				return getRoot(xml)
						.elements().stream()
						.map(Element::asXML)
						.map(this::xml2Map)
						.collect(Collectors.toList());
	        } catch (Exception e) { 
	        	log.error("xml2List error"+e);
	        	return new ArrayList<>();
	        }
	    }

	public  <T> T mapToBean(Map<String, String> map, Class<T> clazz) throws Exception {
		T obj = clazz.newInstance();
		if(map != null && map.size() > 0) {
			for(Map.Entry<String, String> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				String setMethodName = "set"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				assert field != null;
				Class fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
			}
		}
		return obj;
	}

	private  Object convertValType(Object value, Class fieldTypeClass) {
		Object retVal = null;
		if(Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if(Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if(Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if(Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	private  Field getClassField(Class clazz, String fieldName) {
		if( Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field []declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class superClass = clazz.getSuperclass();
		if(superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}
}

