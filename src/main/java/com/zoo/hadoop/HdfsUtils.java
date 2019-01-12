package com.zoo.hadoop;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class HdfsUtils {
	
	public static FileSystem getFileSystem() {
		FileSystem hdfs=null;
		
		try {
			Configuration conf=new Configuration();
			conf.set("fs.hdfs.impl",DistributedFileSystem.class.getName());
			hdfs=FileSystem.get(URI.create("hdfs://hadoop1:8020/"),conf,"hadoop");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hdfs;
	}
}
