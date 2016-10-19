package com.u1.util;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceHelper {
	public static final Locale DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;
	
	public static String getMessage(String code){
		String msg = messageSource.getMessage(code, null, DEFAULT_LOCALE);
        return msg != null ? msg.trim() : msg;
	}
	
	public static String getMessage(String code, Object[] args) {
        String msg = messageSource.getMessage(code, args, DEFAULT_LOCALE);
        return msg != null ? msg.trim() : msg;
    }

	private static ReloadableResourceBundleMessageSource messageSource;
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource){
		this.messageSource = messageSource;
	}
	
}

