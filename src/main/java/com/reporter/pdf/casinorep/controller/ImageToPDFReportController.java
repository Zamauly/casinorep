package com.reporter.pdf.casinorep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reporter.pdf.casinorep.dto.ImageToPDFReportDto;
import com.reporter.pdf.casinorep.dto.resources.ResponseDTO;
import com.reporter.pdf.casinorep.model.ImageToPDFReport;
import com.reporter.pdf.casinorep.services.ImageToPDFReportService;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class ImageToPDFReportController {
    @Autowired
    private ImageToPDFReportService fileService;

   // for uploading the SINGLE file to the database
    @PostMapping("/single/base")
    public ResponseEntity<ResponseDTO<?>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

    	ImageToPDFReportDto attachment = null;
        String downloadURl = "";
        attachment = fileService.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO<String>("O0", "se procesa peticion", downloadURl));
    }
    
    //for uploading the MULTIPLE files to the database
    @PostMapping("/multiple/base")
    public ResponseEntity<ResponseDTO<?>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception {
        List<String> responseList = new ArrayList<>();
        try {
	        for (MultipartFile file : files) {
	        	ImageToPDFReportDto attachment = fileService.saveAttachment(file);
	            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                    .path("/download/")
	                    .path(attachment.getId())
	                    .toUriString();
				/*
				 * ResponseClass response = new ResponseClass(attachment.getFileName(),
				 * downloadUrl, file.getContentType(), file.getSize());
				 */
	            
	            responseList.add(downloadUrl);
	        }
        }catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body(new ResponseDTO<String>("O1", "Error at process files", e.getMessage()));
        }
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO<List<String>>("O0", "se procesa peticion", responseList));
    }
    //for retrieving  all the  files uploaded 
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<?>>getAllFiles() {
        List<ImageToPDFReport> imagesToReport = fileService.getAllFiles();
        List<String> responseClasses = imagesToReport.stream().map(imageToReport -> {
            String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(imageToReport.getId())
                    .toUriString();
            //return new ResponseDTO<String>("O0", "se procesa peticion", downloadURL);
            return downloadURL;
        }).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO<List<String>>("O0", "se procesa peticion", responseClasses));
    }
    @PostMapping("/single/file")
    public ResponseEntity<ResponseDTO<?>> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("\\tmp\\images_pdf\\" + fileName));
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName)
                    .toUriString();

    		return ResponseEntity.status(HttpStatus.OK)
    				.body(new ResponseDTO<String>("O0", "se procesa peticion", downloadUrl));
        } catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body(new ResponseDTO<String>("O1", "Error at process files", e.getMessage()));
        }
    }
    
    //for uploading the MULTIPLE file to the File system
    @PostMapping("/multiple/file")
    public ResponseEntity<ResponseDTO<?>> handleMultipleFilesUpload(@RequestParam("files") MultipartFile[] files) {
        List<String> responseList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File("\\tmp\\images_pdf\\" + fileName));
                String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(fileName)
                        .toUriString();

                responseList.add(downloadUrl);
            } catch (Exception e) {	
            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body(new ResponseDTO<String>("O1", "Error at process files", e.getMessage()));
            
            }
        }
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO<List<String>>("O0", "se procesa peticion", responseList));
    }
}
