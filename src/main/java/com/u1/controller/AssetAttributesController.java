package com.u1.controller;

import com.u1.model.AssetAttributesTemplate;
import com.u1.model.AssetAttributesTemplateWithOption;
import com.u1.model.AssetType;
import com.u1.model.AttrSelectOption;
import com.u1.model.Condition;
import com.u1.model.Department;
import com.u1.model.TaskType;

import com.u1.service.StatisticsService;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;


@Controller
public class AssetAttributesController extends BaseController {
    @RequestMapping("/assetattributes")
    public String init(ModelMap modelmap) {
        List<AssetType> atlist = this.commonService.listValid(AssetType.class);
        modelmap.put("assetTypeList", atlist);

        return "assetattributes";
    }

    @RequestMapping("/assetattributeslist/{assetTypePid}/{page}")
    public String list(@PathVariable
    Integer assetTypePid, @PathVariable
    int page, ModelMap modelmap) {
        Condition c = new Condition("assetTypePid", assetTypePid);
        List<Condition> cs = new ArrayList();
        cs.add(c);

        return super.listpage(modelmap, page, AssetAttributesTemplate.class,
            cs, "assetattributeslist");
    }

    @RequestMapping("/assetattributesdetail/{id}")
    public String detailEnquiry(@PathVariable
    Integer id, ModelMap modelmap) {
        return super.detailEnquiry(modelmap,
            AssetAttributesTemplateWithOption.class, id,
            "assetattributesDetail", "assetattributesdetail");
    }

    @RequestMapping(value = "/assetattributesdetail/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String update(@PathVariable
    Integer id, @Valid
    AssetAttributesTemplateWithOption aatwo, BindingResult result,
        HttpServletRequest request) {
        if ("SELECT".equals(aatwo.getAttributeType()) ||
                "MULTI_SELECT".equals(aatwo.getAttributeType())) {
            Set<ConstraintViolation<AssetAttributesTemplateWithOption>> constraintViolations =
                validator.validate(aatwo,
                    com.u1.model.AssetAttributesTemplateWithOption.Select.class);

            if (constraintViolations.size() != 0) {
                return super.buildErrorMessage(constraintViolations);
            }

            //get hidden select which indicate the existing/new option
            String[] hiddenOptionList = request.getParameterValues(
                    "hiddenOptionList");

            if (aatwo.getSelectOptionList() != null) {
                for (int i = 0; i < aatwo.getSelectOptionList().size(); i++) {
                    aatwo.getSelectOptionList().get(i).setTemplate(aatwo);

                    if ((hiddenOptionList != null) &&
                            (i < hiddenOptionList.length)) {
                        aatwo.getSelectOptionList().get(i)
                             .setAttrSelectOptionPid(Integer.valueOf(
                                hiddenOptionList[i]));
                    }
                }
            }
        } else {
            aatwo.setSelectOptionList(null); //ignore option list if not select or multi_select
        }

        //clear other unnecessary properties
        aatwo.setAssetAttrTemplatePid(null);
        aatwo.setAssetTypePid(null);
        aatwo.setLength(null);
        aatwo.setPrimaryType(null);
        aatwo.setEnabled(null);

        return super.updateWithoutMessage(aatwo, id, 1, result);
    }

    @RequestMapping(value = "/assetattributesstatusupdate/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String statusUpdate(@PathVariable
    Integer id, @RequestParam("cs")
    boolean currentStatus) {
        return super.updateStatusWithoutMessage(AssetAttributesTemplate.class,
            id, currentStatus);
    }

    //	@RequestMapping(value="/assetattributesdelete/{id}", method = RequestMethod.POST)
    //	public @ResponseBody String delete(@PathVariable Integer id){
    //		AssetAttributesTemplateWithOption aatwo = new AssetAttributesTemplateWithOption();
    //		aatwo.setAssetAttrTemplatePid(id);
    //		return super.deleteWithoutMessage(aatwo);
    //	}

    @RequestMapping("/addassetattributes")
    public String addNewPage(ModelMap modelmap) {
        List<AssetType> atlist = this.commonService.listValid(AssetType.class);
        modelmap.put("assetTypeList", atlist);
        modelmap.put("assetattributes", new AssetAttributesTemplateWithOption());

        return "addassetattributes";
    }

    @RequestMapping(value = "/addassetattributes", method = RequestMethod.POST)
    public @ResponseBody
    String addNew(@Valid
    AssetAttributesTemplateWithOption aatwo, BindingResult result) {
        if ("SELECT".equals(aatwo.getAttributeType()) ||
                "MULTI_SELECT".equals(aatwo.getAttributeType())) {
            Set<ConstraintViolation<AssetAttributesTemplateWithOption>> constraintViolations =
                validator.validate(aatwo,
                    com.u1.model.AssetAttributesTemplateWithOption.Select.class);

            if (constraintViolations.size() != 0) {
                return super.buildErrorMessage(constraintViolations);
            }

            if (aatwo.getSelectOptionList() != null) {
                for (int i = 0; i < aatwo.getSelectOptionList().size(); i++) {
                    aatwo.getSelectOptionList().get(i).setTemplate(aatwo);
                }
            }
        } else {
            aatwo.setSelectOptionList(null); //ignore option list if not select or multi_select
        }

        //clear other unnecessary properties
        aatwo.setEnabled(true);
        aatwo.setAssetAttrTemplatePid(null);
        aatwo.setLength(null);
        aatwo.setPrimaryType(null);

        return super.addNewAndReturnNewId(aatwo, result);
    }
}
