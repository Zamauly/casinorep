package com.reporter.pdf.casinorep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reporter.pdf.casinorep.config.SingletonsInitializer;

@SpringBootApplication
public class CasinorepApplication {

	private static Logger logger = LoggerFactory.getLogger(CasinorepApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CasinorepApplication.class, args);
		logger.info("Test logger");
		//logger.debug("Test logger");
		//logger.trace("Test logger");
	}

}
