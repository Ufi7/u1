package com.u1.util.converter;

import org.springframework.core.convert.converter.Converter;

import com.u1.model.AssetType;
import com.u1.model.AssetTypeWithTemplate;

public class StringToAssetTypeWithTemplate implements Converter<String, AssetTypeWithTemplate>   {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public AssetTypeWithTemplate convert(String text) {
		if (text.isEmpty())
			 return null;
        else
            return new AssetTypeWithTemplate(text);  
	}

}
