package com.zoo.design.templateMethod;

/**
 * 模板方法模式:父类中定义好了规则，子类实现其中某部分的业务，让父类来调子类的实现，过程完全由父类控制.
 * 好莱坞原则:Don't call me,we'll call you back,讲述了主动权归谁的为题
 * @author ZOO
 *
 */
public class TemplateMethod {

	public static void main(String[] args) {
		BankTransaction bt1=new DrawMoneyTransaction();
		
		BankTransaction bt2=new CreateAccountTransaction();
		
		bt1.process();
		bt2.process();
	}

}
