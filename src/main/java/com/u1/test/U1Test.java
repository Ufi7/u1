package com.u1.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.hibernate.validator.ap.checks.ConstraintValidatorCheck;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.ModelMap;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


import com.sun.org.apache.bcel.internal.generic.Select;
//import com.sun.xml.internal.ws.api.message.Attachment;
import com.u1.util.Constants;
import com.u1.util.DataConvertionUtil;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;
import com.u1.controller.AssetController;
import com.u1.controller.BaseController;
import com.u1.dao.CommonDao;
import com.u1.dao.hibernateimpl.SqlDaoImpl;
import com.u1.model.Asset;
import com.u1.model.AssetAttribute;
import com.u1.model.AssetAttributesTemplate0;
import com.u1.model.AssetAttributesTemplateWithOption;
import com.u1.model.AssetGroup;
import com.u1.model.AssetGroupWithSimpleAsset;
import com.u1.model.AssetType;
import com.u1.model.AssetTypeWithTemplate;
import com.u1.model.AttrSelectOption;
import com.u1.model.Component;
import com.u1.model.Componentrecord;
import com.u1.model.Componentstore;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.DataConvertion;
import com.u1.model.Department;
import com.u1.model.GroupWithRole;
import com.u1.model.Groups;
import com.u1.model.Order;
import com.u1.model.RefCustomer;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.Resources;
import com.u1.model.Roles;
import com.u1.model.Roles0;
import com.u1.model.RoleWithResource;
import com.u1.model.SearchResult;
import com.u1.model.SimpleAsset;
import com.u1.model.SimpleAssetWithTask;
import com.u1.model.SimpleTask;
import com.u1.model.Task;
import com.u1.model.TaskForUpdate;
import com.u1.model.TaskWithAsset;

import com.u1.model.SimpleUserWithGroup;
import com.u1.model.SimpleUsers;
import com.u1.model.SimpleUsers0;
import com.u1.model.TaskType;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.ComponentService;
import com.u1.service.StatisticsService;
//import com.u1.service.RolesService;
import com.u1.service.UserAuthService;
import com.u1.model.AssetAttributesTemplate;

public class U1Test {
	
	private static BeanFactory bf;
	private static CommonService commonService;
	private static UserAuthService userAuthService;
	private static ComponentService componentService;
	private static CommonDao commonDao;
//	private static RolesService rolesService;
	private static ReloadableResourceBundleMessageSource ms;
	private static StatisticsService statisticsService;
	private static MessageSourceHelper msh;

	
	static{
//		bf = new XmlBeanFactory(new FileSystemResource("D:/springsource/workspace-sts-3.2.0.RELEASE/U7Demo/WebContent/WEB-INF/hibernatedaotest-config.xml"));
		bf = new FileSystemXmlApplicationContext("D:/springsource/workspace/u1/WebContent/WEB-INF/test-config.xml");
//		bf = new FileSystemXmlApplicationContext("D:/springsource/workspace-sts-3.2.0.RELEASE/u1/WebContent/WEB-INF/test-config.xml");
		commonService = (CommonService)getBean("commonService");
		userAuthService = (UserAuthService)getBean("userAuthService");
		commonDao = (CommonDao)getBean("commonDao");
//		rolesService= (RolesService)getBean("rolesService");
//		commentService = (CommentService)getBean("commentService");
		ReloadableResourceBundleMessageSource ms= (ReloadableResourceBundleMessageSource)getBean("messageSource");
		statisticsService = (StatisticsService)getBean("statisticsService");
		componentService = (ComponentService)getBean("componentService");
		msh = (MessageSourceHelper)getBean("messageSourceHelper");
	}
	
	public static Object getBean(String beanName){
		return bf.getBean(beanName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
// TODO Auto-generated method stub
//		test2();
//		test5();
//		test6();
//		test7();
//		test8();
//		test9();
//		test12();
//		test8();
//		test14();
//		test15();
//		test16();
//		test17();
//		test18();
//		test19();
//		test20();
//		test21();
//		test22();
//		test23();
//		test24();
//		test25();
//		test26();
//		test27();
//		test28();
//		test29();
//		test30();
//		test31();
//		test32();
//		test33();
		test34();
	}
	public static void test1(){
		String[] testStr = new String[]{"admin", "Yufi", "levin", "test","lixf"};
		List<Long> statics=new ArrayList();
		Long loopCount=100l;
		String key;
		for(int i=0;i<loopCount;i++){
			key=testStr[loopCount.intValue()%testStr.length];
			Long s = System.currentTimeMillis();
			//TODO add testing method below
//			userAuthService.getAccessableResource(key);
			//TODO add testing method above
			Long e = System.currentTimeMillis();
			statics.add(e-s);
		}
		Long total=0l;
		for(Long sub:statics){
			System.out.print(sub);
			System.out.print("\t");
			total+=sub;
		}
		System.out.println();
		Double d = new Double(total)/new Double(loopCount);
		System.out.println(d);
	}
	
	public static void test2(){
//		Users user = (Users)commonService.getByUsername(Users.class, "huangzh");
//		System.out.println(user);
//		System.out.print(user.getGroupList().size());
//		System.out.println("----------------------------------------------------------------------------------");
//		user.getGroupList().remove(0);
//		Groups g1 = (Groups)commonService.get(Groups.class, 3);
//		Groups g2 = (Groups)commonService.get(Groups.class, 2);
//		System.out.println("----------------------------------------------------------------------------------");
//		user.getGroupList().add(g2);
//		user.getGroupList().add(g2);
		
		System.out.println("----------------------------------------------------------------------------------");
//		commonService.saveOrUpdate(user);
	}
	
	public static void test3(){
		Order order = new Order("roleName", false);
		List<Order> orders = new ArrayList();orders.add(order);
		SearchResult sr = commonService.search(Roles.class, null, orders);
		System.out.println(sr.getTotalCount());
		for(Object o:sr.getResult())
			System.out.println(((Roles)o).getRoleName());
	}
	public static void test4(){
		Long count =1l;
		int sizePerPage=10;
		int index=6;
		int currentPage=0;
		int totalpage = 0;
		if(count==0){
			currentPage=1;
			totalpage=1;
		}else{
			Long temp = count/sizePerPage;
			totalpage =temp.intValue() + (count%sizePerPage==0?0:1);
			if(index!=0){
				Long _count = new Long(index) * sizePerPage;
				if(_count>=count){
					index =totalpage -1;
				}
			}
			currentPage = index+1;
		}
		
		System.out.println();
		System.out.println(currentPage);
		System.out.println(totalpage);
	}
	
	public static void test5(){
		String[] name_prefix=new String[]{"Yufi","Carrie","Ufi", "zhang", "He","abc", "test", "hero", "superman"};
		for(int i=0;i<20;i++){
			for(int n=0;n<name_prefix.length;n++){
				Roles r = new Roles();
				r.setRoleName(name_prefix[n]+"_"+i);
				commonService.save(r);
			}
		}
	}
	public static void test6(){
//		List<RoleWithResource> rr = commonService.searchByMultiCondition(RoleWithResource.class, null, null, 20);
//		for(RoleWithResource r:rr){
//			System.out.println(r.getRoleId());
//			System.out.println(r.getRoleName());
//			System.out.println(r.getRoleResourceList().size());
//		}
		RoleWithResource rr1 = (RoleWithResource)commonService.get(RoleWithResource.class, 5);
		System.out.println(rr1.getRoleId());
		System.out.println(rr1.getRoleName());
//		System.out.println(rr1.getRoleResourceList().size());
		RoleWithResource rr2 = new RoleWithResource();
		rr2.setRoleId(rr1.getRoleId());
		rr2.setRoleName(rr1.getRoleName());
//		rr2.setRoleResourceList(null);
//		commonService.update(rr2);etRoleResourceList(null);
//		commonService.update(rr2);
		
	}
	
	public static void test7(){
//		List<RoleWithResource> rr = commonService.searchByMultiCondition(RoleWithResource.class, null, null);
//		for(RoleWithResource r:rr){
//			System.out.println(r.getRoleId());
//			System.out.println(r.getRoleName());
//			System.out.println(r.getResourceList().size());
//		}
		
//		List<Resources> r = commonService.searchByMultiCondition(Resources.class, null, null);
//		for(Resources r0:r){
//			System.out.println(r0.getResourceId());
//			System.out.println(r0.getResourceName());
//			System.out.println(r0.getDescription());
//			System.out.println(r0.getType());
//			System.out.println("--------------------------");
//		}
//		Order o=new Order("resourceId");List<Order> orders=new ArrayList();
//		orders.add(o);
//		SearchResult sr = commonService.search(RoleWithResource.class, null, null,16);
//		System.out.println(sr.getTotalCount());
//		sr = commonService.search(Roles0.class, null, null,16);
//		System.out.println(sr.getTotalCount());
//		for(Object o:sr.getResult()){
//			RoleWithResource rr=(RoleWithResource)o;
//			System.out.println("--------------------");
//			System.out.println(rr.getRoleId());
//			System.out.println(rr.getRoleName());
//			System.out.println(rr.getResourceList().size());
//		}
		System.out.println(commonService.listAll(RoleWithResource.class).size());
		System.out.println(commonService.listAll(Roles0.class).size());
	}
	
	public static void test8(){
		//
		List<Long> statics=new ArrayList();
		Object result=null;
		Long loopCount=1000l;
//		SimpleUserWithGroup ug=(SimpleUserWithGroup)commonService.get(SimpleUserWithGroup.class, 2);
//		BaseController bc = new BaseController();
		SimpleUserWithGroup newone;
		for(int i=0;i<loopCount;i++){
			Long id = new Long(i>50?i%50:i);
			String name = "Yufi_" + i;
			Long s = System.currentTimeMillis();
			//to do test
			AssetAttributesTemplateWithOption aa = (AssetAttributesTemplateWithOption)commonService.get(AssetAttributesTemplateWithOption.class, 68l);
			Long e = System.currentTimeMillis();
			statics.add(e-s);
		}
		Long total=0l;
		for(Long sub:statics){
			System.out.print(sub);
			System.out.print("\t");
			total+=sub;
		}
		System.out.println();
		Double d = new Double(total)/new Double(loopCount);
		System.out.println(d);
		
	}
	
	public static void test9(){
		
		RoleWithResource rr = new RoleWithResource();
		rr.setRoleId(141);
		rr.setRoleName("new Roles update");
		List<Resources> rList=new ArrayList();
		for(Integer i=1;i<=27;i++){
			Resources r = new Resources();
			r.setResourceId(i);
			rList.add(r);
		}
		rr.setResourceList(rList);
		commonService.update(rr);
		
	}
	
	public static void test10(){
		RoleWithResource rr = null;
//		Object[] o = rolesService.getRolesDetailsAndAllRecourcesList(141);
//		List<Resources> rList =(List<Resources>) o[0];
//		rr = (RoleWithResource)o[1];
		boolean[] flags;
//		for(Resources r:rList)
//			System.out.println(r.getResourceId());
		System.out.println("---------");
		for(Resources r0:rr.getResourceList()){
			System.out.print(r0.getResourceId());
//			System.out.print(rList.contains(r0));
			System.out.println();
		}
		System.out.println("-----------------------------------");
//		flags = new boolean[rList.size()];
//		for(int i=0;i<flags.length;i++){
//			if(rr.getResourceList().contains(rList.get(i))){
//				flags[i]=true;
//			}else{
//				flags[i]=false;
//			}
//			System.out.println(flags[i]);
//		}
		
		System.out.println("-----------------------------------");
//		rr = (RoleWithResource)commonService.get(RoleWithResource.class, 141);
//		for(Resources r:rList)
//			System.out.println(r.getResourceId());
		System.out.println("---------");
		for(Resources r0:rr.getResourceList()){
			System.out.print(r0.getResourceId());
//			System.out.print(rList.contains(r0));
			System.out.println();
		}
		System.out.println("-----------------------------------");
//		flags = new boolean[rList.size()];
//		for(int i=0;i<flags.length;i++){
//			if(rr.getResourceList().contains(rList.get(i))){
//				flags[i]=true;
//			}else{
//				flags[i]=false;
//			}
//			System.out.println(flags[i]);
//		}
		
		for(Resources r0:rr.getResourceList()){
			System.out.println("----------------------------------------------------------------------");
//			for(Resources r1:rList){
//				System.out.print(r0.equals(r1) + "\t");
//			}
		}
	}
	
	public static void test11(){
//		List<Roles> roleList = commonService.listAll(Roles.class);
//		System.out.println(roleList.size());
		
		List<GroupWithRole> groupList = commonService.listAll(GroupWithRole.class);
		System.out.println(groupList.size());
		
	}
	
	public static void test12(){
		String[] name_prefix=new String[]{"Yufi","Carrie","Ufi", "zhang", "He","abc", "test", "hero", "superman"};
		for(int i=0;i<5;i++){
			for(int n=0;n<name_prefix.length;n++){
				Groups r = new Groups();
				r.setGroupName("Group_"+name_prefix[n]+"_"+i);
				commonService.save(r);
			}
		}
	}
	
	public static void test13(){
//		List<Groups> gl = commonService.listAll(Groups.class);
//		for(Groups g:gl){
//			System.out.println(g.getGroupName());
//		}
//		List<SimpleUsers0> sl = commonService.searchByMultiCondition(SimpleUserWithGroup.class, null, null);
//		for(SimpleUsers0 su:sl){
//			System.out.println(su.getUsername());
//		}
		
		SimpleUserWithGroup su = (SimpleUserWithGroup)commonService.get(SimpleUserWithGroup.class, 1);
		SimpleUserWithGroup su2 = new SimpleUserWithGroup();
		su2.setUserId(17);
		su2.setGivenName("Yufi"+"_");
		System.out.println("--------------------------");
//		su.setUserId(null);
		
		commonService.merge(su);
		System.out.println(su.getGivenName());
		
	}
	public static void checkUpdateField(Object dbone, Object updateone){
//		Field field[] = dbone.getClass().getDeclaredFields();
		try{
			for(Field field:dbone.getClass().getDeclaredFields()){
				Object dbfield = field.get(dbone);
				Object updatefield = field.get(updateone);
				if(updatefield==null & dbfield!=null && !(dbfield instanceof List)){
					field.set(dbone, field.get(updateone));
				}
			}
		}catch(Exception e){
			//error handling...
		}
		
	}
	
	public static void test14(){
		SimpleUserWithGroup newSimpleUserWithGroup = new SimpleUserWithGroup();
		newSimpleUserWithGroup.setUsername("Yufi_He");
		newSimpleUserWithGroup.setGivenName("Yufi");
		newSimpleUserWithGroup.setTelephone("1357924");
		newSimpleUserWithGroup.setGroupList(null);
		newSimpleUserWithGroup.setEmail("abc@abc.com");
		commonService.myMerge(newSimpleUserWithGroup, 2);
	}
	public static void test15(){
//		AssetType at = new AssetType();
//		at.setCode("abcdefg");
//		at.setDescription("abc");
//		at.setEnabled(true);
//		commonService.save(at);
		
		List<AssetType> atList = (List<AssetType>)commonService.listAll(AssetType.class);
		for(AssetType at0:atList){
			System.out.println(at0);
		}
	}
	
	public static void test16(){
//		List<TaskType> ttlist = commonService.listAll(TaskType.class);
//		for(TaskType tt:ttlist){
//			System.out.println(tt);
//		}
		
//		List<Department> dlist = commonService.listAll(Department.class);
//		for(Department d:dlist){
//			System.out.println(d);
//		}
		
//		System.out.println(commonService.delete(AssetType.class, 64));
//		System.out.println(commonService.delete(AssetType.class, 264));
//		System.out.println(commonService.delete(SimpleUserWithGroup.class, 22));
//		System.out.println(commonService.delete(SimpleUserWithGroup.class, 23));
		
//		SimpleUserWithGroup s = new SimpleUserWithGroup();
//		s.setUserId(24);
//		commonService.delete(s);
		
		ReloadableResourceBundleMessageSource message = (ReloadableResourceBundleMessageSource)getBean("messageSource");
		System.out.print(message.getMessage("success_delete_assettype", new String[]{"abc"}, Locale.SIMPLIFIED_CHINESE));
	}
	
	public static void test17(){
		AssetType at = new AssetType();
		at.setEnabled(true);
		commonService.myMerge(at, 89);
		
	}
	
	public static void test18(){
//		List<AttrSelectOption> ttlist = commonService.listAll(AttrSelectOption.class);
//		for(AttrSelectOption aa:ttlist){
//			System.out.println(aa);
//		}
		
//		List<AssetAttributesTemplateWithOption> aatolist = commonService.listAll(AssetAttributesTemplateWithOption.class);
//		for(AssetAttributesTemplateWithOption aato:aatolist){
//			System.out.println(aato.toString());
//			if(aato.getSelectOptionList()!=null){
//				for(AttrSelectOption aa:aato.getSelectOptionList()){
//					System.out.println("\t"+aa.toString());
//				}
//			}
//		}
		
//		List<String> sectionList = statisticsService.getExistingAssetAttributesTeamplateSection();
//		if(sectionList == null){
//			System.out.println("xxxxxxxxxxxx");
//		}
//		System.out.println(sectionList.size());
//		for(String s:sectionList){
//			System.out.println(s);
//		}
		
//		AssetAttributesTemplate aatwo = new AssetAttributesTemplate();
//		aatwo.setAssetTypePid(22l);
//		aatwo.setAttributeName("newoption");
//		aatwo.setAttributeType("SELECT");
//		aatwo.setEnabled(true);
//		aatwo.setSection("newoption");
//		commonService.save(aatwo);
//		aatwo.setAssetAttrTemplatePid(73l);
//		List<AttrSelectOption> optionlist = new ArrayList();
//		for(int i=5;i<20;i++){
//			AttrSelectOption option = new AttrSelectOption();
//			option.setDescription(i+"");
//			option.setValue(i+"");
//			option.setTemplate(aatwo);
//			commonService.save(option);
//			optionlist.add(option);
//		}
		//aatwo.setSelectOptionList(optionlist);
//		commonService.save(aatwo);
		
		AssetAttributesTemplateWithOption aa = (AssetAttributesTemplateWithOption)commonService.get(AssetAttributesTemplateWithOption.class, 74l);
		System.out.println(aa);
		System.out.println("-------------------------------------------------------------------");
		System.out.println(aa.getSelectOptionList() instanceof List);
		for(int i=0;i<aa.getSelectOptionList().size();i++){
//			System.out.println(aa.getSelectOptionList().get(i).getTemplate().getAssetAttrTemplatePid());
			System.out.println("\t"+aa.getSelectOptionList().get(i));
//			System.out.println("\t"+"\t"+aa.getSelectOptionList().get(i).getTemplate());
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println(aa.getSelectOptionList().size());
//		AttrSelectOption s = new AttrSelectOption();
//		s.setDescription("e");
//		s.setTemplate(aa);
//		aa.getSelectOptionList().add(s);
//		commonService.update(aa);
//		System.out.println(aa.getSelectOptionList().size());
		
		List<Condition> conditions = new ArrayList();
		List<AttrSelectOption> optlist = commonService.searchByMultiCondition(AttrSelectOption.class, conditions, null);
		
		
		System.out.println("-------------------------------------------------------------------");
//		AssetAttributesTemplateWithOption aa2 = new AssetAttributesTemplateWithOption();
//		aa2.setAssetAttrTemplatePid(68l);
		
		
//		commonService.delete(aa.getSelectOptionList().get(0));
//		System.out.println(aa.getSelectOptionList().size());
		
//		System.out.println(aa.getSelectOptionList().contains(s));
		
		commonService.delete(aa.getSelectOptionList().get(0));
		aa.getSelectOptionList().remove(0);
		System.out.println(aa.getSelectOptionList().size());
		commonService.update(aa);
		
		
		
//		AssetAttributesTemplate0 a = aa;
//		AssetAttributesTemplate aaa = (AssetAttributesTemplate)a;
//		System.out.println(aaa);
	}
	
	public static void test19(){
		AttrSelectOption option = new AttrSelectOption();
		option.setDescription("~~~~~");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
//		 Set<ConstraintViolation<AttrSelectOption>> constraintViolations =validator.validate( option );
//		 System.out.println(constraintViolations.size());
//		 for(Object o:constraintViolations){
//			 System.out.println(o);
//		 }
		 
		 AssetAttributesTemplateWithOption aa = new AssetAttributesTemplateWithOption();
		 aa.setAttributeName("12");
		 aa.setSection("2aaa");
		 Set<ConstraintViolation<AssetAttributesTemplateWithOption>> constraintViolations2 =validator.validate(aa);
		 System.out.println(constraintViolations2.size());
		 for(Object o:constraintViolations2){
			 System.out.println(o);
		 }
//		 System.out.println(MessageSourceHelper.getMessage("MessageSourceHelper", null, null, null));
	}
	
	public static void test20(){
		AssetAttributesTemplateWithOption aa = new AssetAttributesTemplateWithOption();
//		aa.setAssetAttrTemplatePid(81l);
		aa.setAttributeName("NewSelectTest");
		aa.setAssetTypePid(22);
		aa.setEnabled(true);
		aa.setAttributeType("SELECT");
		aa.setSelectOptionList(new ArrayList());
		for(int i=1;i<10;i++){
			AttrSelectOption option= new AttrSelectOption();
			option.setDescription(i+"");
			option.setTemplate(aa);
			aa.getSelectOptionList().add(option);
		}
		System.out.println("hello..............................................");
		commonService.myMerge(aa, 500l,0);
		System.out.println("hello..............................................end");
	}
	
	public static void test21(){
		AssetAttributesTemplateWithOption aa = (AssetAttributesTemplateWithOption)commonService.get(AssetAttributesTemplateWithOption.class, 83l);
		for(int i=1;i<10;i++){
			AttrSelectOption option= new AttrSelectOption();
			option.setDescription(i+"");
			option.setTemplate(aa);
			aa.getSelectOptionList().add(option);
		}
		commonService.delete(aa);
		
		System.out.println(commonService.enable(AssetAttributesTemplateWithOption.class, 101l));
		System.out.println(commonService.disable(AssetAttributesTemplateWithOption.class, 101l));
		System.out.println(commonService.disable(AssetAttributesTemplateWithOption.class, 101l));
		System.out.println(commonService.enable(AssetAttributesTemplateWithOption.class, 1000l));
		System.out.println(commonService.disable(AssetAttributesTemplateWithOption.class, 1000l));
	}
	public static void test22(){
//		List<SimpleAsset> alist = commonService.searchByMultiCondition(SimpleAsset.class, null, null, 10000, 0);
//		for(SimpleAsset a:alist){
//			System.out.println(a);
//		}
		//System.out.println(msh.getMessage("dateformaterror", null));
		
		SimpleUserWithGroup updateSimpleUserWithGroup = new SimpleUserWithGroup();
		updateSimpleUserWithGroup.setUsername("admin3");
		updateSimpleUserWithGroup.setGivenName("admin3");
		updateSimpleUserWithGroup.setTelephone("131313");
		updateSimpleUserWithGroup.setGroupList(null);
		commonService.myMerge(updateSimpleUserWithGroup, 30l);
	}
	
	public static void test23(){
//		List<AssetTypeWithTemplate>  attl = commonService.listAll(AssetTypeWithTemplate.class);
//		System.out.println(attl.size());
//		for(AssetTypeWithTemplate att : attl){
//			System.out.println(att);
//			System.out.println("\t"+att.getTemplateList().size());
//		}
		
		long s = System.currentTimeMillis();
		AssetTypeWithTemplate att = (AssetTypeWithTemplate)commonService.get(AssetTypeWithTemplate.class, 22l);
		System.out.println(att);
		System.out.println("\t"+att.getTemplateList().size());
		long e = System.currentTimeMillis();
		System.out.println(e-s);
	}
	
	public static void test24(){
		long s = System.currentTimeMillis();
//		List<Asset> al = commonService.listAll(Asset.class);
		long e = System.currentTimeMillis();
//		System.out.println(al.size());
//		for(Asset a : al){
//			System.out.println(a);
//			System.out.println("\t"+a.getAssetType());
//			System.out.println("\t\t"+a.getAssetType().getTemplateList().size());
//		}
//		System.out.println(e-s);
		
		s = System.currentTimeMillis();
		Asset a = (Asset)commonService.get(Asset.class, 2364l);
		e = System.currentTimeMillis();
		System.out.println(e-s);
		System.out.println(a.getAssetName());
		System.out.println("\t"+a.getAssetType().getTemplateList().size());
		System.out.println("-------------------------------------------------------------");
		for(int i=0;i<a.getAssetType().getTemplateList().size();i++){
			AssetAttributesTemplateWithOption aatwo=a.getAssetType().getTemplateList().get(i);
			System.out.println("\t"+aatwo.getAttributeName()+"\t"+aatwo.getAttributeType()+"\t"+aatwo.getSection());
		}
		System.out.println("-------------------------------------------------------------");
		for(int i=0;i<a.getAttributeList().size();i++){
			AssetAttribute aa = a.getAttributeList().get(i);
			System.out.println(aa.getAssetAttrbutePid());
			System.out.println("\t"+aa.getStringValue()+"\t"+aa.getDoubleValue()+"\t"+aa.getDoubleValue()+"\t"+aa.getIntegerValue()+"\t"+aa.getBooleanValue());
			for(int n=0;n<aa.getSelectedOptionList().size();n++){
				System.out.println("\t\t"+aa.getSelectedOptionList().get(n).getDescription());
			}
			if(aa.getSelectedOptionList().size()==3){
			}
		}
		System.out.println("-------------------");
		Object[][] values=Util.returnAttributesStringArray(a);
		
		
//		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
//		Asset newa = new Asset();
//		newa.setAssetName("mytestassetname");
//		newa.setAssetNum("UFI20131101");
//		newa.setAssetPid(2365l);
//		AssetAttribute aa = new AssetAttribute();
//		aa.setAssetAttrbutePid(20164l);
//		aa.setAssetPid(newa.getAssetPid());
//		aa.setAttributeTemplatePid(124l);
//		AttrSelectOption aso = new AttrSelectOption();
//		aso.setAttrSelectOptionPid(255l);
//		AttrSelectOption aso2 = new AttrSelectOption();
//		aso2.setAttrSelectOptionPid(254l);
//		AttrSelectOption aso3 = new AttrSelectOption();
//		aso3.setAttrSelectOptionPid(253l);
//		List<AttrSelectOption> selOptionList = new ArrayList();
//		selOptionList.add(aso);
//		selOptionList.add(aso2);
//		selOptionList.add(aso3);
//		aa.setSelectedOptionList(selOptionList);
//		List<AssetAttribute> aalist = new ArrayList();
//		aalist.add(aa);
//		newa.setAttributeList(aalist);
		
//		commonService.myMerge(newa, 2365l, 1);
		
	}

	public static void test25(){
//		List<AssetGroup> aglist = commonService.searchByMultiCondition(AssetGroup.class, BaseController.initEnabledCondition(),null);
//		for(AssetGroup ag:aglist){
//			System.out.println(ag);
//		}
		
		AssetGroupWithSimpleAsset agwsa = (AssetGroupWithSimpleAsset)commonService.get(AssetGroupWithSimpleAsset.class, 1l);
		System.out.println(agwsa.getGroupName());
		System.out.println(agwsa.getAssetList().size());
		for(SimpleAsset as : agwsa.getAssetList()){
			System.out.println(as);
		}
	}
	
	public static void test26(){
//		List<SimpleAsset> asgl2 = commonService.searchByMultiCondition(SimpleAsset.class, null, null, 20, 0);
//		System.out.println(asgl2.size());
		List<com.u1.model.Task> tl = commonService.listAll(com.u1.model.Task.class);
//		List<TaskType> ttl = commonService.listAll(TaskType.class);
//		List<SimpleUsers> sul = commonService.listAll(SimpleUsers.class);
//		List<Department> dl = commonService.listAll(Department.class);
		System.out.println(tl.size());
//		System.out.println(sul.size());
		for(com.u1.model.Task t:tl){
			System.out.println(t);
			System.out.println("\t"+t.getTaskType());
			System.out.println("\t\t"+t.getCreatedBy());
			System.out.println("\t\t\t"+t.getAssignedTo());
//			System.out.println("\t\t\t\t"+t.getDepartment());
//			if(t.getTaskType()==null){
//				for(TaskType tt:ttl){
//					if(t.getTaskTypeCode().equals(tt.getCode())){
//						t.setTaskType(tt);
//						commonService.update(t);
//						break;
//					}
//				}
//			}
//			if(t.getCreatedBy()==null || t.getAssignedTo()==null){
//				boolean needupdate = false;
//				if(t.getCreatedBy()==null){
//					for(SimpleUsers su:sul){
//						if(t.getCreatedByName().equals(su.getUsername())){
//							t.setCreatedBy(su);
//							needupdate = true;
//							break;
//						}
//					}
//				}
//				if(t.getAssignedTo()==null){
//					for(SimpleUsers su:sul){
//						if(t.getAssignedToName().equals(su.getUsername())){
//							t.setAssignedTo(su);
//							needupdate = true;
//							break;
//						}
//					}
//				}
//				if(needupdate){
//					commonService.update(t);
//				}
//			}
//			if(t.getDepartmentstr()!=null && !t.getDepartmentstr().isEmpty()){
//				for(Department d:dl){
//					if(t.getDepartmentstr().equals(d.getDepartmentName())){
//						t.setDepartment(d.getDepartmentPid());
//						commonService.update(t);
//						break;
//					}
//				}
//			}
			
		}
	}
	public static void test27(){
		Date now = new Date();
		DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		DataConvertion<Date> d = DataConvertionUtil.getDateRange("aaa", 300);
		for(Date dd:d.getResult()){
			if(dd!=null)
				System.out.println(dfs.format(dd));
		}
		System.out.println(d.getErrorMessage());
	}
	
	public static void test28(){
//		List<Condition> cs = BaseController.initEnabledCondition();
//		DataConvertion<Date> initDate = DataConvertionUtil.getDateRange("today", 3*200);
//		System.out.println(DataConvertionUtil.df.format(initDate.getResult().get(0)));
//		System.out.println(DataConvertionUtil.df.format(initDate.getResult().get(1)));
//		Condition c = new Condition("createdDatetime",initDate.getResult().get(0), initDate.getResult().get(1));
//		cs.add(c);
//		
//		Condition c = new Condition("assetGroupList", false, "empty");
//		cs.add(c);
		
//		SearchResult sr = commonService.search(TaskWithAssetAndAssetGroup.class, cs, null);
//		System.out.println(sr.getTotalCount());
		
		
//		Asset a = (Asset)commonService.get(Asset.class, 2372l);
//		a.getAssetType().setExistingAttributeIdValues(Util.returnAttributesStringArray(a));
//		Object[][] b = a.getAssetType().getExistingAttributeIdValues();
//		for(Object[] c:b){
//			for(Object d:c){
//				System.out.println(d);
//			}
//		}
//		System.out.println("--------------");
//		System.out.println("1.attributelist size="+a.getAttributeList().size());
//		for(AssetAttribute at : a.getAttributeList()){
//			System.out.println(at.getAttributeTemplatePid());
//		}
//		System.out.println("--------------");
//		System.out.println("--------------");
//		System.out.println("2.assettype template size="+a.getAssetType().getTemplateList().size());
//		for(AssetAttributesTemplateWithOption aa:a.getAssetType().getTemplateList()){
//			System.out.println(aa.getAssetAttrTemplatePid());
//		}
		
//		List<TaskType> ttl = commonService.listAll(TaskType.class);
//		for(TaskType tt:ttl){
//			System.out.println(tt);
//		}
//		SimpleUsers su = new SimpleUsers();
//		su.setGivenName("HE XIAO HUI");
//		commonService.myMerge(su, 2l);
		
//		List<TaskForUpdate> tl = commonService.listAll(TaskForUpdate.class);
//		for(TaskForUpdate t:tl){
//			System.out.println(t);
//		}
		
		
//		System.out.println(commonService.countByMultiCondition(Users.class, null));
//		commonService.test();
//		List<Condition> cs = new ArrayList();
//		SimpleAssetWithTask awt = (SimpleAssetWithTask)commonService.get(SimpleAssetWithTask.class, 2367);
//		System.out.println(awt.getTaskList().size());
//		List<TaskWithAsset> tl = commonService.searchByMultiCondition(TaskWithAsset.class, null, null, 0, 0)
		
//		List<Attachment> al = commonService.listAll(Attachment.class);
//		System.out.println(al.size());
//		
//		List<Condition> cs = new ArrayList();
//		Condition c = new Condition("assetId", 1);
//		cs.add(c);
//		List<Attachment> atml = commonService.listAll(Attachment.class);
//		System.out.println(atml.size());
		

	}
	
	public static void test29(){
//		System.out.println('a');
//		List<Object[]> result = statisticsService.getLast12MonthTaskCountByMonth();
//		System.out.println(result.size());
		
//		int[] taskCount = statisticsService.getTaskCountOfThisMonth();
//		for(int i:taskCount){
//			System.out.println(i);
//		}
		
//		output(statisticsService.getPendingTask());
	}
	
	public static void test30(){
//		List<Customer> cl = commonService.listAll(Customer.class);
//		System.out.println(cl.size());
//		for(Customer c:cl){
//			System.out.println(c);
//		}
		List<Component> ufal = commonService.listAll(Component.class);
		System.out.println(ufal.size());
		for(Component u : ufal){
			System.out.println(u.getField1());
			System.out.println(u.getField2());
		}
	}
	
	public static void test31(){
		List<Condition> cs = Util.initEnabledCondition();
		Condition c;
		String[] vs = new String[]{Constants.TASK_STATUS_ASSIGNED, Constants.TASK_STATUS_PENDING_REASSIGNED};
		c = new Condition("status", vs);
		cs.add(c);
		c = new Condition("customerId", new Integer(1));
		cs.add(c);
		List<SimpleTask> tl = commonService.searchByMultiCondition(SimpleTask.class, cs, null, 0, 0);
		System.out.println(tl.size());
		for(SimpleTask t:tl){
			System.out.print(t.getCustomerId()+"\t");
			System.out.print(t.getStatus()+"\t");
		}
	}
	
	public static void test32(){
		List<Condition> cs = Util.initEnabledCondition();
		Condition c;
		c = new Condition("component/field2", "SAMSUNG", false);
		cs.add(c);
		List<Componentstore> result = commonService.searchByMultiCondition(Componentstore.class, cs, null);
		System.out.println(result.size());
	}
	
	public static void output(List<Object[]> result){
		System.out.println(result.size());
		for(Object[] os:result){
			for(Object o:os){
				System.out.print(o);
				System.out.print("\t");
				System.out.print(o.getClass());
				System.out.print("\t");
			}
			System.out.println("------");
		}
	}
	
	public static void test33(){
		List<Object> statistics = componentService.getComponentstoreStatistics(new Integer(15));
		for(Object o : statistics){
			output((List<Object[]>)o);
		}
		System.out.println(componentService.getComponentstoreLatestPrice(15));
	}
	public static void test34(){
		List<RefCustomer> clist = commonService.listAll(RefCustomer.class);
		System.out.println(clist.size());
		List<RefTask> tlist = commonService.listAll(RefTask.class);
		System.out.println(tlist.size());
		List<RefUser> ulist = commonService.listAll(RefUser.class);
		System.out.println(ulist.size());
		List<Componentrecord> rlist = commonService.listAll(Componentrecord.class);
		System.out.println(rlist.size());
	}
}
