package com.reporter.pdf.casinorep.prototype;

import java.util.Date;
import java.util.List;


public abstract class ReporterAbstract implements Cloneable {
	//All final attributes
	private String reportName; // optional
	private String subsidiaryName; // optional
	//private final int age; // optional
	private Date reportDate; // optional
	private List<String> reportImageList; // required
	private final String reportType;// required
	protected final List<String> defaultImageList; // required
	protected final String logoPath; // required
	
	protected ReporterAbstract(ReporterAbstractBuilder builder) {
		this.reportName = builder.reportName;
		this.subsidiaryName = builder.subsidiaryName;
		this.reportDate = builder.reportDate;
		this.reportImageList = builder.reportImageList;
		this.reportType = builder.reportType;
		this.defaultImageList = builder.defaultImageList;
		this.logoPath = builder.logoPath;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getSubsidiaryName() {
		return subsidiaryName;
	}
	public void setSubsidiaryName(String subsidiaryName) {
		this.subsidiaryName = subsidiaryName;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public List<String> getReportImageList() {
		return reportImageList;
	}
	public void setReportImageList(List<String> reportImageList) {
		this.reportImageList = reportImageList;
	}
	public String getReportType() {
		return reportType;
	}
	public List<String> getDefaultImageList() {
		return defaultImageList;
	}
	public String getLogoPath() {
		return logoPath;
	}

	@Override
	public abstract String toString();
	
	@Override
	public abstract int hashCode();

	@Override
	public abstract ReporterAbstract clone() throws CloneNotSupportedException;
	
	@Override
	public abstract boolean equals(Object obj);
	
	public abstract static class ReporterAbstractBuilder{
		//All final attributes
		private String reportName; // optional
		private String subsidiaryName; // optional
		//private final int age; // optional
		private Date reportDate; // optional
		private List<String> reportImageList; // required
		private final String reportType;// required
		private final List<String> defaultImageList; // required
		private final String logoPath; // required
		
		public ReporterAbstractBuilder(String reportType, List<String> defaultImageList, String logoPath) {
			this.reportType = reportType;
			this.defaultImageList = defaultImageList;
			this.logoPath = logoPath;
		}
		public ReporterAbstractBuilder reportDate(Date reportDate) {
			this.reportDate = reportDate;
			return this;
		}
		public ReporterAbstractBuilder reportName(String reportName) {
			this.reportName = reportName;
			return this;
		}
		public ReporterAbstractBuilder subsidiaryName(String subsidiaryName) {
			this.subsidiaryName = subsidiaryName;
			return this;
		}
		public ReporterAbstractBuilder reportImageList(List<String> reportImageList) {
			this.reportImageList = reportImageList;
			return this;
		}
		//Return the finally constructed User object
		public abstract ReporterAbstract build();
		public abstract void validateReporterObject(ReporterAbstract reporter);
	}
}
