package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Department;

public class StringToDepartment implements Converter<String, Department>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Department convert(String text) {
		if (text.isEmpty())  
            return null;  
        else
            return new Department(text);  
	}

}
