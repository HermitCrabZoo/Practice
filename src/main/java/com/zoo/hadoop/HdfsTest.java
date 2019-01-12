package com.zoo.hadoop;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

public class HdfsTest {

	public static void main(String[] args) throws IOException {
//		testRead();
//		testList();
//		testUpload();
//		testDownload();
//		testDelete();
//		testMkdir();
//		testCreateFile();
//		testRename();
//		testLocation();
//		testCluster();
//		productData();
	}
	
	/**
	 * 上传文件
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void testUpload() throws IllegalArgumentException, IOException {
		System.out.println("上传文件到hdfs");
		FileSystem fs =HdfsUtils.getFileSystem();
        fs.copyFromLocalFile(new Path("e:\\upload.txt"),new Path("/input/upload.txt"));
//        fs.copyFromLocalFile(new Path("f:\\Download\\MuchMoreFonts.7z"),new Path("/input/MuchMoreFonts.7z"));
        System.out.println("上传成功！");
	}
	
	/**
	 * 读取hdfs文件内容
	 * @throws IOException
	 */
	public static void testRead() throws IOException {
		System.out.println("从hdfs读文件");
		FileSystem hdfs=HdfsUtils.getFileSystem();
		
		Path wordFile=new Path("/input/upload.txt");
		
		FSDataInputStream inStream=hdfs.open(wordFile);
		
		IOUtils.copyBytes(inStream, System.out, 4096, false);
		
		IOUtils.closeStream(inStream);
	}
	
	
	/**
	 * 列出目录
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void testList() throws FileNotFoundException, IOException {
		System.out.println("从hdfs读目录");
		FileSystem hdfs=HdfsUtils.getFileSystem();
		
		Path path=new Path("/input/");

		FileStatus[] status=hdfs.listStatus(path);
		
		for (FileStatus fs:status) {
			Path p=fs.getPath();
			System.out.println(p+(fs.isDirectory()?" is dir":" is file"));
		}
	}
	
	/**
	 * 下载文件
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void testDownload() throws IllegalArgumentException, IOException {
		System.out.println("从hdfs下载文件");
		FileSystem fs =HdfsUtils.getFileSystem();
        fs.copyToLocalFile(false,new Path("/input/upload.txt"),new Path("e:\\upload1.txt"),true);
        System.out.println("下载成功！");
	}
	
	/**
	 * 删除文件
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void testDelete() throws IllegalArgumentException, IOException {
		System.out.println("从hdfs删除文件");
		FileSystem fs =HdfsUtils.getFileSystem();
		fs.delete(new Path("/input/upload.txt"),true);
		System.out.println("删除成功！");
	}
	
	/**
	 * 创建文件夹
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void testMkdir() throws IllegalArgumentException, IOException {
		System.out.println("在hdfs上创建文件夹");
		FileSystem fs =HdfsUtils.getFileSystem();
		fs.mkdirs(new Path("/test1"));
		System.out.println("创建成功！");
	}
	
	
	/**
	 * 创建文件
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void testCreateFile() throws IllegalArgumentException, IOException {
		System.out.println("在hdfs上创建文件");
		FileSystem fs = HdfsUtils.getFileSystem();
		String content = "Hello hadoop,this is first time that I create file on hdfs\n";
		FSDataOutputStream fsout = fs.create(new Path("/input/createdFile.go"));
		BufferedOutputStream bout = new BufferedOutputStream(fsout);
		bout.write(content.getBytes(), 0, content.getBytes().length);
		bout.close();
		fsout.close();
		System.out.println("创建成功！");
	}
	
	/**
	 * 改名
	 * @throws IOException
	 */
	public static void testRename() throws IOException {
		System.out.println("在hdfs上改名");
		FileSystem fs =HdfsUtils.getFileSystem();
		Path s=new Path("/input/createdFile.go");
		Path d=new Path("/input/createdFileRename.go");
		boolean success=fs.rename(s, d);
		System.out.println("改名"+(success?"成功！":"失败！"));
	}
	
	
	/**
	 * 获取文件在hdfs上的位置
	 * @throws IOException
	 */
	public static void testLocation() throws IOException {
		System.out.println("获取hdfs上文件的位置");
		FileSystem fs =HdfsUtils.getFileSystem();
		Path p=new Path("/input/MuchMoreFonts.7z");
		FileStatus status=fs.getFileStatus(p);
		BlockLocation[] bls=fs.getFileBlockLocations(status, 0, status.getLen());//每个文件可能存在多个块
		for (BlockLocation bl : bls) {
			System.out.println("块 offset:"+bl.getOffset()+" length:"+bl.getLength());
			String[] hosts=bl.getHosts();//每个块可能分布在不同的节点上
			for (String host : hosts) {
				System.out.println("host:"+host);
			}
		}
	}
	
	/**
	 * 获取节点信息
	 * @throws IOException
	 */
	public static void testCluster() throws IOException {
		System.out.println("获取hdfs集群节点信息");
		FileSystem fs =HdfsUtils.getFileSystem();
		DistributedFileSystem dfs=(DistributedFileSystem) fs;
		DatanodeInfo[] dnis=dfs.getDataNodeStats();
		for (DatanodeInfo dni : dnis) {
			String hostName=dni.getHostName();
			System.out.println("host:"+hostName+" ip:"+dni.getIpAddr());
		}
	}
	
	
	public static void productData() {
//		p=new Path("e:\\traffic_2.txt");
		Random rand=new Random();
		String[] phones= {"13800138000","10086","12580","12345","95588","95566","110","119"};
		try (FileChannel outChannel = FileChannel.open(Paths.get("e:\\traffic_2.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);){
			ByteBuffer buf = ByteBuffer.allocate(2048);
			int lines=100;
			for(int i=0;i<lines;i++) {
				StringBuilder sb=new StringBuilder(phones[rand.nextInt(8)]);
				sb.append("\t").append(rand.nextInt(200)+1)
				.append("\t").append(rand.nextInt(2000)+1)
				.append("\t").append(rand.nextInt(200)+1)
				.append("\t").append(rand.nextInt(1000)+1)
				.append("\n");
				buf.put(sb.toString().getBytes());
				buf.flip(); //切换读取数据的模式
				//④将缓冲区中的数据写入通道中
				outChannel.write(buf);
				buf.clear(); //清空缓冲区
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
