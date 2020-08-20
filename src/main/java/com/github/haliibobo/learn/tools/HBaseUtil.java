package com.github.haliibobo.learn.tools;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.DependentColumnFilter;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizib0 on 2017/2/24.
 */
public class HBaseUtil {
	private static volatile HBaseUtil hbaseUtil = null;
	private Configuration config = null;
	private Admin admin = null;
	private Connection connection = null;
	private FileSystem fileSystem =null;
	private LocalFileSystem localFileSystem=null;
	private static final String DEFAULT_CF = "F1";
	private static final Log log = LogFactory.getLog(HBaseUtil.class);

	public HBaseUtil()  {
		try{
			config =HBaseConfiguration.create();
			connection = ConnectionFactory.createConnection(config);
			admin = connection.getAdmin();
			fileSystem = FileSystem.get(getHdfsUrl(),config,System.getProperties().getProperty("user.name"));
			localFileSystem= FileSystem.getLocal(config);
		}catch(Exception e){
			log.error("HBaseUtil() error:" + e,e);	
		}
		
	}

	public static HBaseUtil getInstance()  {
		if (hbaseUtil == null) {
			hbaseUtil = new HBaseUtil();
		}
		return hbaseUtil;
	}

	public void createTable(HTableDescriptor table) {
		TableName tableName = table.getTableName();

		try {
			if (admin.tableExists(tableName)) {
				log.info("createTable:" + tableName.getNameAsString() + " exists ");
			} else {
				admin.createTable(table);
			}

		} catch (IOException e) {
			log.error("createTable:" + tableName.getNameAsString() + "createTable error:" + e);
		}

	}

	public void createSchemaTables(String tName) {
		try {
			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tName));
			table.addFamily(new HColumnDescriptor(DEFAULT_CF).setCompressionType(Algorithm.GZ).setMaxVersions(HConstants.ALL_VERSIONS));
			createTable(table);
		} catch (Exception e) {
			log.error("createSchemaTables:" + tName + "createSchemaTables error:" + e);
		}
	}

	/**
	 * 插入数据
	 *
	 *@param rowKey
	 *@param map
	 */
	@SuppressWarnings("deprecation")
	public void insertData(String tName, String rowKey, Map<String, String> map) {

		try {
			Table table = connection.getTable(TableName.valueOf(tName));
			Put put = new Put(Bytes.toBytes(rowKey));

			for (Map.Entry<String, String> entry : map.entrySet()) {
				put.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
			}
			table.put(put);
		} catch (IOException e) {
			log.error("insertData() tableName:" + tName + "insertData error:" + e);
		}

	}
    /*  
     * 更新表中的某一列  
     *   
     * @tableName 表名  
     *   
     * @rowKey rowKey  
     *   
     * @familyName 列族名  
     *   
     * @columnName 列名  
     *   
     * @value 更新后的值  
     */  
    @SuppressWarnings("deprecation")
	public  void updateTable(String tName, String rowKey, String columnName, String value)   {  
    	Table table;
		try {
			table = connection.getTable(TableName.valueOf(tName));
			 Put put = new Put(Bytes.toBytes(rowKey));
		     put.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(columnName),   Bytes.toBytes(value));
		     table.put(put);
		} catch (IOException e) {
			log.error("updateTable() tableName:" + tName + "updateTable error:" + e);
		}   
    }
	/*
	 * 查询表中的某一列
	 * 
	 * @tName 表名
	 * 
	 * @rowKey rowKey
	 */
	@SuppressWarnings("deprecation")
	public String getRecordByColumn(String tName, String rowKey, String columnName,Boolean allVersion)  {
		try{
			Table table = connection.getTable(TableName.valueOf(tName));
		Get get = new Get(Bytes.toBytes(rowKey));
		if(allVersion)
			//获取所有版本
			get.setMaxVersions();
		// 获取指定列族和列修饰符对应的列
		
		get.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(columnName));
		Result result = table.get(get);

		Cell ce = result.getColumnLatestCell(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(columnName));
		return ce==null?"":Bytes.toString(ce.getValueArray());
		}catch(IOException e){
			log.error("getRecordByColumn () tableName:" + tName  +" rowKey: "+rowKey+"   getRecordByColumn error:" + e);
			return "";
		}
		
	}
     public List<String> getRecordByFilter(String tName,String columnName,CompareOp compareOp,String value){
    	 List<String> list = new ArrayList<String>();
    	 try {
			Table table = connection.getTable(TableName.valueOf(tName));
			DependentColumnFilter filter = new DependentColumnFilter( Bytes.toBytes(DEFAULT_CF),Bytes.toBytes(columnName),false,compareOp, new BinaryPrefixComparator(Bytes.toBytes(value)));
			
			Scan scan = new Scan();
	        scan.setFilter(filter);//为全表扫描器设置过滤器
	        ResultScanner scanner;
	        scanner = table.getScanner(scan);
	         for (Result result : scanner) { 
	             list.add(CellUtil.getCellKeyAsString(result.rawCells()[0]).split("/")[0]);
	         }
		} catch (IOException e) {
			log.error("tableName:" + tName  +" columnName: "+columnName+"   getRecordByFilter error:" + e);
		}
    	 return list;
     }
	/*
	 * 查询表中的某几列
	 * 
	 * @tName 表名
	 * 
	 * @rowKey rowKey
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String,String>> getRecordByColumns(String tName, String rowKey, String[] columnNames,Boolean allVersion)  {
		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
		try{
			Table table = connection.getTable(TableName.valueOf(tName));
		Get get = new Get(Bytes.toBytes(rowKey));
		if(allVersion)
			//获取所有版本
			get.setMaxVersions();
		// 获取指定列族和列修饰符对应的列
		for(int i=0;i<columnNames.length;i++){
			get.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(columnNames[i]));	
		}
		Result result = table.get(get);
	    List<Cell> list = result.listCells();
	    if(list==null){
	    	return l;	
	    }
	  
	    int version = list.size()/columnNames.length;
	    
	    for(int i=0;i<version;i++){
	    	l.add(new HashMap<String,String>());
	    }
	     for( int j=0;j<list.size();j++){
	    	 Cell kv=list.get(j);
	    	 l.get(j%version).put(Bytes.toString(kv.getQualifierArray()), Bytes.toString(kv.getValueArray()));
	     }
		return l;
		}catch(IOException e){
			log.error("tableName:" + tName  +" rowKey: "+rowKey+"   getRecordByColumn error:" + e);
			return l;
		}
		
	}
	   /** 
     * File对象上传到hdfs
     * @throws IOException 
	 * @throws InterruptedException 
     */  
    public boolean uploadFile(File file,String dstPath) {  
 
        try {  
        	
            Path localFilePath = new Path(file.getAbsolutePath());
            Path hdfsFilePath = new Path(Path.mergePaths(fileSystem.getWorkingDirectory(),new Path(dstPath)) + "/"+file.getName());
            FSDataInputStream  fsin = localFileSystem.open(localFilePath);  
            FSDataOutputStream fsout = fileSystem.create(hdfsFilePath,true,4096);
           
            IOUtils.copyBytes(fsin, fsout, 4096, false);  
            IOUtils.closeStream(fsin);  
            IOUtils.closeStream(fsout); 
           /* byte[] buf =new byte[1024];
            int readbytes=0;
            while ((readbytes=fsin.read(buf))>0){
            	fsout.write(buf,0,readbytes);
            }*/
            return true;
        } catch(IOException e) {  
        	log.error( "uploadFile error:" + e);
        	return false;
        }  
       
    }
    
    
    
    /** 
     * fileItem对象上传到hdfs
     */  
    public boolean uploadFile(FileItem fileItem,String dstPath){  
 
        try {  
        	
            Path hdfsFilePath = new Path(Path.mergePaths(fileSystem.getWorkingDirectory(),new Path(dstPath)) + "/"+fileItem.getName());
            InputStream fsin = fileItem.getInputStream();
            FSDataOutputStream fsout = fileSystem.create(hdfsFilePath,true,4096);
           
            IOUtils.copyBytes(fsin, fsout, 4096, false);  
             
            IOUtils.closeStream(fsin);
            IOUtils.closeStream(fsout);
            /*byte[] buf =new byte[1024];
            int readbytes=0;
            while ((readbytes=fsin.read(buf))>0){
            	fsout.write(buf,0,readbytes);
            }*/
            return true;
        } catch(IOException e) {  
        	log.error( "uploadFile error:" + e);
           return false;
        }  
       
    }
    

    
   
    /**
    * @Title: downLoadFile
    * @Description: 下载文件
    * @param @param dstPath
    * @param @param srcPath
    * @param @param fsout
    * @param @return    参数
    * @return boolean    返回类型
    * @throws
    */
    public  boolean downLoadFile(String dstPath,String srcPath ,OutputStream out)  {  
		try {
	        Path hdfsPath = Path.mergePaths(fileSystem.getWorkingDirectory(), new Path(srcPath));
	    	 
	    	if(!fileSystem.exists(hdfsPath)){
	    	     return false;	
	    	}else{
	    		 FSDataInputStream  fsin = fileSystem.open(hdfsPath);
	        	 // Local
	    		  OutputStream fsout = out ==null?new FileOutputStream(dstPath):out;
	    		
	    	      //将文件COPY到指定目录 
	    	     //IOUtils.copyBytes(fsin,  fsout, 4096,false);
	    	    // IOUtils.closeStream(fsin);
	    	    // IOUtils.closeStream(fsout); 
	    		  byte[] buf =new byte[1024];
	              int readbytes=0;
	              while ((readbytes=fsin.read(buf))>0){
	              	fsout.write(buf,0,readbytes);
	              }
	    	      return true;
	    	}
		} catch (IOException e) {
			log.error( "downLoadFile error:" + e);
			 return false;	
		}	  
    } 
    
    
    public boolean delFile(String path){
    	try {
    		 Path hdfsPath = Path.mergePaths(fileSystem.getWorkingDirectory(), new Path(path));
    		return fileSystem.delete(hdfsPath, true);
    	} catch (IOException e) {
    		log.error( "delFile error:" + e);
    	return false;
    	}
    	
    	}
    
    /**
    * @Title: getHdfsUrl
    * @Description: 获取hdfs 工作url
    * @param @return    参数
    * @return URI    返回类型
    * @throws
    */
    @SuppressWarnings("rawtypes")
	private URI getHdfsUrl(){
    	
    	try {
    		Map<String, Object> map = XmlUtil.xml2Map(XmlUtil.doc2Str(XmlUtil.parse(CommonUtil.getWebInfPath("core-site.xml")), "UTF-8"));
        	String url = String.valueOf(((Map )(((List) map.get("data")).get(0))).get("value"));
			return new URI(url);
		} catch (Exception e) {
			log.error( "getHdfsUrl error:" + e,e);
			return null;
		}
    }
    
    public Map<String,Object> getFileMetaData(String srcPath) {
    	Map<String,Object> m =new HashMap<String,Object>();
		try {
			Path hdfsPath = Path.mergePaths(fileSystem.getWorkingDirectory(), new Path(srcPath));
	    	FileStatus fileStatus = fileSystem.getFileStatus(hdfsPath);
	    	m.put("path", fileStatus.getPath());
	    	m.put("len", fileStatus.getLen());
	    	m.put("modificationTime", fileStatus.getModificationTime());
	    	m.put("accessTime", fileStatus.getAccessTime());
	    	m.put("owner", fileStatus.getOwner());
	    	m.put("group", fileStatus.getGroup());
	    	m.put("permission", fileStatus.getPermission());
	    	
	    	
		} catch (IOException e) {
			log.error( "getFileMetaData error:" + e);
		}
		return m;
    	 
    }
    
    public Map<String,Object> getDirMetaData(String srcPath) {
    	Map<String,Object> m =new HashMap<String,Object>();
		try {
			
			Path hdfsPath = Path.mergePaths(fileSystem.getWorkingDirectory(), new Path(srcPath));
	    	FileStatus fileStatus = fileSystem.getFileStatus(hdfsPath);
	    	m.put("path", fileStatus.getPath());
	    	m.put("len", fileStatus.getLen());
	    	m.put("modificationTime", fileStatus.getModificationTime());
	    	m.put("accessTime", fileStatus.getAccessTime());
	    	m.put("owner", fileStatus.getOwner());
	    	m.put("group", fileStatus.getGroup());
	    	m.put("permission", fileStatus.getPermission());
	    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	    	 for(FileStatus fs : fileSystem.listStatus(hdfsPath)){ 
	    		 Map<String,Object> map =new HashMap<String,Object>();
	    		 map.put("isFile", fs.isFile());
	    		 map.put("name", fs.getPath());
	    		 list.add(map);
	         }
	    	m.put("sub", list);
	    	
	    	
		} catch (IOException e) {
			log.error( "getDirMetaData error:" + e);
		}
		return m;
    	 
    }
    

}