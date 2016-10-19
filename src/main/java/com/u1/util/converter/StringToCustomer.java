package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.Customer;
import com.u1.model.Department;

public class StringToCustomer implements Converter<String, Customer>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Customer convert(String text) {
		if (text.isEmpty())  
            return null;  
        else{
        	try{
        		return new Customer(Integer.valueOf(text));
        	}catch(NumberFormatException e){
        		return null;
        	}
        }
	}

}
