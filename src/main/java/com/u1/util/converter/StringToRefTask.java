package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Department;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;

public class StringToRefTask implements Converter<String, RefTask>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public RefTask convert(String text) {
		if (text.isEmpty())  
            return null;  
        else{
            try{
            	return new RefTask(Integer.valueOf(text));
            }catch(Exception e){
            	return null;
            }
        }
	}

}
