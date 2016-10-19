package com.u1.util;

import java.text.SimpleDateFormat;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Constants {
	public final static long MAX_REVALUALBE_TASK_PERIOD=7*24*60*60*1000;
	
	public final static String TASK_STATUS_CREATED = "created";
	public final static String TASK_STATUS_PENDING_REASSIGNED = "pending-reassigned";
	public final static String TASK_STATUS_ASSIGNED = "assigned";
	public final static String TASK_STATUS_IN_PROGRESS = "in_progress";
	public final static String TASK_STATUS_DONE="done";
	public final static String TASK_STATUS_REJECTED="rejected";
	
	public final static String TASK_ACTION_CODE_ADD_LOG = "n";
	public final static String TASK_ACTION_CODE_New = "c";
	public final static String TASK_ACTION_CODE_REJECT = "r";
	public final static String TASK_ACTION_CODE_ASK_FOR_PENDING_ASSIGNED = "p";
	public final static String TASK_ACTION_CODE_ASSIGN = "a";
	public final static String TASK_ACTION_CODE_IN_PROGRESS = "i";
	public final static String TASK_ACTION_CODE_DONE = "d";
	public final static String TASK_ACTION_CODE_REVALUE = "v";
	public final static String TASK_ACTION_CODE_UPLOADFILE = "f";
	public final static String TASK_ACTION_CODE_COMPONENTSELECT = "s";
	
	public final static String ASSET_ACTION_CODE_ADD_LOG = "n";
	public final static String ASSET_ACTION_CODE_NEW = "c";
	public final static String ASSET_ACTION_CODE_UPDATE = "u";
	public final static String ASSET_ACTION_CODE_UPLOADFILE = "f";
	
//	public final static String TASK_ACTION_CODE_NC = "NC";
//	public final static String TASK_ACTION_CODE_NS = "NS";
//	public final static String TASK_ACTION_CODE_NI = "NI";
//	public final static String TASK_ACTION_CODE_CA = "CA";
//	public final static String TASK_ACTION_CODE_CI = "CI";
//	public final static String TASK_ACTION_CODE_CR = "CR";
//	public final static String TASK_ACTION_CODE_AI = "AI";
//	public final static String TASK_ACTION_CODE_AP = "AP";
//	public final static String TASK_ACTION_CODE_PA = "PA";
//	public final static String TASK_ACTION_CODE_PI = "PI";
//	public final static String TASK_ACTION_CODE_IA = "IA";
//	public final static String TASK_ACTION_CODE_ID = "ID";
	
	//status, allow action, targetstatus, flag that this action must be perform by AsiTo of task, flag need to set assignedTo(0/notUpdate, 1/setSELF, 2/SetAsiTo, 3/SetNull)
	public final static Object[][] STATUS_ACTIONCODE_MATRIX={
		{null, 								TASK_ACTION_CODE_ADD_LOG, 					null, 							false, 			0},
		{TASK_STATUS_CREATED, 				TASK_ACTION_CODE_ASSIGN, 					TASK_STATUS_ASSIGNED, 			false, 			2},
		{TASK_STATUS_CREATED, 				TASK_ACTION_CODE_IN_PROGRESS, 				TASK_STATUS_IN_PROGRESS, 		false,			1},
		{TASK_STATUS_CREATED, 				TASK_ACTION_CODE_REJECT, 					TASK_STATUS_REJECTED, 			false, 			0},
		{TASK_STATUS_PENDING_REASSIGNED,	TASK_ACTION_CODE_ASSIGN, 					TASK_STATUS_ASSIGNED, 			false, 			2},
		{TASK_STATUS_PENDING_REASSIGNED,	TASK_ACTION_CODE_IN_PROGRESS, 				TASK_STATUS_IN_PROGRESS, 		false, 			1},
		{TASK_STATUS_ASSIGNED,				TASK_ACTION_CODE_ASK_FOR_PENDING_ASSIGNED,	TASK_STATUS_PENDING_REASSIGNED, true, 			3},
		{TASK_STATUS_ASSIGNED, 				TASK_ACTION_CODE_ASSIGN,					null,							false, 			2},
		{TASK_STATUS_ASSIGNED,				TASK_ACTION_CODE_IN_PROGRESS,				TASK_STATUS_IN_PROGRESS,		true,			0},
		{TASK_STATUS_IN_PROGRESS,			TASK_ACTION_CODE_ASK_FOR_PENDING_ASSIGNED,	TASK_STATUS_PENDING_REASSIGNED,	true,   		3},
		{TASK_STATUS_IN_PROGRESS,			TASK_ACTION_CODE_DONE,						TASK_STATUS_DONE,				true,   		0},
		{TASK_STATUS_DONE,					TASK_ACTION_CODE_REVALUE,					null,							false,  		0}
		//from status						//action									//to status						//need self,	//setAssignedTo
	};
	
	
	public final static String TASK_NUM_PREFIX = "T";
	public final static String ASSET_NUM_PREFIX = "A";
	public final static int AUTOGEN_NUM_LENGTH = 6;
	
	public final static String DB_FIELD_ENABLE_NAME = "enabled";
	public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat SIMPLE_DATE_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public final static int SIZE_PER_PAGE = 10;
	
	public final static String MODEL_RESOURCES_KEY_METHOD_NAME = "getResourceId";
	public final static String MODEL_RESOURCES_VALUE_METHOD_NAME = "getDescription";
	
	public final static String DEFAULT_INIT_PASSWORD = "888888";
	
	
	public final static String HTML_PREFIX_CONTENT_LOGIN = "<!--login-->";
	public final static String HTML_PREFIX_CONTENT_ERROR = "<!--error-->";
	public final static String HTML_PREFIX_CONTENT_SUCCESS = "<!--success-->";
	
	public final static String ATTR_TYPE_MUL = "MULTI_SELECT";
	public final static String ATTR_TYPE_SEL = "SELECT";
	public final static String ATTR_TYPE_TXT = "ENTRY";
	public final static String ATTR_TYPE_BOL = "BOOLEAN";
	public final static String ATTR_TYPE_CAL = "CALENDAR";
	
	public final static String FIELD_NAME_PREFIX = "u1teamplate_";
	
	public final static String FIELD_VALIDATE_NOT_ALLOW_EMPTY = "EMPTY_NOT_ALLOW";
	public final static String FIELD_VALIDATE_INVALID = "INVALID_INPUT";
	
	public final static String ERROR_MESSAGE_DUPLICATE_ENTRY_PREFIX = "Duplicate entry";
	public final static String ERROR_AVOID_COMPONENTSOTRE_DUPLICATE = "AVOID_COMPONENTSOTRE_DUPLICATE";
	
	public final static String ERROR_UPDATE_COMPONENT_STORE = "component_store_update_not_enought";
	
	public final static String ERROR_TASK_STATUS = "ERROR_TASK_STATUS";
	public final static String ERROR_COMPONENT_SELECT = "ERROR_COMPONENT_SELECT";
}
