package com.zoo.design.facade;

/**
 * 提供的门面
 * @author ZOO
 *
 */
public class RegisterFacade {
	public void register() {
		ICBureau icb=new ICBureau();
		icb.checkName();
		
		QSBureau qsb=new QSBureau();
		qsb.orgCodeCertificate();
		
		TaxBureau tb=new TaxBureau();
		tb.taxCertificate();
		
		Bank bank=new Bank();
		bank.openAccount();
	}
}
