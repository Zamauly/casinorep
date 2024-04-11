package com.reporter.pdf.casinorep.singleton;

import org.springframework.beans.factory.annotation.Autowired;

import com.reporter.pdf.casinorep.config.ResourcesProperties;

public class ReporterPropertiesSingleton {
	
	@Autowired
    private ResourcesProperties appResourcesProperties;
	
    private static ReporterPropertiesSingleton instance;
    
    private ReporterPropertiesSingleton( ) {
        // The following code emulates slow initialization.
        try {
            //Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ReporterPropertiesSingleton getInstance() {
        if (instance == null) {
            instance = new ReporterPropertiesSingleton();
        }
        return instance;
    }

	public ResourcesProperties getAppResourcesProperties() {
		return appResourcesProperties;
	}

	public void setAppResourcesProperties(ResourcesProperties appResourcesProperties) {
        if (this.appResourcesProperties == null) {
        	this.appResourcesProperties = appResourcesProperties;
        }
	}

}
