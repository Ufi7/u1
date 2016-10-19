package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Component;
import com.u1.model.Customer;
import com.u1.model.TaskType;

public class StringToComponent implements Converter<String, Component>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Component convert(String text) {
		if (text.isEmpty())  
            return null;  
        else
            try{
        		return new Component(Integer.valueOf(text));
        	}catch(NumberFormatException e){
        		return null;
        	}
	}

}
