package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.TaskType;

public class StringToTaskType implements Converter<String, TaskType>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public TaskType convert(String text) {
		if (text.isEmpty())  
            return null;  
        else
            return new TaskType(text);  
	}

}
