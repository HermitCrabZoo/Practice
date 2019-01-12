package com.zoo.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class HdfsUrlTest {
	
	/**
	 * 让jdk识别hdfs开头的url
	 */
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) throws IOException {
		testRead();
	}
	
	/**
	 * 读取hdfs文件内容
	 * @throws IOException
	 */
	public static void testRead() throws IOException {
		System.out.println("以URL的方式从hdfs读文件");
		//hdfs上的文件路径
		URL url=new URL("hdfs://hadoop1:8020/input/upload.txt");
		InputStream is=url.openStream();
		//输出到控制台
		IOUtils.copyBytes(is, System.out, 4096);
		IOUtils.closeStream(is);
	}
	
}
