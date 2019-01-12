package com.zoo.design.templateMethod;

public class CreateAccountTransaction extends BankTransaction {

	@Override
	public void transact() {
		System.out.println("工商银行开户");
	}

}
