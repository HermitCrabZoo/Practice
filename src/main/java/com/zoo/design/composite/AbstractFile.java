package com.zoo.design.composite;

import java.util.ArrayList;
import java.util.List;

public interface AbstractFile {
	void killVirus();
}

class Folder implements AbstractFile{

	private String name;
	private List<AbstractFile> files=new ArrayList<>();
	
	public Folder(String name) {
		super();
		this.name = name;
	}

	public boolean add(AbstractFile file) {
		return files.add(file);
	}
	
	public boolean remove(AbstractFile file) {
		return files.remove(file);
	}
	
	public AbstractFile getChild(int index) {
		return files.get(index);
	}

	@Override
	public void killVirus() {
		System.out.println("查杀文件夹:\""+name+"\"");
		for (AbstractFile abstractFile : files) {
			abstractFile.killVirus();
		}
	}

}

class TextFile implements AbstractFile{
	private String name;
	
	public TextFile(String name) {
		super();
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("查杀文本文件:\""+name+"\"");
	}
}

class ImageFile implements AbstractFile{
	private String name;
	
	public ImageFile(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("查杀图片文件:\""+name+"\"");
	}
}

class VideoFile implements AbstractFile{
	private String name;
	
	public VideoFile(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("查杀视频文件:\""+name+"\"");
	}
}