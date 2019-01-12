package com.zoo.design.memento;

/**
 * 备忘录模式：实现do/undo功能
 * @author ZOO
 *
 */
public class Memento {

	public static void main(String[] args) {
		Notepad np=new Notepad();
		Man man=new Man("张三", 15, "广州");
		System.out.println(man);
		np.save(man.backup());
		man.setAge(18);
		man.setAddress("深圳");
		System.out.println(man);
		np.save(man.backup());
		man.setAge(20);
		man.setAddress("东莞");
		System.out.println(man);

		System.out.println("成年后在东莞打拼后回到刚成年");
		man.recovery(np.back());
		System.out.println(man);
		
		System.out.println("回到未成年");
		man.recovery(np.back());
		System.out.println(man);
		
		System.out.println("我还想回去");
		System.out.println("对不起你的备份没了");
		man.recovery(np.back());
		System.out.println(man);
	}

}
