package com.u1.model;

public class ModelConstants {
	public final static String DEFINED_CODE = "[A-Z]{3}";
	
	public final static String REG_CHN_20 = "[\\s\u4e00-\u9fa5_a-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{1,20}";
	
	public final static String REG_CHN_ALLOW_EMPTY_20 = "[\\s\u4e00-\u9fa5_a-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{0,20}";
	
	public final static String REG_CHN_ALLOW_EMPTY_100 = "[\\s\u4e00-\u9fa5_a-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{0,100}";
	
	public final static String REG_CHN_100 = "[\\s\u4e00-\u9fa5_a-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{1,100}";
	
	public final static String REG_ENG_20 = "[\\sa-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{1,20}";
	
	public final static String REG_ENG_ALLOW_EMPTY_20 = "[\\sa-zA-Z0-9~!@#$%^&*()_+-=－|\\;:,.\\/?，。？：；！—……、]{0,20}";
	
	public final static String EMAIL="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$";
	public final static String EMAIL_ALLOW_EMPTY="[a-zA-Z0-9_@.]{0,30}";
	public final static String TEL="((\\d{11})|^((\\d{7,8})|(\\d{3,4})-(\\d{7,8})|(\\d{3,4})-(\\d{7,8})-(\\d{1,4})|(\\d{7,8})-(\\d{1,4}))$)";
	public final static String TEL_ALLOW_EMPTY="((\\d{0,11})|^((\\d{0,8})|(\\d{0,4})-(\\d{0,8})|(\\d{0,4})-(\\d{7,8})-(\\d{0,4})|(\\d{0,8})-(\\d{0,4}))$)";
}
