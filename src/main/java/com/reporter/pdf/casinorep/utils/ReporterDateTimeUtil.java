package com.reporter.pdf.casinorep.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;  
//import java.text.SimpleDateFormat;  

public class ReporterDateTimeUtil {
	//dateFormatDef preferred to use "dd-mm-yyyy"
	public static Date getDateFromString(String dateToParse, String dateFormatDef) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatDef, Locale.US);

		Date date = null;
		date = formatter.parse(dateToParse);
		return date;
	}
	
	//dateFormatDef preferred to use "ddmmyyhhmm"
	public static String getFormatStringFromDate(Date dateToParse, String dateFormatDef) throws Exception {
        //Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat(dateFormatDef);  
        String strDate = null;
		strDate = dateFormat.format(dateToParse);
		return strDate;
	}
}
