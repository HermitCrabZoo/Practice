package com.zoo.design.composite;

/**
 * 组合模式:处理树形结构时的天然递归功能。
 * @author ZOO
 *
 */
public class Composite {

	public static void main(String[] args) {
		AbstractFile image=new ImageFile("斜眼笑.png");
		AbstractFile text=new TextFile("银河系漫游指南.txt");
		AbstractFile video1=new TextFile("阿凡达.mkv");
		AbstractFile video2=new TextFile("少年派的奇幻漂流.mkv");
		AbstractFile kingOfRing1=new TextFile("指环王1-护戒使者.mkv");
		AbstractFile kingOfRing2=new TextFile("指环王2-双塔奇兵.mkv");
		AbstractFile kingOfRing3=new TextFile("指环王3-王者归来.mkv");
		
		Folder sonFolder=new Folder("视频");
		sonFolder.add(video1);
		sonFolder.add(video2);
		sonFolder.add(kingOfRing1);
		sonFolder.add(kingOfRing2);
		sonFolder.add(kingOfRing3);
		Folder folder=new Folder("C盘");
		folder.add(image);
		folder.add(text);
		folder.add(sonFolder);
		
		//查杀
		folder.killVirus();
		
	}

}
