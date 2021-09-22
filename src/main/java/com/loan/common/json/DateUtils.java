package com.loan.common.json;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	
	public static String getDateToYYYMMDD(Date date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
		return strDate;
	}

}
