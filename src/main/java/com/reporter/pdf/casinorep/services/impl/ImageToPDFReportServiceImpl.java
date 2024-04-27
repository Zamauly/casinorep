package com.reporter.pdf.casinorep.services.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.reporter.pdf.casinorep.dto.ImageToPDFReportDto;
import com.reporter.pdf.casinorep.model.ImageToPDFReport;
import com.reporter.pdf.casinorep.repository.ImageToPDFReportRepository;
import com.reporter.pdf.casinorep.services.ImageToPDFReportService;

@Service
public class ImageToPDFReportServiceImpl implements ImageToPDFReportService {

	private static Logger logger = LoggerFactory.getLogger(ImageToPDFReportServiceImpl.class);

	@Autowired
	private ImageToPDFReportRepository fileRepository;

	@Override
	public ImageToPDFReportDto saveAttachment(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {

			logger.debug(":: This is fileName {}", fileName);
			logger.debug(":: This is file.getBytes().length {}", file.getBytes().length);
			logger.debug(":: This is file.getContentType() {}", file.getContentType());
			if (fileName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName);
			}
			if (file.getBytes().length > (4096 * 1024)) {
				throw new Exception("File size exceeds maximum limit: "+(4096/1024)+"MB");
			}
			switch(file.getContentType().toLowerCase().trim()) {
				case "image/png":
				case "image/jpeg":
				case "image/jpg":
					ImageToPDFReport attachment = new ImageToPDFReport(fileName, file.getContentType(), file.getBytes(),
							"generated_report_uuid");
					logger.debug(":: This is the image to save into report {}", attachment.toString());
					attachment = fileRepository.save(attachment);
					ImageToPDFReportDto imageSaved = new ImageToPDFReportDto(attachment.getId(),attachment.getReportId(),"");
					return imageSaved;
				default:
					throw new Exception("Unssuported file extension");
			}

		} catch (MaxUploadSizeExceededException e) {
			throw new MaxUploadSizeExceededException(file.getSize());
		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName + "ERROR_MSG: " + e.getMessage());
		}
	}

	@Override
	public void saveFiles(MultipartFile[] files) throws Exception {
		Arrays.asList(files).forEach(file -> {
			try {
				saveAttachment(file);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public List<ImageToPDFReport> getAllFiles() {
		return fileRepository.findAll();
	}

}
