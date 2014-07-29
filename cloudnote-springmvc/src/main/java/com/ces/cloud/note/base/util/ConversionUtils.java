package com.ces.cloud.note.base.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基本数据类型转换类
 * 
 * @author hc
 *
 */
public class ConversionUtils {

	public static Integer decimalFormatInteger(Double number) {
		DecimalFormat formula = new DecimalFormat("#");
		return new Integer(formula.format(number));
	}

	public static Double doubleFormat(Double number, String format) {
		DecimalFormat formula = new DecimalFormat(format);
		return Double.valueOf(formula.format(number));
	}
	
	public static String decimalFormat(Double number, String format) {
		DecimalFormat formula = new DecimalFormat(format);
		return formula.format(number);
	}

	public static String formatDate(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
