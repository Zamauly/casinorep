package com.reporter.pdf.casinorep.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.reporter.pdf.casinorep.commons.ResourcesConstants;
import com.reporter.pdf.casinorep.dto.PDFReporterDTO;
import com.reporter.pdf.casinorep.dto.PDFRequestDTO;
import com.reporter.pdf.casinorep.prototype.ReporterPDFPrototype;
import com.reporter.pdf.casinorep.services.ReporterPDFService;
import com.reporter.pdf.casinorep.utils.FileReporterUtil;
import com.reporter.pdf.casinorep.utils.ReporterDateTimeUtil;

@Service
public class ReporterPDFServiceImpl implements ReporterPDFService {

	private static Logger logger = LoggerFactory.getLogger(ReporterPDFServiceImpl.class);
	
	@Override
	public PDFReporterDTO generatePDF(PDFRequestDTO dataToProcess) throws Throwable {
		// TODO Auto-generated method stub
		Date reportDate = ReporterDateTimeUtil.getDateFromString(dataToProcess.getReport_date(), "dd-mm-yyyy");
		List<String> imageList = new ArrayList<String>(); 
		imageList.add("/home/asus13/Documents/projects/casinorep/resources/images-api/test-1.png");
		imageList.add("/home/asus13/Documents/projects/casinorep/resources/images-api/test-2.xnl");
		imageList.forEach(imageItem -> {
			if(!FileReporterUtil.validateImageFormat(imageItem)) throw new RuntimeException(ResourcesConstants.FORMAT_IMG_ERROR_MSG.concat(imageItem));
		});
		String reportName = FileReporterUtil.getDateFormatedFileName(dataToProcess.getReport_name(), reportDate);
		ReporterPDFPrototype reporter = new ReporterPDFPrototype.ReporterPDFPrototypeBuilder("ASECO", imageList, "/home/asus13/Documents/projects/casinorep/resources/images-api/aseco.png")
				.reportDate(reportDate)
				.reportName(reportName)
				.subsidiaryName(dataToProcess.getSubsidiary_name())
				.build();

		logger.debug("Post Prototype Reporter TEST type: {}", reporter.getReportName());

		logger.debug("Post Prototype Reporter TEST name: {}", reporter.getSubsidiaryName());

		logger.debug("Post Prototype Reporter TEST key: {}", reporter.getLogoPath());

		logger.debug("Post Prototype Reporter TEST hashCode: {}", reporter.hashCode());
		
		ReporterPDFPrototype cloned_reporter = reporter.clone();
		reportName = FileReporterUtil.getDateFormatedFileName(dataToProcess.getReport_name(), reportDate);
		
		cloned_reporter.setReportName(reportName);
		cloned_reporter.setSubsidiaryName(dataToProcess.getSubsidiary_name()+" : CLON");
		cloned_reporter.setReportDate(ReporterDateTimeUtil.getDateFromString("24-01-2024", "dd-mm-yyyy"));
		/*
		 * List<String> imageList = new ArrayList<String>(); imageList.add(
		 * "/home/asus13/Documents/projects/casinorep/resources/images-api/test-1.png");
		 * imageList.add(
		 * "/home/asus13/Documents/projects/casinorep/resources/images-api/test-2.png");
		 * cloned_reporter.setDefaultImageList(imageList);
		 */
		logger.debug("Post ClonedPrototype Reporter TEST type: {}", cloned_reporter.getReportName());

		logger.debug("Post ClonedPrototype Reporter TEST name: {}", cloned_reporter.getSubsidiaryName());

		logger.debug("Post ClonedPrototype Reporter TEST key: {}", cloned_reporter.getLogoPath());

		logger.debug("Post ClonedPrototype Reporter TEST hashCode: {}", cloned_reporter.hashCode());
		
		PDFReporterDTO reporterDto =  new PDFReporterDTO.PDFReporterDTOBuilder(cloned_reporter).build();

		logger.debug("Post DTO Reporter TEST type: {}", reporterDto.getReportName());

		logger.debug("Post DTO Reporter TEST name: {}", reporterDto.getSubsidiaryName());

		logger.debug("Post DTO Reporter TEST hashCode: {}", reporterDto.hashCode());
		
		return reporterDto; 
	}

}
