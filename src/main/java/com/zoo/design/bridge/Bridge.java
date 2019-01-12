package com.zoo.design.bridge;

/**
 * 桥接模式:存在两个维度以上(平台与品牌)的相互组合,使用类之间的组合代替类的继承,减少复杂度,增加灵活性
 * @author ZOO
 *
 */
public class Bridge {

	public static void main(String[] args) {
		
		Computer macPro=new Desktop(new Apple());
		Computer miAir=new Laptop(new XiaoMi());
		macPro.boot();
		miAir.boot();
		
	}

}
