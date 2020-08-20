/**
 * 
 */
package com.github.haliibobo.learn.tools;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;





/**
 * @author advance
 *
 */
public class XmlUtil {
	private static final Log log = LogFactory.getLog(XmlUtil.class);
	
	/**
	 * 解析file xml文档
	 *
	 * @return Document
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	public static Document parse(String xmlPath) throws FileNotFoundException, DocumentException  {
		SAXReader reader = new SAXReader();
		FileInputStream file = new FileInputStream(xmlPath);
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
	public static  Element getRootElement(Document doc){
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
	@SuppressWarnings("unchecked")
	public static  Map<String,Object> listAttribute(Element node) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Attribute> list = node.attributes();
		for (Attribute attribute : list) {
			map.put(attribute.getName(), attribute.getValue());
		}
		return map;

	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object>  listSubNode(Element node){
		Map<String,Object>  map = new HashMap<String,Object> ();
		 //当前节点下面的所有一级子节点    
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();
            map.put(e.getName(), e.getText());
        }  
		return map;
	}
	public static List<Element> listSubElement(Element node){
		 List<Element> list = new ArrayList<Element>();
		 //当前节点下面的所有一级子节点    
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();
            list.add(e);
        }  
		return list;
	}
	/**
	 * 保存文档
	 * 
	 * @param doc
	 * @param xmlPath
	 * @param encoding
	 * @throws IOException 
	 * @throws Exception
	 */
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
	public static void modifyDocument(File inputXml, String nodes, String attributename, String value, String outXml) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			List list = document.selectNodes(nodes);
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Attribute attribute = (Attribute) iter.next();
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

		catch (DocumentException e) {
			log.error(e);
		} catch (IOException e) {
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
	public static String doc2Str(Document doc, String encoding) throws IOException {
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
	private static  Document str2Doc(String text) throws DocumentException {
		return DocumentHelper.parseText(text);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  Map<String,Object>  xml2Map(String str) {
		Document doc= null;
		Map data = new HashMap();
		List<Map<String,Object> > list = new ArrayList<Map<String,Object> >();
		try {
			 doc = str2Doc(str);
				Element root =getRootElement(doc);
				 data =listAttribute(root);
				List<Element> l = listSubElement(root);
				for(int i=0;i<l.size();i++){
					Map<String,Object>  map = listSubNode(l.get(i));
					list.add(map);
				}
		} catch (DocumentException e) {
			
			
			if(log.isErrorEnabled()){
				log.error("xml2Map出错:"+e);
				log.error("数据str:" + str);
			}
		}
		

		data.put("data", list);
        return data;
	}

	 public static String map2Xml(Map<String,Object> map,String encoding) throws IOException {  
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
		public static String list2Xml(List list,String encoding) throws IOException {  
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
	    
	    @SuppressWarnings("rawtypes")
		public static List xml2List(String xml) {  
	        try {  
	            List<Map> list = new ArrayList<Map>();  
	            Document document = DocumentHelper.parseText(xml);  
	            Element nodesElement = document.getRootElement();  
	            List nodes = nodesElement.elements();  
	            for (Iterator its = nodes.iterator(); its.hasNext();) {  
	                Element nodeElement = (Element) its.next();  
	                Map map = xml2Map(nodeElement.asXML());  
	                list.add(map);   
	            }  
	            return list;  
	        } catch (Exception e) { 
	        	log.error("xml2List error"+e);
	        	return new ArrayList<Map>();
	        }  
	          
	    }

}

