package com.zoo.design.templateMethod;

public class DrawMoneyTransaction extends BankTransaction {

	@Override
	public void transact() {
		System.out.println("取现20万");
	}

}
