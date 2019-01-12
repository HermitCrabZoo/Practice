package com.zoo.design.adapter;

/**
 * 带有USB接口的小米笔记本
 * @author ZOO
 *
 */
public class MiAir {
	
	
	public void joinKeyboard(USB usb) {
		usb.connect();
	}
	
	
	public static void main(String[] args) {
		MiAir air12_5=new MiAir();//12.5寸的小米air笔记本
		Keyboard kb=new Keyboard();//老式键盘
		USB usb=new USBToPS2Adapter1_0();//1.0版本的适配器
		usb=new USBToPS2Adapter2_0(kb);//2.0版本的适配器
		air12_5.joinKeyboard(usb);//笔记本通过usb适配器连接ps/2键盘
	}
}
