package com.reporter.pdf.casinorep.services;

import java.util.List;

import com.reporter.pdf.casinorep.dto.ImageToPDFReportDto;
import com.reporter.pdf.casinorep.model.ImageToPDFReport;
import org.springframework.web.multipart.MultipartFile;

public interface ImageToPDFReportService {
	
	ImageToPDFReportDto saveAttachment(MultipartFile file) throws Exception;
    void saveFiles(MultipartFile[] files) throws Exception;
    List<ImageToPDFReport> getAllFiles();
    
}
