package com.u1.util.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.u1.util.Util;

public class StringToDate implements Converter<String, Date>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(String text) {
		Date returnDate = null;
		if(!text.isEmpty()){
        	try{
            	returnDate = Util.df.parse(text);
            }catch(Exception e){
            	returnDate = null;
            }
        }
		return returnDate; 
	}

}

