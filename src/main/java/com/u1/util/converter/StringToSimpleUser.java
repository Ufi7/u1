package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Department;
import com.u1.model.SimpleUsers;

public class StringToSimpleUser implements Converter<String, SimpleUsers>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public SimpleUsers convert(String text) {
		if (text.isEmpty())  
            return null;  
        else
            return new SimpleUsers(text);  
	}

}
