package com.reporter.pdf.casinorep.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.reporter.pdf.casinorep.dto.FileInfoDto;
import com.reporter.pdf.casinorep.dto.resources.ResponseDTO;
//import com.reporter.pdf.casinorep.dto.ResponseMessage;
import com.reporter.pdf.casinorep.services.FilesStorageService;

@Controller
@CrossOrigin("http://localhost:8081")
public class FilesController {

	private static Logger logger = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	FilesStorageService storageService;

	@PostMapping("/report-pdf-content")
	public ResponseEntity<ResponseDTO<?>> doReportWithImages(@RequestParam("files") MultipartFile[] files) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();

			Arrays.asList(files).stream().forEach(file -> {		
				logger.debug("Get Reporter File TEST Name: {}", file.getName());
				logger.debug("Get Reporter File TEST type: {}", file.getContentType());

				//storageService.save(file);
				fileNames.add(file.getOriginalFilename());
			});

			message = "Uploaded the files successfully: " + fileNames;
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO<String>("O0", "se procesa peticion", message));
		} catch (Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO<String>("O1", "error al procesar", message));
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseDTO<?>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();

			Arrays.asList(files).stream().forEach(file -> {
				storageService.save(file);
				fileNames.add(file.getOriginalFilename());
			});

			message = "Uploaded the files successfully: " + fileNames;
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO<String>("O0", "se procesa peticion", message));
		} catch (Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO<String>("O1", "error al procesar", message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfoDto>> getListFiles() {
		List<FileInfoDto> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

			return new FileInfoDto(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
