package com.u1.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static String getLastDayOfLastMonth(Calendar c){
		Calendar cc = (Calendar)c.clone();
		cc.add(Calendar.DATE, -cc.get(Calendar.DAY_OF_MONTH));
		return Constants.SIMPLE_DATE_FORMAT.format(cc.getTime());
	}
	public static String get1stDayOfCurrentMonthInLastYear(Calendar c){
		Calendar cc = (Calendar)c.clone();
		cc.add(Calendar.YEAR, -1);
		cc.set(Calendar.DAY_OF_MONTH, 1);
		return Constants.SIMPLE_DATE_FORMAT.format(cc.getTime());
	}
	public static String get1stDayOf11MonthAgo(Calendar c){
		Calendar cc = (Calendar)c.clone();
		cc.add(Calendar.MONTH, -11);
		cc.set(Calendar.DAY_OF_MONTH, 1);
		return Constants.SIMPLE_DATE_FORMAT.format(cc.getTime());
	}
	public static Calendar getStartOfThisMonth(Calendar c){
		Calendar cc = (Calendar)c.clone();
		cc.set(Calendar.DAY_OF_MONTH, 1);
		//cc.add(Calendar.MONTH, -1);
		cc.set(Calendar.HOUR, 0);
		cc.set(Calendar.MINUTE, 0);
		cc.set(Calendar.SECOND,0);
		cc.set(Calendar.MILLISECOND, 0);
		return cc;
	}
	
	
}
