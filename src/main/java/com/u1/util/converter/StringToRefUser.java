package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Department;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;

public class StringToRefUser implements Converter<String, RefUser>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public RefUser convert(String text) {
		if (text.isEmpty())  
            return null;  
        else{
            try{
            	return new RefUser(Integer.valueOf(text));
            }catch(Exception e){
            	return null;
            }
        }
	}

}
