package com.u1.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.u1.model.AssetType;
import com.u1.model.AttrSelectOption;
import com.u1.model.DataConvertion;
import com.u1.model.ModelConstants;
import com.u1.model.SimpleUserWithGroup;
import com.u1.util.Constants;
import com.u1.util.DataConvertionUtil;
import com.u1.util.DateUtil;
import com.u1.util.MessageSourceHelper;

public class U1SimpleTest {
	public static void main(String[] args) {
//		SimpleUserWithGroup sg = new SimpleUserWithGroup();
//		Class c = sg.getClass();
//		while(c.getSuperclass()!=null){
//			System.out.println(c);
//			System.out.println(c.getDeclaredFields().length);
//			c=c.getSuperclass();
//		}
//		System.out.println(sg.isEnabled());
		
//		AssetType at = new AssetType();
//		at.setEnabled(true);
		
//		System.out.println(at.getEnabled());
		
//		System.out.println(MessageSourceHelper.messageSource);
		//System.out.println(Long.valueOf(""));
//		DataConvertionUtil.convertDateFromTo("2013-10-10", "2013-10-20");
		
//		System.out.println("HITACHI HCP－960X".matches(ModelConstants.REG_CHN_ALLOW_EMPTY_100));
//		
//		System.out.println("    ".trim().length());
//		
//		String a=null;
//		System.out.println(a.isEmpty());
		
		DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		DataConvertion dc = DataConvertionUtil.convertDateFromTo("t", "2013-10-10");
//		System.out.println(dfs.format((Date)dc.getResult().get(0)));
//		System.out.println(dfs.format((Date)dc.getResult().get(1)));
		
//		DataConvertion<Date> initDate = DataConvertionUtil.getDateRange("today", 90);
//		System.out.println(dfs.format(initDate.getResult().get(0)));
//		System.out.println(dfs.format(initDate.getResult().get(1)));
//		
//		
//		String a= "";
//		System.out.println(Long.valueOf(a));
		
//		Calendar c = Calendar.getInstance();
//		System.out.println(Calendar.getInstance().get(Calendar.YEAR));
//		c.add(Calendar.HOUR_OF_DAY, 24);
//		System.out.println(dfs.format(c.getTime()));
		
//		Calendar c = Calendar.getInstance();
//		Calendar e = (Calendar)c.clone();
//		e.add(Calendar.HOUR, 12);
//		System.out.println(dfs.format(e.getTime()));
		
		
//		String str = "1";
//		long s = System.currentTimeMillis();
//		while(str.length()<10000){
//			str = str.concat("0");
//		}
//		long e = System.currentTimeMillis();
//		System.out.println(str);
//		System.out.println(e - s);
		
//		String a = "abc";
//		String b = "Bc";
		//System.out.println(a.inde.indexOf(b));
//		String em = "Duplicate entry '工单管理角色' for key 'ROLE_NAME'";
//		String[] s = em.split("'");
//		for(String ss:s){
//			System.out.println(ss);
//		}
//		Integer a = Integer.valueOf("99.9");
//		System.out.println(a);
//		boolean a = false;
//		boolean b = true;
		
//		int a = 5;
//		boolean[] vs = {false, false, false, false, true, true};
//		int[] ars = new int[]{3, 2};
//		System.out.println(checkright(vs, ars));
//		Object b = 5;
//		Object d = new Integer[]{5, 6};
//		System.out.println(b instanceof Integer);
//		System.out.println(b instanceof Integer[]);
//		System.out.println(d instanceof Integer);
//		System.out.println(d instanceof Integer[]);
		
		
//		caclScore();
//		calendartest();
		forTest();
	}
	
	public static boolean checkright(boolean[] vs, int[] ars){
		boolean value = false;
		for(int a=0;a<ars.length;a++){
			value=vs[ars[a]];
			if(value==true)
				return value;
		}
		return value;
	}
	
	public static void caclScore(){
		int a = 1;
		int score;
		long timeout = 1000*60*60*5 +36000;
		long targetspan = 30*1000*60*60;
		
    	if(a==1){// calculate based on timeout count
    		score = 100 - 1*((new Long(timeout/(1000*60*60)).intValue()+(timeout%(1000*60*60)==0?0:1)));
    	}else{// calculate based on timeout percentage
    		long calunit = targetspan*10/100;
    		System.out.println(calunit);
    		System.out.println(timeout/calunit+ (timeout%calunit==0?0:1));
    		score = 100 - 20*new Long(timeout/calunit).intValue() - (timeout%calunit==0?0:1)*20;
    	}
    	System.out.println(score);
    	if(score<0){
    		score=0;
    	}
    	System.out.println(score);
    	
	}
	public static void calendartest(){
//		Calendar c = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		c.add(Calendar.DATE, -c.get(Calendar.DATE));
//		System.out.println(sdf.format(c.getTime()));
//		Calendar c = Calendar.getInstance();
//		System.out.println(DateUtil.getLastDayOfLastMonth(c));
//		System.out.println(DateUtil.get1stDayOfCurrentMonthInLastYear(c));
//		String hql = "select count(*), avg(t.score), year(t.endDatetime),month(t.endDatetime) from Task as t where t.status ='done' and t.endDatetime between '"
//				.concat(DateUtil.get1stDayOfCurrentMonthInLastYear(c)).concat("' and '").concat(DateUtil.getLastDayOfLastMonth(c))
//				.concat("' group by year(t.endDatetime), month(t.endDatetime) order by t.endDatetime desc");
//		System.out.println(hql);
//		System.out.println(Constants.SIMPLE_DATE_FORMAT_LONG.format(DateUtil.getStartOfThisMonth(Calendar.getInstance()).getTime()));

	}
	
	public static void forTest(){
//		List<Integer> il = null;
//		while(il!=null){
//		for(Integer i :il){
//			System.out.println(i);
//		}
//		}
//		for(int i=0;i<1000;i++){
//			Integer a = new Integer(i);
//			System.out.println(a.equals(i));
//		}
//		String s = "abcbcd";
//		String[] ss = s.split("/");
//		System.out.println(ss.length);
//		for(String a:ss){
//			System.out.println(a);
//		}
		
		System.out.println(DateUtil.get1stDayOfCurrentMonthInLastYear(Calendar.getInstance()));
		System.out.println(DateUtil.get1stDayOf11MonthAgo(Calendar.getInstance()));
		System.out.println(new Integer(0).equals(0));
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.MONTH));
		System.out.println(c.get(Calendar.YEAR));
		
		Integer[] is = new Integer[1000];
		for(int i=0;i<is.length;i++){
			is[i] = new Integer(i);
		}
		for(int n=0;n<is.length;n++){
			System.out.println(is[n].equals(n));
			System.out.println(is[n]==n);
		}
		
//		String a = null;
//		Integer b = Integer.valueOf(a);
//		System.out.println(b);
		
//		List a = new ArrayList();
//		a.add(new Object[]{"a", new Integer(5)});
//		Object[] b = (Object[])a.get(0);
//		Integer d = (Integer)b[1];
//		d+=5;
//		Object[] e = (Object[])a.get(0);
//		for(Object f:e){
//			System.out.println(f);
//		}
	}
	
	
}
