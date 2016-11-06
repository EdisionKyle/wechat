/**
 *  工程名：wechat
 *  文件名：DateUtils.java
 *  包名：com.miles.wechat
 *  创建时间：2016年11月4日 下午2:19:30
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 */
package com.miles.wechat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  类名：DateUtils
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月4日 下午2:19:30
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 */
public class DateUtils {

	/** 定义常量 **/
	public static final String DATE_JFP = "yyyyMMdd";
	public static final String DATE_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL = "yyyy-MM-dd";

	public static String formatNow() {
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL);
		return sd.format(new Date());
	}

}