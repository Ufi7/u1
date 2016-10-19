package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToInterger implements Converter<String, Integer>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Integer convert(String text) {
		//	System.out.println("..............in converter...............");
		// TODO Auto-generated method stub
		if (text.isEmpty())  
            return null;  
        else{
        	Integer val = null;
        	try{
        		val = Integer.parseInt(text);
        	}catch(NumberFormatException e){
        		//nothing to do
        	}
            return val;
        }
	}

}
