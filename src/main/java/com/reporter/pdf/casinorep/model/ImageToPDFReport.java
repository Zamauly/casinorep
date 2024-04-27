package com.reporter.pdf.casinorep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;
import lombok.ToString;

@ToString
@Entity
@Table(name = "files_to_images")
public class ImageToPDFReport {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;
    private String reportId;
    
    @Lob
    private byte[] data;

	public ImageToPDFReport() {
		super();
	}

	public ImageToPDFReport(String fileName, String fileType, byte[] data, String reportId) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.reportId = reportId;
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
    
}
