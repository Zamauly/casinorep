package com.reporter.pdf.casinorep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.reporter.pdf.casinorep.config.SingletonsInitializer;
import com.reporter.pdf.casinorep.services.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class CasinorepApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(CasinorepApplication.class);
	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(CasinorepApplication.class, args);
		logger.info("Test logger");
		//logger.debug("Test logger");
		//logger.trace("Test logger");
	}
	
	@Override
	public void run(String... arg) throws Exception {
	  storageService.deleteAll();
	  storageService.init();
	}
}
