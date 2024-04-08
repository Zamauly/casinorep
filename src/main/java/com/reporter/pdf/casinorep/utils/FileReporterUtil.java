package com.reporter.pdf.casinorep.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reporter.pdf.casinorep.commons.ResourcesConstants;
import com.reporter.pdf.casinorep.commons.ResourcesConstants.ValidImageFormats;

public class FileReporterUtil {

	private static Logger logger = LoggerFactory.getLogger(FileReporterUtil.class);

	public static String getDateFormatedFileName(String seatedName, Date reportDate) throws Exception {
		String reportName = ResourcesConstants.getCompleteFormatName();
		reportName = reportName.replace(ResourcesConstants.SETTED_NAME, seatedName.trim().toLowerCase());
		reportName = reportName.replace(ResourcesConstants.SETTED_DATE, ReporterDateTimeUtil.getFormatStringFromDate(reportDate, "ddmmyyhhmm"));
		return reportName;
	}
	
	public static Boolean validateImageFormat(String imageName) {
		
		imageName = imageName.trim();
		logger.debug("Get image name : {}", imageName);
	    //int lastAppersDot = imageName.lastIndexOf(".");
		if(!imageName.contains(ResourcesConstants.DOT_CHAR))
			return false;
		
		String imgExt = imageName.substring(imageName.lastIndexOf(ResourcesConstants.DOT_CHAR)).trim();
		logger.debug("Get image extension : {}", imgExt);
		
		return ValidImageFormats.evalTipes(imgExt);
	}
	
}
