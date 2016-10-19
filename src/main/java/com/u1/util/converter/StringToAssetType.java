package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;

public class StringToAssetType implements Converter<String, AssetType>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public AssetType convert(String text) {
		if (text.isEmpty())
			 return null;
        else
            return new AssetType(text);  
	}

}
