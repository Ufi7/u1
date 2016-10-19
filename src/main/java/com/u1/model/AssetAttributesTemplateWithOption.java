package com.u1.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class AssetAttributesTemplateWithOption extends AssetAttributesTemplate0 {
	@Valid
	@Size(min=1, groups=Select.class, message="{Size.sssetattributes.selectoptionlist}")
	@NotNull(groups=Select.class, message="{Size.sssetattributes.selectoptionlist}")
	private List<AttrSelectOption> selectOptionList;

	/**
	 * @return the selectOptionList
	 */
	public List<AttrSelectOption> getSelectOptionList() {
		return selectOptionList;
	}

	/**
	 * @param selectOptionList the selectOptionList to set
	 */
	public void setSelectOptionList(List<AttrSelectOption> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}
	
	public interface Select extends Default{}
}
