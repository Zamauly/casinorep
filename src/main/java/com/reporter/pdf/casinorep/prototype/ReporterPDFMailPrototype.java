package com.reporter.pdf.casinorep.prototype;

import java.util.List;
import java.util.Objects;

public class ReporterPDFMailPrototype extends ReporterAbstract {
	
	private ReporterPDFMailPrototype(ReporterPDFMailPrototypeBuilder builder) {
		super(builder);
	}
	
	@Override
	public String toString() {
		return "ReporterPDFMailPrototype: "+this.getReportName()+", "+this.getSubsidiaryName()+", "+this.getReportDate()+", "+this.defaultImageList+", "+this.logoPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultImageList, logoPath, getReportDate(), getReportName(), getSubsidiaryName());
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
		return Objects.equals(defaultImageList, other.getDefaultImageList()) && Objects.equals(logoPath, other.getLogoPath())
				&& Objects.equals(getReportDate(), other.getReportDate()) && Objects.equals(getReportName(), other.getReportName())
				&& Objects.equals(getSubsidiaryName(), other.getSubsidiaryName());
	}

	@Override
	public ReporterAbstract clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new ReporterPDFMailPrototype.ReporterPDFMailPrototypeBuilder(this.getReportType(), this.defaultImageList, this.logoPath)
				.reportDate(this.getReportDate())
				.reportName(this.getReportName())
				.subsidiaryName(this.getSubsidiaryName())
				.build();
		//return reporter;
	}

	public static class ReporterPDFMailPrototypeBuilder extends ReporterAbstractBuilder{

		public ReporterPDFMailPrototypeBuilder(String reportType, List<String> defaultImageList, String logoPath) {
			super(reportType, defaultImageList, logoPath);
			// TODO Auto-generated constructor stub
		}

		@Override
		public ReporterPDFMailPrototype build() {
			ReporterPDFMailPrototype reporter =  new ReporterPDFMailPrototype(this);
			validateReporterObject(reporter);
			return reporter;
		}

		private void validateReporterObject(ReporterPDFMailPrototype reporter) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}

		@Override
		public void validateReporterObject(ReporterAbstract reporter) {
			// TODO Auto-generated method stub
			
		}
	}
}
