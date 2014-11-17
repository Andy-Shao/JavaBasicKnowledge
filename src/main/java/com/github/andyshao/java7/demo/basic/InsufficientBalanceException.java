package com.github.andyshao.java7.demo.basic;

import java.math.BigDecimal;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class InsufficientBalanceException extends LocalizedException{

    private static final long serialVersionUID = 5282747443163513027L;
    private BigDecimal requested;
    private BigDecimal balance;
    private BigDecimal shortage;
    
    public InsufficientBalanceException(BigDecimal requested, BigDecimal balance) {
    	super("INSUFFICIENT_BALANCE_EXCEPTION");
    	this.requested = requested;
    	this.balance = balance;
    	this.shortage = this.requested.subtract(balance);
    }

	@Override
    public String getLocalizedMessage() {
	    return this.format(this.balance, this.requested, this.shortage);
    }

}
