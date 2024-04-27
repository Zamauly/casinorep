package com.reporter.pdf.casinorep.dto;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class ImageToPDFReportDto {

	private String id;
	protected String UUIDReport;
	protected String uriPath;
	
	public ImageToPDFReportDto(String id, String UUIDReport, String uriPath) {
		super();
		this.id = id;
		this.UUIDReport = UUIDReport;
		this.uriPath = uriPath;
	}

	public String getId() {
		return id;
	}

	public String getUUIDReport() {
		return UUIDReport;
	}

	public void setUUIDReport(String uUIDReport) {
		UUIDReport = uUIDReport;
	}

	public String getUriPath() {
		return uriPath;
	}

	public void setUriPath(String uriPath) {
		this.uriPath = uriPath;
	}
	
}
