package com.u1.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import com.u1.exception.U1Exception;
import com.u1.model.Asset;
import com.u1.model.AssetAttribute;
import com.u1.model.AssetAttributesTemplateWithOption;
import com.u1.model.AssetTypeWithTemplate;
import com.u1.model.AttrSelectOption;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.CustomerInterface;
import com.u1.model.Groups;
import com.u1.model.ModelConstants;
import com.u1.model.Resources;
import com.u1.model.Roles;
import com.u1.model.SearchResult;
import com.u1.model.SelectOptionModel;
import com.u1.model.UserForAuthOnly;

public class Util {
	
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	public static String getSimpleDateFormat(Date date){
		return df.format(date);
	}
	
	public static String MD5(String password){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);
        String pwd = md5.encodePassword(password, null);
        return pwd;
	}
	
	//put search result to session key by key
	public static void buildSearchResultList(ModelMap model, SearchResult sr){
		model.put("count", sr.getTotalCount());
		model.put("result", sr.getResult());
		model.put("currentPage", sr.getCurrentPage());
		model.put("totalPage", sr.getTotalPage());
	}

	public static void build3SelTeamplate(ModelMap model, List<Resources> bigSel,List<Resources> smallSel, String selectPropertyName){
		List<SelectOptionModel> sel1 = new ArrayList();
		if(bigSel!=null){
			for(Resources r1:bigSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r1.getResourceId().toString());
				option.setValue(r1.getDescription());
				sel1.add(option);
			}
		}
		List<SelectOptionModel> sel2 = new ArrayList();
		if(smallSel!=null){
			for(Resources r2:smallSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r2.getResourceId().toString());
				option.setValue(r2.getDescription());
				sel2.add(option);
			}
		}
		model.put("sel_full_list", sel1);
		model.put("sel_selected_list", sel2);
		model.put("select_property_name", selectPropertyName);
	}
	
	public static void build3SelTeamplateForRoles(ModelMap model, List<Roles> bigSel,List<Roles> smallSel, String selectPropertyName){
		List<SelectOptionModel> sel1 = new ArrayList();
		if(bigSel!=null){
			for(Roles r1:bigSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r1.getRoleId().toString());
				option.setValue(r1.getRoleName());
				sel1.add(option);
			}
		}
		List<SelectOptionModel> sel2 = new ArrayList();
		if(smallSel!=null){
			for(Roles r2:smallSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r2.getRoleId().toString());
				option.setValue(r2.getRoleName());
				sel2.add(option);
			}
		}
		model.put("sel_full_list", sel1);
		model.put("sel_selected_list", sel2);
		model.put("select_property_name", selectPropertyName);
	}
	
	public static void build3SelTeamplateForGroups(ModelMap model, List<Groups> bigSel,List<Groups> smallSel, String selectPropertyName){
		List<SelectOptionModel> sel1 = new ArrayList();
		if(bigSel!=null){
			for(Groups r1:bigSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r1.getGroupId().toString());
				option.setValue(r1.getGroupName());
				sel1.add(option);
			}
		}
		List<SelectOptionModel> sel2 = new ArrayList();
		if(smallSel!=null){
			for(Groups r2:smallSel){
				SelectOptionModel option = new SelectOptionModel();
				option.setKey(r2.getGroupId().toString());
				option.setValue(r2.getGroupName());
				sel2.add(option);
			}
		}
		model.put("sel_full_list", sel1);
		model.put("sel_selected_list", sel2);
		model.put("select_property_name", selectPropertyName);
	}
	
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   }   
	
	public static String buildAssetAttributes(AssetTypeWithTemplate atwt, Asset updateModel, HttpServletRequest request, List<AssetAttribute> existingAttributeList){
		StringBuilder errorsb = new StringBuilder();
		List<AssetAttribute> newAttrList = new ArrayList();
		for(int i=0;i<atwt.getTemplateList().size();i++){
			AssetAttributesTemplateWithOption aatwo = atwt.getTemplateList().get(i);
			String[] value = request.getParameterValues(Constants.FIELD_NAME_PREFIX+aatwo.getAssetAttrTemplatePid());
			if(!aatwo.getEnabled() || value==null || (value.length==1 && value[0].trim().equals(""))){//no input case
				//no attribute to add
				if(aatwo.getEnabled() && aatwo.getRequired()){//mandtory field hanlding
					errorsb.append(MessageSourceHelper.getMessage(Constants.FIELD_VALIDATE_NOT_ALLOW_EMPTY, new String[]{aatwo.getAttributeName()}));
				}
			}else{//check input
				AssetAttribute newat = new AssetAttribute();
				newat.setAsset(updateModel);
				newat.setAttributeTemplatePid(aatwo.getAssetAttrTemplatePid());
				boolean tobeadd = true;
				if(Constants.ATTR_TYPE_TXT.equalsIgnoreCase(aatwo.getAttributeType())){
					if(!value[0].matches(ModelConstants.REG_CHN_ALLOW_EMPTY_100)){
						errorsb.append(MessageSourceHelper.getMessage(Constants.FIELD_VALIDATE_INVALID, new String[]{aatwo.getAttributeName()}));
					}
					newat.setStringValue(value[0]);
				}else if(Constants.ATTR_TYPE_SEL.equalsIgnoreCase(aatwo.getAttributeType())){
					AttrSelectOption option = new AttrSelectOption();
					option.setAttrSelectOptionPid(Integer.parseInt(value[0]));
					List<AttrSelectOption> sol = new ArrayList();
					sol.add(option);
					newat.setSelectedOptionList(sol);
					boolean optionValid = false;
					for(AttrSelectOption availableOption : aatwo.getSelectOptionList()){
						if(value[0].equals(availableOption.getAttrSelectOptionPid().toString())){
							optionValid = true;
							break;
						}
					}
					if(!optionValid){
						errorsb.append(MessageSourceHelper.getMessage(Constants.FIELD_VALIDATE_INVALID, new String[]{aatwo.getAttributeName()}));
					}
				}else if(Constants.ATTR_TYPE_MUL.equalsIgnoreCase(aatwo.getAttributeType())){
					List<AttrSelectOption> mol = new ArrayList();
					boolean erroradded = false;
					for(String o:value){
						AttrSelectOption moption = new AttrSelectOption();
						moption.setAttrSelectOptionPid(Integer.parseInt(o));
						mol.add(moption);
						if(!erroradded){
							boolean moptionValid = false;
							for(AttrSelectOption availablemOption : aatwo.getSelectOptionList()){
								if(o.equals(availablemOption.getAttrSelectOptionPid().toString())){
									moptionValid = true;
									break;
								}
							}
							if(!moptionValid){
								errorsb.append(MessageSourceHelper.getMessage(Constants.FIELD_VALIDATE_INVALID, new String[]{aatwo.getAttributeName()}));
								erroradded = true;
							}
						}
					}
					newat.setSelectedOptionList(mol);
				}else if(Constants.ATTR_TYPE_BOL.equalsIgnoreCase(aatwo.getAttributeType())){
					if(value[0].equals("true")){
						newat.setBooleanValue(true);
					}else{
						newat.setBooleanValue(false);
					}
				}else if(Constants.ATTR_TYPE_CAL.equalsIgnoreCase(aatwo.getAttributeType())){
					Date inputDate;
					try{
						inputDate = Util.df.parse(value[0]);
						newat.setDateValue(inputDate);
		            }catch(Exception e){
		            	errorsb.append(MessageSourceHelper.getMessage(Constants.FIELD_VALIDATE_INVALID, new String[]{aatwo.getAttributeName()}));
		            }
				}
				if(existingAttributeList!=null){//find the existing db record id
					for(AssetAttribute existingOne : existingAttributeList){
						if(existingOne.getAttributeTemplatePid().equals(aatwo.getAssetAttrTemplatePid())){
							newat.setAssetAttrbutePid(existingOne.getAssetAttrbutePid());
							break;
						}
					}
				}
				newAttrList.add(newat);
			}
		}
		updateModel.setAttributeList(newAttrList);
		return errorsb.toString();
	}
	
	public static Object[][] returnAttributesStringArray(Asset a){
		Object[][] arrayValue=new Object[2][a.getAssetType().getTemplateList().size()];
		for(int i=0;i<a.getAssetType().getTemplateList().size();i++){
			AssetAttributesTemplateWithOption aatwo = a.getAssetType().getTemplateList().get(i);
			String value="";
			String id="";
			boolean[] multiSelectValue=null;
			for(int n=0;n<a.getAttributeList().size();n++){
				AssetAttribute at = a.getAttributeList().get(n);
				if(Constants.ATTR_TYPE_MUL.equalsIgnoreCase(aatwo.getAttributeType())){
					multiSelectValue = new boolean[aatwo.getSelectOptionList().size()];
				}
				if(at.getAttributeTemplatePid().equals(aatwo.getAssetAttrTemplatePid())){
					id=at.getAssetAttrbutePid().toString();
					if(Constants.ATTR_TYPE_TXT.equalsIgnoreCase(aatwo.getAttributeType())){
						value=at.getStringValue();
					}else if(Constants.ATTR_TYPE_CAL.equalsIgnoreCase(aatwo.getAttributeType())){
						if(at.getDateValue()!=null){
							value=Util.getSimpleDateFormat(at.getDateValue());
						}
					}else if(Constants.ATTR_TYPE_SEL.equalsIgnoreCase(aatwo.getAttributeType())){
						value=at.getSelectedOptionList().get(0).getAttrSelectOptionPid().toString();
					}else if(Constants.ATTR_TYPE_MUL.equalsIgnoreCase(aatwo.getAttributeType())){
						for(int m=0;m<aatwo.getSelectOptionList().size();m++){
							for(int l=0;l<at.getSelectedOptionList().size();l++){
								if(at.getSelectedOptionList().get(l).getAttrSelectOptionPid().equals(aatwo.getSelectOptionList().get(m).getAttrSelectOptionPid())){
									multiSelectValue[m] = true;
									continue;
								}
							}
						}
					}else if(Constants.ATTR_TYPE_BOL.equalsIgnoreCase(aatwo.getAttributeType())){
						if(at.getBooleanValue()==null || at.getBooleanValue()==false){
							value="false";
						}else{
							value="true";
						}
					}
					break;
				}
			}
			arrayValue[0][i]=id;
			if(multiSelectValue!=null){
				arrayValue[1][i] = multiSelectValue;
			}else{
				arrayValue[1][i] = value;
			}
		}
		return arrayValue;
	}
	
	public static void checkCrossCustomerProcess(Authentication authentication, Customer inputCustomer){
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		checkCrossCustomerProcess(user, inputCustomer);
	}
	
	public static void checkCrossCustomerProcess(UserForAuthOnly user, Customer inputCustomer){
		if(!user.getCustomer().equals(inputCustomer) && !user.getAccessRights().contains(new Integer(8))){
			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
		}
	}
	
//	public static Integer getCustomerIdInput(UserForAuthOnly user, HttpServletRequest request, boolean allowNullReturn){
//		if (user.getAccessRights().contains(new Integer(8))){
//			Integer customerId = Integer.valueOf(request.getParameter("customer"));
//			if(customerId==null && !allowNullReturn){
//				return user.getCustomer().getCustomerId();
//			}
//			return customerId;
//		}
//		else{
//			return user.getCustomer().getCustomerId();
//		}
//	}
	
	public static void handleCustomerInfo(CustomerInterface parent, UserForAuthOnly user, boolean allowEmpty){
		boolean right8 = user.getAccessRights().contains(new Integer(8));
		if(parent.getCustomer()==null && (!allowEmpty || !right8)){
			parent.setCustomer(user.getCustomer());
		}else if(!user.getCustomer().equals(parent.getCustomer())){	//cross company
			if(!right8){	//without 8 right
				throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));	//handle error case
			}
		}
	}
	
	public static Integer handleCustomerInfo(UserForAuthOnly user,HttpServletRequest request, boolean allowEmpty){
		boolean right8 = user.getAccessRights().contains(new Integer(8));
		if(right8){
			String customer=(String)request.getParameter("customer");
			if(customer!=null && !customer.isEmpty()){
				try{
					return Integer.valueOf(customer);
				}catch(Exception e){
					//nothing to do
				}
			}else if(customer!=null && customer.isEmpty() && allowEmpty){
				return null;
			}
		}
		return user.getCustomer().getCustomerId();
	}
	
	public static List<Condition> initEnabledCondition(){
		List<Condition> conditions=new ArrayList();
		Condition c = new Condition("enabled", true);
		conditions.add(c);
		return conditions;
	}
	
	public static List<Object[]> build12MonthData(List<Object[]> data, boolean insertto12, boolean includeCurrentMonth){
//		if(data.size()==0)
//			return data;
//		else{
			Object[] pre, cur;
			List<Object[]> returnlist = new ArrayList();
			Calendar c = Calendar.getInstance();
			int comparem = c.get(Calendar.MONTH) + (includeCurrentMonth?1:0);
			Integer y1=c.get(Calendar.YEAR), m1=comparem;
			if(data.size()==0){
				returnlist.add(new Object[]{null, y1.toString()+"."+m1.toString()});
			}else{
				pre = data.get(0);
				y1=(Integer)pre[1];
				m1=(Integer)pre[2];
				returnlist.add(new Object[]{pre[0], y1.toString()+"."+m1.toString()});
			}
			int prey, prem, cury = y1, curm = m1;
			for(int i=1;i<data.size();i++){
				pre = data.get(i-1);
				cur = data.get(i);
				prey = ((Integer)pre[1]).intValue();
				prem = ((Integer)pre[2]).intValue();
				cury = ((Integer)cur[1]).intValue();
				curm = ((Integer)cur[2]).intValue();
				int offset = 1;
				while(!((prey+((prem+offset)>12?1:0)==cury)&&(((prem+offset==12)?12:(prem+offset)%12)==curm))){
					
					returnlist.add(new Object[]{null, ""+(prey+((prem+offset)>12?1:0))+"."+((prem+offset==12)?12:(prem+offset)%12)});
					offset++;
				}
				returnlist.add(new Object[]{cur[0], cur[1].toString()+"."+cur[2].toString()});
			}
			while(insertto12 && curm != comparem){
				curm++;
				if(curm==13){
					curm=1;
					cury++;
				}
				returnlist.add(new Object[]{null, cury+"."+curm});
			}
			while(insertto12 && returnlist.size()<12){
				m1--;
				if(m1.equals(0)){
					y1--;
					m1=12;
				}
				returnlist.add(0, new Object[]{null, y1.toString()+"."+m1.toString()});
			}
			return returnlist;
//		}
	}
}
