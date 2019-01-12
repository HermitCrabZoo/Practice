package com.zoo.jvm;

import java.nio.file.Paths;

import com.zoo.mix.Filer;
import com.zoo.mix.Pather;
import com.zoo.system.Platform;

public class MyClassLoader extends ClassLoader {
	
	private String name="MyClassLoader";
	private String path="D:";
	private String fileType=".class";
	
	/**
	 * 父加载器为：系统加载器
	 * @param name
	 */
	public MyClassLoader(String name) {
		super();
		this.name=name;
	}
	
	/**
	 * 指定父加载器
	 * @param parent 若为null则代表父加载器为根类加载器：Bootstrap
	 * @param name
	 */
	public MyClassLoader(ClassLoader parent,String name) {
		super(parent);
		this.name=name;
	}
	
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data=loadClassData(name);
		
		//将类的字节码数据生成Class实例，并放到jvm堆里
		return defineClass(name, data, 0, data.length);
	}
	
	/**
	 * 从磁盘读取字节码文件为byte数组
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		String filename=Pather.join(path,name.replace(".", Platform.slash()))+fileType;
		byte[] data=Filer.readBytes(Paths.get(filename));
		return  data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
