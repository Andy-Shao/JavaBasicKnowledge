package com.github.andyshao.java7.demo.basic;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public abstract class LocalizedException extends Exception{

    private static final long serialVersionUID = -6517704092864425309L;
	private static final String DEFAULT_BASE_NAME = "java7/message";
    private String baseName = DEFAULT_BASE_NAME;
    protected ResourceBundle resourceBundle;
    private String messageKey;
    
    public LocalizedException(String messageKey) {
    	this.messageKey = messageKey;
    	initResourceBundle();
    }
    
    public LocalizedException(String messagekey, String baseName) {
    	this.messageKey = messagekey;
    	this.baseName = baseName;
    }
    
    private void initResourceBundle(){
    	this.resourceBundle = ResourceBundle.getBundle(this.baseName);
    }

	public void setBaseName(String baseName) {
    	this.baseName = baseName;
    }
	
	public abstract String getLocalizedMessage();
	
	public String getMessage(){
		return getLocalizedMessage();
	}
	
	protected String format(Object... args){
		String message = this.resourceBundle.getString(this.messageKey);
		return MessageFormat.format(message, args);
	}
}
