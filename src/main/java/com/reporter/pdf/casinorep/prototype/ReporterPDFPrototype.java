package com.reporter.pdf.casinorep.prototype;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReporterPDFPrototype implements Cloneable {
	//All final attributes
	private String reportName; // required
	private String subsidiaryName; // required
	//private final int age; // optional
	private Date reportDate; // optional
	private final String reportType;
	private final List<String> defaultImageList; // optional
	private final String logoPath; // optional

	private ReporterPDFPrototype(ReporterPDFPrototypeBuilder builder) {
		this.reportName = builder.reportName;
		this.subsidiaryName = builder.subsidiaryName;
		this.reportDate = builder.reportDate;
		this.reportType = builder.reportType;
		this.defaultImageList = builder.defaultImageList;
		this.logoPath = builder.logoPath;
	}

	//All getter, and NO setter to provide immutability
	public String getReportName() {
		return reportName;
	}
	public String getSubsidiaryName() {
		return subsidiaryName;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public List<String> getDefaultImageList() {
		return defaultImageList;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public void setSubsidiaryName(String subsidiaryName) {
		this.subsidiaryName = subsidiaryName;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "ReporterPDFPrototype: "+this.reportName+", "+this.subsidiaryName+", "+this.reportDate+", "+this.defaultImageList+", "+this.logoPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultImageList, logoPath, reportDate, reportName, subsidiaryName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReporterPDFPrototype other = (ReporterPDFPrototype) obj;
		return Objects.equals(defaultImageList, other.defaultImageList) && Objects.equals(logoPath, other.logoPath)
				&& Objects.equals(reportDate, other.reportDate) && Objects.equals(reportName, other.reportName)
				&& Objects.equals(subsidiaryName, other.subsidiaryName);
	}

	@Override
	public ReporterPDFPrototype clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ReporterPDFPrototype.ReporterPDFPrototypeBuilder(this.reportType, this.defaultImageList, this.logoPath)
				.reportDate(this.reportDate)
				.reportName(this.reportName)
				.subsidiaryName(this.subsidiaryName)
				.build();
		//return reporter;
	}

	public static class ReporterPDFPrototypeBuilder
	{

		private String reportName;
		private String subsidiaryName;
		private Date reportDate;
		private final String reportType;
		private final List<String> defaultImageList;
		private final String logoPath;

		public ReporterPDFPrototypeBuilder(String reportType, List<String> defaultImageList, String logoPath) {
			this.reportType = reportType;
			this.defaultImageList = defaultImageList;
			this.logoPath = logoPath;
		}
		public ReporterPDFPrototypeBuilder reportDate(Date reportDate) {
			this.reportDate = reportDate;
			return this;
		}
		public ReporterPDFPrototypeBuilder reportName(String reportName) {
			this.reportName = reportName;
			return this;
		}
		public ReporterPDFPrototypeBuilder subsidiaryName(String subsidiaryName) {
			this.subsidiaryName = subsidiaryName;
			return this;
		}
		//Return the finally constructed User object
		public ReporterPDFPrototype build() {
			ReporterPDFPrototype reporter =  new ReporterPDFPrototype(this);
			validateUserObject(reporter);
			return reporter;
		}
		private void validateUserObject(ReporterPDFPrototype reporter) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}
	}
}