package com.reporter.pdf.casinorep.services;

import com.reporter.pdf.casinorep.dto.PDFReporterDTO;
import com.reporter.pdf.casinorep.dto.PDFRequestDTO;

public interface ReporterPDFService {

	public abstract PDFReporterDTO generatePDF(PDFRequestDTO dataToProcess) throws Throwable;
	
}
