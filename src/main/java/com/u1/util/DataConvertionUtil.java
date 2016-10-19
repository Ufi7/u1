package com.u1.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.u1.controller.BaseController;
import com.u1.model.DataConvertion;

public class DataConvertionUtil {
	private final static Logger logger = Logger.getLogger(DataConvertionUtil.class);
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	
	public static DataConvertion convertDateFromTo(String from, String to){
		DataConvertion<Date> v = new DataConvertion();
		Date dateFrom;
		Date dateTo;
		if(from.isEmpty()||to.isEmpty()){
			v.addMessage(MessageSourceHelper.getMessage("dateincompleteerror", null));
			return v;
		}
		try {
			dateFrom = df.parse(from);
			dateTo = df.parse(to);
			if(dateTo.before(dateFrom)){
				v.addMessage(MessageSourceHelper.getMessage("dateperioderror", null));
			}
			v.addResult(dateFrom);
			v.addResult(new Date(dateTo.getTime()+24*60*60*1000-1));
		} catch (ParseException e) {
			logger.debug(e);
			v.addMessage(MessageSourceHelper.getMessage("dateformaterror", null));
		}
		return v;
	}
	
	public static DataConvertion<Date> getDateRange(String toStr, int offset){
		DataConvertion<Date> v = new DataConvertion();
		Date from = null, to = null;
		if(toStr == null || toStr.equalsIgnoreCase("today")){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE,0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.add(Calendar.MILLISECOND, -1);
			to = c.getTime();
		}else{
			try{
				to = df.parse(toStr);
				to = new Date(to.getTime()+24*60*60*1000-1);
			}catch(ParseException e) {
				logger.debug(e);
				v.addMessage(MessageSourceHelper.getMessage("dateformaterror", null));
			}
		}
		if(to!=null){
			from = new Date(to.getTime()-(long)offset*24*60*60*1000+1);
		}
		v.addResult(from);
		v.addResult(to);
		return v;
	}
}
