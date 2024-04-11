package com.reporter.pdf.casinorep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reporter.pdf.casinorep.singleton.ReporterPropertiesSingleton;

@Service
public class SingletonsInitializer {
	
	@Autowired
    private ResourcesProperties appResourcesProperties;	
	
	public void initializeSinghletons() {

		ReporterPropertiesSingleton.getInstance().setAppResourcesProperties(appResourcesProperties);
	}
}
