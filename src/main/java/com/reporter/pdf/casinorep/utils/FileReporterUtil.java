package com.reporter.pdf.casinorep.utils;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reporter.pdf.casinorep.commons.ResourcesConstants;
import com.reporter.pdf.casinorep.commons.ResourcesConstants.ValidDependencyFormats;
import com.reporter.pdf.casinorep.commons.ResourcesConstants.ValidImageFormats;
import com.reporter.pdf.casinorep.singleton.ReporterPropertiesSingleton;

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
		
		return ValidImageFormats.evalTypes(imgExt);
	}
	
	public static String validateDependencyLogo(String subsidiaryName) throws Exception {
		logger.debug("Post reporter subsidiaryName: {}", subsidiaryName);
		String dependencyLogo = ValidDependencyFormats.evalDependencyLogo(subsidiaryName);
		logger.debug("Post reporter dependencyLogo: {}", dependencyLogo);
		if(dependencyLogo != null) 
			switch(dependencyLogo) {
				case ResourcesConstants.LOGO_ONE:
					dependencyLogo = ReporterPropertiesSingleton.getInstance().getAppResourcesProperties().getBase_images().getLogo_aseco();
					break;
				case ResourcesConstants.LOGO_TWO:
				default:
					dependencyLogo = ReporterPropertiesSingleton.getInstance().getAppResourcesProperties().getBase_images().getLogo_limpo();
					break;
			}
		else
			dependencyLogo = ReporterPropertiesSingleton.getInstance().getAppResourcesProperties().getBase_images().getLogo_limpo();

		return dependencyLogo;
	}
	
	public static List<String> getBaseImages(String typeReport) throws Exception {
		
		/*
		 * List<String> testListBaseImgs =
		 * ReporterPropertiesSingleton.getInstance().getAppResourcesProperties().
		 * getBase_images().getFiles_base(); if(testListBaseImgs.equals(null))
		 * logger.debug("Test list images is null "); else
		 * testListBaseImgs.forEach(baseImgElement -> {
		 * logger.debug("Get image extension : {}", baseImgElement); });
		 */
		
		switch(typeReport) {
			case ResourcesConstants.PDF_TYPE:
			default:
				//List<String> baseImageList = new ArrayList<>();
				return ReporterPropertiesSingleton.getInstance().getAppResourcesProperties().getBase_images().getFiles_base();
		
		}
		
	}
	
}
