package com.reporter.pdf.casinorep.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reporter.pdf.casinorep.dto.PDFReporterDTO;
import com.reporter.pdf.casinorep.dto.PDFRequestDTO;
import com.reporter.pdf.casinorep.dto.resources.RequestDTO;
import com.reporter.pdf.casinorep.dto.resources.ResponseDTO;
import com.reporter.pdf.casinorep.prototype.ReporterPDFPrototype;
import com.reporter.pdf.casinorep.services.ReporterPDFService;
import com.reporter.pdf.casinorep.utils.ReporterDateTimeUtil;

@RestController
public class PdfRestController {

	private static Logger logger = LoggerFactory.getLogger(PdfRestController.class);
	
	@Autowired
	ReporterPDFService reporterService;
	
	@GetMapping("/pdf-report")
	ResponseEntity<ResponseDTO<PDFReporterDTO>> testGet() {
		ReporterPDFPrototype reporter = new ReporterPDFPrototype.ReporterPDFPrototypeBuilder("ASECO", null, "/home/asus13/Documents/projects/casinorep/resources/images-api/aseco.png")
				.reportDate(new Date())
				.reportName("Hipocampo")
				.subsidiaryName("Hipocampo")
				.build();
		
		logger.debug("Get Reporter TEST type: {}", reporter.getReportName());

		logger.debug("Get Reporter TEST name: {}", reporter.getSubsidiaryName());

		logger.debug("Get Reporter TEST key: {}", reporter.getLogoPath());

		logger.debug("Get Reporter TEST hashCode: {}", reporter.hashCode());
		PDFReporterDTO reporterDto =  new PDFReporterDTO.PDFReporterDTOBuilder(reporter).build();
		
		return ResponseEntity.ok(new ResponseDTO<PDFReporterDTO>("O0", "se procesa peticion", reporterDto));

	}	
	
	protected String setFileName(String embededName, Date embededDate, String timeFormat) throws Exception {

		String reportName = "casino-pdf-report-"+embededName+"-%DATE%.pdf";
		return reportName.replace("%DATE%", ReporterDateTimeUtil.getFormatStringFromDate(embededDate, timeFormat));
	}
	
	@PostMapping("/pdf-report")
	ResponseEntity<ResponseDTO<?>> testPostBodySpecificResEntityClass(@RequestBody RequestDTO<PDFRequestDTO> reqBody) {

		logger.debug("Post Req Reporter TEST type: {}", reqBody.getRequest_type());

//		logger.debug("Post Req Reporter TEST name: {}", reqBody.getRequest_body().getReport_name());

		logger.debug("Post Req Reporter TEST key: {}", reqBody.getRequest_body().getSubsidiary_name());

		logger.debug("Post Req Reporter TEST key: {}", reqBody.getRequest_body().getReport_date());

		try {
			PDFReporterDTO reporterDto = reporterService.generatePDF(reqBody.getRequest_body());
			if(reqBody.getRequest_type().equals("generate-pdf")) {

				//response_data.setMessage("logged-in"); 
				//response_data.setToken_key("ndkjdhfadd0q3980q923849q3r"); 
				//ResponseDTO<Map<String, Object>> responseBody = new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);

				//return ResponseEntity.badRequest().build();
				reporterDto.setFileStatus(true);
				logger.debug("Post DTO Reporter TEST key: {}", reporterDto.getFileStatus());
				return ResponseEntity.ok(new ResponseDTO<PDFReporterDTO>("O0", "se procesa peticion", reporterDto));
			} else {
				//response_data.setMessage("logged-fail"); 
				//response_data.setToken_key("fail?token?generation"); 

				reporterDto.setFileStatus(false);
				logger.debug("Post DTO Reporter TEST key: {}", reporterDto.getFileStatus());
				return ResponseEntity.badRequest().body(new ResponseDTO<PDFReporterDTO>("O1", "no se procesa peticion", reporterDto));
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();

			Map<String, Object> response_data = new HashMap<String, Object>();

			response_data.put("error_message", e.getMessage()); 
			response_data.put("error", e.getCause()); 
			return ResponseEntity.badRequest().body(new ResponseDTO<Map<String, Object> >("O2", "no se procesa peticion", response_data));
			
		}
		

	}
}
